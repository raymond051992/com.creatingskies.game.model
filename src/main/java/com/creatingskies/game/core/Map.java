package com.creatingskies.game.core;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.creatingskies.game.model.IAuditRecord;

@Entity(name="gMap")
public class Map implements IAuditRecord{

	private static final long serialVersionUID = 1019227672929433839L;
	
	private Integer idNo;
	private String name;
	private String description;
	private List<MapWeather> weathers;
	private Integer width;
	private Integer height;
	private List<Tile> tiles;
	
	private String entryBy;
	private Date entryDate;
	private String editBy;
	private Date editDate;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getIdNo(){
		return idNo;
	}
	
	public void setIdNo(Integer idNo) {
		this.idNo = idNo;
	}
	
	@Column(nullable=false)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(nullable=false)
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
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
	
	@Column(nullable=false)
	public String getEntryBy() {
		return entryBy;
	}
	
	public void setEntryBy(String entryBy) {
		this.entryBy = entryBy;
	}
	
	@Column(nullable=false)
	public Date getEntryDate() {
		return entryDate;
	}
	
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	
	public String getEditBy() {
		return editBy;
	}
	
	public void setEditBy(String editBy) {
		this.editBy = editBy;
	}
	
	public Date getEditDate() {
		return editDate;
	}
	
	public void setEditDate(Date editDate) {
		this.editDate = editDate;
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
		if(name == null || (name != null && name.isEmpty())){
			return false;
		}
		if(description == null || (description != null && description.isEmpty())){
			return false;
		}
		if(width < 1){
			return false;
		}
		if(height < 1){
			return false;
		}
		
		if(tiles == null || (tiles != null && tiles.isEmpty())){
			return false;
		}
		if(getStartPoint() == null){
			return false;
		}
		if(getEndPoint() == null){
			return false;
		}
		return true;
	}
}
