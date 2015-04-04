package com.creatingskies.game.model.obstacle;

import java.util.List;

import com.creatingskies.game.model.GenericDAO;

public class ObstacleDAO extends GenericDAO {

	private static final long serialVersionUID = -1476100538427749478L;
	
	@SuppressWarnings("unchecked")
	public List<Obstacle> findAll(){
		return (List<Obstacle>) findAll(Obstacle.class);
	}
	
}
