package com.creatingskies.game.core;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.creatingskies.game.model.GenericDAO;

public class MapDao extends GenericDAO{

	private static final long serialVersionUID = 1561399064207986263L;

	@SuppressWarnings("unchecked")
	public List<Map> findAllMaps(){
		Session session = openSession();
		try{
			List<Map> maps = session.createCriteria(Map.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.list();
			
			return maps;
		}finally{
			session.close();
		}
	}
	
	public Map findMapWithDetails(Integer idNo){
		Session session = openSession();
		try{
			Map map = (Map) session.createCriteria(Map.class)
					.add(Restrictions.eq("idNo", idNo))
					.setFetchMode("tiles", FetchMode.JOIN)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.uniqueResult();
			
			return map;
		}finally{
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<TileImage> findAllTileImages(){
		Session session = openSession();
		try{
			List<TileImage> maps = session.createCriteria(TileImage.class)
					.list();
			return maps;
		}finally{
			session.close();
		}
	}
}
