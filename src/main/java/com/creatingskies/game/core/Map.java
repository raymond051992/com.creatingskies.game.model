package com.creatingskies.game.core;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.creatingskies.game.model.IRecord;

@Entity(name="gMap")
public class Map implements IRecord{

	private static final long serialVersionUID = 1019227672929433839L;
	
	private Integer idNo;
	private Integer width;
	private Integer height;
	
	private TileImage defaultTileImage;
	private List<Tile> tiles;
	private List<MapWeather> weathers;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getIdNo(){
		return idNo;
	}
	
	public void setIdNo(Integer idNo) {
		this.idNo = idNo;
	}
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="map",targetEntity=MapWeather.class,orphanRemoval=true)
	public List<MapWeather> getWeathers() {
		return weathers;
	}
	
	public void setWeathers(List<MapWeather> weathers) {
		this.weathers = weathers;
	}
	
	@Column(nullable=false)
	public Integer getWidth() {
		return width;
	}
	
	public void setWidth(Integer width) {
		this.width = width;
	}
	
	@Column(nullable=false)
	public Integer getHeight() {
		return height;
	}
	
	public void setHeight(Integer height) {
		this.height = height;
	}
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="map",targetEntity=Tile.class,orphanRemoval=true)
	public List<Tile> getTiles() {
		return tiles;
	}
	
	public void setTiles(List<Tile> tiles) {
		this.tiles = tiles;
	}
	
	@OneToOne(targetEntity = TileImage.class)
	@JoinColumn(name = "defaultTileImageIdNo", nullable = false)
	public TileImage getDefaultTileImage() {
		return defaultTileImage;
	}

	public void setDefaultTileImage(TileImage defaultTileImage) {
		this.defaultTileImage = defaultTileImage;
	}
	
	@Transient
	public Tile getStartPoint(){
		if(getTiles() != null){
			return getTiles().stream()
					.filter(t -> t.getStartPoint() == true)
					.findFirst().orElse(null);
		}
		return null;
	}
	
	@Transient
	public Tile getEndPoint(){
		if(getTiles() != null){
			return getTiles().stream()
					.filter(t -> t.getEndPoint() == true)
					.findFirst().orElse(null);
		}
		return null;
	}
	
	@Transient
	public Boolean isReady(){
		if(getStartPoint() == null){
			return false;
		}
		if(getEndPoint() == null){
			return false;
		}
		return true;
	}

}
