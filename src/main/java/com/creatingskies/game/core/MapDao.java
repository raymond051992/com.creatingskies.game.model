package com.creatingskies.game.core;

import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.Session;

import com.creatingskies.game.model.GenericDAO;

public class MapDao extends GenericDAO{

	private static final long serialVersionUID = 1561399064207986263L;

	@SuppressWarnings("unchecked")
	public List<Map> findAllMaps(){
		Session session = openSession();
		try{
			List<Map> maps = session.createCriteria(Map.class)
					.setFetchMode("tiles", FetchMode.JOIN)
					.list();
			
			return maps;
		}finally{
			session.close();
		}
	}
}
