package com.creatingskies.game.model.weather;

import java.util.List;

import com.creatingskies.game.model.GenericDAO;

public class WeatherDAO extends GenericDAO{

	private static final long serialVersionUID = 309964740698313289L;

	@SuppressWarnings("unchecked")
	public List<Weather> findAll(){
		return (List<Weather>) findAll(Weather.class);
	}
	
}
