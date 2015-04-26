package com.creatingskies.game.core;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

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
	public List<GameResult> findAllGameResults(Criterion...criterions){
		Session session = openSession();
		try {
			Criteria criteria = session.createCriteria(GameResult.class);
			
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteria.createAlias("group.company", "company");
			criteria.addOrder(Order.asc("company.name"));
			
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
}
