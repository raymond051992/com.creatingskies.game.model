package com.creatingskies.game.core;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.creatingskies.game.core.Tile.Orientation;
import com.creatingskies.game.model.IAuditRecord;

@Entity(name="gGame")
public class Game implements IAuditRecord{

	private static final long serialVersionUID = 3076486234883193667L;

	public enum Type {
		ROWING,CYCLING
	}
	
	private Integer idNo;
	private String title;
	private String description;
	private Type type;
	private List<GameWeather> weathers;
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
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(nullable=false)
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="game",targetEntity=GameWeather.class,orphanRemoval=true)
	public List<GameWeather> getWeathers() {
		return weathers;
	}
	
	public void setWeathers(List<GameWeather> weathers) {
		this.weathers = weathers;
	}
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="game",targetEntity=Tile.class,orphanRemoval=true)
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
	public List<Tile> getHorizontalTiles(){
		if(tiles != null && !tiles.isEmpty()){
			return tiles.stream()
					.filter(t -> t.getOrientation() == Orientation.HORIZONTAL)
					.collect(Collectors.toList());
		}
		return null;
	}
	
	@Transient
	public List<Tile> getVerticalTiles(){
		if(tiles != null && !tiles.isEmpty()){
			return tiles.stream()
					.filter(t -> t.getOrientation() == Orientation.VERTICAL)
					.collect(Collectors.toList());
		}
		return null;
	}
}
