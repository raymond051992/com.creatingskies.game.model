package com.creatingskies.game.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.creatingskies.game.model.IRecord;

@Entity(name="gTile")
public class Tile implements IRecord{

	private static final long serialVersionUID = 8791664261827955489L;

	public enum Orientation{
		HORIZONTAL,VERTICAL
	}
	
	private Integer idNo;
	private Game game;
	private byte[] image;
	private Orientation orientation;
	private Integer orderNo;
	private Boolean obstacle = false;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getIdNo() {
		return idNo;
	}
	
	@JoinColumn(name="gameIdNo",nullable=false)
	@ManyToOne(targetEntity=Game.class)
	public Game getGame() {
		return game;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	public void setIdNo(Integer idNo) {
		this.idNo = idNo;
	}

	@Column(nullable=false)
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	public Orientation getOrientation() {
		return orientation;
	}
	
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}
	
	@Column(nullable=false)
	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	
	@Column(nullable=false)
	public Boolean getObstacle() {
		return obstacle;
	}

	public void setObstacle(Boolean obstacle) {
		this.obstacle = obstacle;
	}
}
