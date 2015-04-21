package com.creatingskies.game.core;

import java.util.List;

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
}
