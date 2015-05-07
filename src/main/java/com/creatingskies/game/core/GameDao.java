package com.creatingskies.game.core;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.creatingskies.game.model.GenericDAO;

public class GameDao extends GenericDAO{

	private static final long serialVersionUID = 7992525831208167204L;
	
	@SuppressWarnings("unchecked")
	public List<Game> findAllGames(){
		return (List<Game>) findAll(Game.class);
	}
	
	public Game findGameWithDetails(Integer idNo){
		Game game = (Game) super.find(Game.class, idNo);
		if(game != null && game.getMap() != null){
			Map map = new MapDao().findMapWithDetails(game.getMap().getIdNo());
			map.getTiles().size();
			game.setMap(map);
		}
		return game;
	}
	
	@SuppressWarnings("unchecked")
	public List<GameResult> findAllGameResults(Order dateOrder, Order speedOrder,
			Criterion...criterions){
		Session session = openSession();
		try {
			Criteria criteria = session.createCriteria(GameResult.class);
			
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteria.createAlias("group", "resultGroup");
			criteria.createAlias("group.company", "company");
			criteria.addOrder(dateOrder);
			criteria.addOrder(speedOrder);
			criteria.addOrder(Order.asc("resultGroup.name"));
			
			if (criterions != null && criterions.length > 0) {
				for (Criterion criterion : criterions) {
					if (criterion != null) {
						criteria.add(criterion);
					}
				}
			}
			
			return criteria.list();
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<GameResult> findAllGameResultsByGame(Game game){
		Session session = openSession();
		try {
			Criteria criteria = session.createCriteria(GameResult.class);
			criteria.add(Restrictions.eq("game", game));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
			return criteria.list();
		} finally {
			session.close();
		}
	}
}
