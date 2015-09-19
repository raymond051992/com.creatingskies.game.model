package com.creatingskies.game.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.creatingskies.game.model.IRecord;
import com.creatingskies.game.model.obstacle.Obstacle;

@Entity(name="gTile")
public class Tile implements IRecord{

	private static final long serialVersionUID = 8791664261827955489L;
	
	private Integer idNo;
	private Map map;
	private Integer colIndex;
	private Integer rowIndex;
	
	private TileImage backImage;
	private TileImage frontImage;
	private Obstacle obstacle;
	
	private Boolean startPoint = false;
	private Boolean endPoint = false;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getIdNo() {
		return idNo;
	}
	
	@JoinColumn(name="mapIdNo",nullable=false)
	@ManyToOne(targetEntity=Map.class)
	public Map getMap() {
		return map;
	}
	
	public void setMap(Map map) {
		this.map = map;
	}
	
	public void setIdNo(Integer idNo) {
		this.idNo = idNo;
	}

	@Column(nullable=false)
	public Integer getColIndex() {
		return colIndex;
	}
	
	public void setColIndex(Integer colIndex) {
		this.colIndex = colIndex;
	}
	
	@Column(nullable=false)
	public Integer getRowIndex() {
		return rowIndex;
	}
	
	public void setRowIndex(Integer rowIndex) {
		this.rowIndex = rowIndex;
	}
	
	@Column(nullable=false)
	public Boolean getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Boolean startPoint) {
		this.startPoint = startPoint;
	}

	@Column(nullable=false)
	public Boolean getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Boolean endPoint) {
		this.endPoint = endPoint;
	}

	@ManyToOne(targetEntity = Obstacle.class)
	@JoinColumn(name = "obstacleIdNo")
	public Obstacle getObstacle() {
		return obstacle;
	}

	public void setObstacle(Obstacle obstacle) {
		this.obstacle = obstacle;
	}

	@ManyToOne(targetEntity = TileImage.class)
	@JoinColumn(name = "backTileImageIdNo")
	public TileImage getBackImage() {
		return backImage;
	}

	public void setBackImage(TileImage backImage) {
		this.backImage = backImage;
	}

	@ManyToOne(targetEntity = TileImage.class)
	@JoinColumn(name = "frontTileImageIdNo")
	public TileImage getFrontImage() {
		return frontImage;
	}

	public void setFrontImage(TileImage frontImage) {
		this.frontImage = frontImage;
	}
	
}
