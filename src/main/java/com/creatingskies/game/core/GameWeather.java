package com.creatingskies.game.core;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.creatingskies.game.model.IRecord;

@Entity(name="gGameWeather")
public class GameWeather implements IRecord{
	
	private static final long serialVersionUID = 4425950601965006108L;
	
	private Integer idNo;
	private Game game;
	private Weather weather;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getIdNo() {
		return idNo;
	}
	
	public void setIdNo(Integer idNo) {
		this.idNo = idNo;
	}
	
	@JoinColumn(name="gameIdNo",nullable=false)
	@ManyToOne(targetEntity=Game.class)
	public Game getGame() {
		return game;
	}
	
	public void setGame(Game game) {
		this.game = game;
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
