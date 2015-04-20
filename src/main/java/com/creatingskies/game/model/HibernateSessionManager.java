package com.creatingskies.game.model;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.creatingskies.game.core.Game;
import com.creatingskies.game.core.Map;
import com.creatingskies.game.core.MapDao;
import com.creatingskies.game.core.MapWeather;
import com.creatingskies.game.core.Tile;
import com.creatingskies.game.core.TileImage;
import com.creatingskies.game.model.company.Company;
import com.creatingskies.game.model.company.Group;
import com.creatingskies.game.model.company.Player;
import com.creatingskies.game.model.company.Team;
import com.creatingskies.game.model.event.GameEvent;
import com.creatingskies.game.model.obstacle.Obstacle;
import com.creatingskies.game.model.user.SecurityQuestion;
import com.creatingskies.game.model.user.User;
import com.creatingskies.game.model.user.User.Status;
import com.creatingskies.game.model.user.User.Type;
import com.creatingskies.game.model.user.UserDao;
import com.creatingskies.game.model.weather.Weather;
import com.creatingskies.game.util.Util;

public class HibernateSessionManager {

	private static final Logger LOGGER = Logger.getLogger(HibernateSessionManager.class.getName());
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	public static void buildSessionFactory() {
		try {
			if(sessionFactory == null){
				Configuration config = new Configuration();
				
				config.addAnnotatedClass(User.class);
				config.addAnnotatedClass(SecurityQuestion.class);
				config.addAnnotatedClass(Company.class);
				config.addAnnotatedClass(Group.class);
				config.addAnnotatedClass(Team.class);
				config.addAnnotatedClass(Player.class);
				
				config.addAnnotatedClass(Game.class);
				config.addAnnotatedClass(Map.class);
				config.addAnnotatedClass(Weather.class);
				config.addAnnotatedClass(MapWeather.class);
				config.addAnnotatedClass(Tile.class);
				config.addAnnotatedClass(Obstacle.class);
				
				config.addAnnotatedClass(TileImage.class);
				config.addAnnotatedClass(GameEvent.class);
				
				config.configure();
				serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(config.getProperties()).build();
				
			    sessionFactory = config.buildSessionFactory(serviceRegistry);
			    
			    initStartupUser();
			    initRequiredTileImages();
			}
		} catch (Throwable e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static Session openSession(){
		return sessionFactory.openSession();
	}

	public static void shutdown() {
		sessionFactory.close();
	}
	
	private static void initStartupUser(){
		UserDao userDao = new UserDao();
		SecurityQuestion defaultSecurityQuestion = userDao.findSecurityQuestion("default");
		
		if(defaultSecurityQuestion == null){
			defaultSecurityQuestion = new SecurityQuestion();
			defaultSecurityQuestion.setCode("default");
			defaultSecurityQuestion.setQuestion("In what city were you born?");
			userDao.save(defaultSecurityQuestion);
		}
		
		if(userDao.findUser("admin") == null){
			User user = new User();
			user.setFirstName("Game");
			user.setLastName("Admin");
			user.setUsername("admin");
			user.setPassword("admin");
			user.setType(Type.ADMIN);
			user.setStatus(Status.ACTIVE);
			user.setSecurityQuestion(defaultSecurityQuestion);
			user.setSecurityQuestionAnswer("Pasig City");
			
			user.setEntryBy("dev");
			user.setEntryDate(new Date());
			
			System.out.println("tests");
			userDao.save(user);
		}
	}
	
	private static void initRequiredTileImages(){
		MapDao mapDao = new MapDao();
		TileImage startTileImage = mapDao.findTileImageByOwner(Constant.IMAGE_START_POINT_OWNER);
		TileImage endTileImage = mapDao.findTileImageByOwner(Constant.IMAGE_END_POINT_OWNER);
		TileImage rowingTileImage = mapDao.findTileImageByOwner(Constant.IMAGE_ROWING_TILE_OWNER);
		TileImage cyclingTileImage = mapDao.findTileImageByOwner(Constant.IMAGE_CYCLING_TILE_OWNER);
		
		if(startTileImage == null){
			createTileImage(mapDao, Constant.IMAGE_START_POINT_OWNER,
					Constant.PATH_TILE_IMAGE_START_POINT_DEFAULT, true);
		}
		
		if(endTileImage == null){
			createTileImage(mapDao, Constant.IMAGE_END_POINT_OWNER,
					Constant.PATH_TILE_IMAGE_END_POINT_DEFAULT, true);
		}
		
		if(rowingTileImage == null){
			createTileImage(mapDao, Constant.IMAGE_ROWING_TILE_OWNER,
					Constant.PATH_TILE_IMAGE_ROWING_DEFAULT, false);
		}
		
		if(cyclingTileImage == null){
			createTileImage(mapDao, Constant.IMAGE_CYCLING_TILE_OWNER,
					Constant.PATH_TILE_IMAGE_CYCLING_DEFAULT, false);
		}
		
	}

	private static void createTileImage(MapDao mapDao, String owner,
			String filepath, Boolean required) {
		TileImage tileImage = new TileImage();
		tileImage.setOwner(owner);
		tileImage.setImage(Util.stringUrlToByteArray(filepath));
		tileImage.setFileName(filepath.substring(filepath.lastIndexOf("/") + 1));
		tileImage.setFileType(Util.getFileExtension(tileImage.getFileName()));
		tileImage.setEntryDate(new Date());
		tileImage.setRequired(required);
		tileImage.setEntryBy("dev");
		tileImage.setFileSize(-1L);
		mapDao.save(tileImage);
	}
	
}
