package com.creatingskies.game.model;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.creatingskies.game.model.company.Company;
import com.creatingskies.game.model.company.Group;
import com.creatingskies.game.model.company.Player;
import com.creatingskies.game.model.company.Team;
import com.creatingskies.game.model.user.User;
import com.creatingskies.game.model.user.UserDao;
import com.creatingskies.game.model.user.User.Type;

public class HibernateSessionManager {

	private static final Logger LOGGER = Logger.getLogger(HibernateSessionManager.class.getName());
	
	private static SessionFactory sessionFactory = buildSessionFactory();
	private static ServiceRegistry serviceRegistry;
	
	public static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			Configuration config = new Configuration();
			
			config.addAnnotatedClass(User.class);
			config.addAnnotatedClass(Company.class);
			config.addAnnotatedClass(Group.class);
			config.addAnnotatedClass(Team.class);
			config.addAnnotatedClass(Player.class);
			
			config.configure();
			serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(config.getProperties()).build();
			
		    sessionFactory = config.buildSessionFactory(serviceRegistry);
		    
		    initStartupUser();
		    return sessionFactory;
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
		
		if(userDao.findUser("admin") == null){
			User user = new User();
			user.setFirstName("Raymond");
			user.setLastName("Francisco");
			user.setUsername("franciscor");
			user.setPassword("holdon");
			user.setType(Type.ADMIN);
			userDao.save(user);
		}
		
	}
}
