package com.creatingskies.game.model.type;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import com.creatingskies.game.model.GenericDAO;

public class GameTypeDAO extends GenericDAO {

	private static final long serialVersionUID = 2823339712885476884L;

	@SuppressWarnings("unchecked")
	public List<GameType> findAll(){
		return (List<GameType>) findAll(GameType.class);
	}
	
	public GameType findGameTypeByName(String name){
		return (GameType) find(GameType.class, Restrictions.eq("name", name));
	}
	
}
