package com.creatingskies.game.core;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.creatingskies.game.model.IRecord;
import com.creatingskies.game.model.weather.Weather;

@Entity(name="gMapWeather")
public class MapWeather implements IRecord{
	
	private static final long serialVersionUID = 4425950601965006108L;
	
	private Integer idNo;
	private Map map;
	private Weather weather;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getIdNo() {
		return idNo;
	}
	
	public void setIdNo(Integer idNo) {
		this.idNo = idNo;
	}
	
	@JoinColumn(name="mapIdNo",nullable=false)
	@ManyToOne(targetEntity=Map.class)
	public Map getMap() {
		return map;
	}
	
	public void setMap(Map map) {
		this.map = map;
	}
	
	@JoinColumn(name="weatherIdNo",nullable=false)
	@ManyToOne(targetEntity=Weather.class)
	public Weather getWeather() {
		return weather;
	}
	
	public void setWeather(Weather weather) {
		this.weather = weather;
	}
}
