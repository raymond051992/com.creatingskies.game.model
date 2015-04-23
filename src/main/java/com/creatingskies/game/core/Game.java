package com.creatingskies.game.core;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.creatingskies.game.model.IAuditRecord;
import com.creatingskies.game.model.weather.Weather;

@Entity(name="gGame")
public class Game implements IAuditRecord{

	private static final long serialVersionUID = 3076486234883193667L;

	public enum Type {
		ROWING, CYCLING
	}
	
	private Integer idNo;
	private String title;
	private String description;
	private Type type;
	private Map map = new Map();
	private Weather weather;
	
	private byte[] audio;
	private String audioFileName;
	private String audioFileType;
	private Long audioFileSize;
	
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
	
	@JoinColumn(name = "mapIdNo")
	@OneToOne(targetEntity = Map.class, cascade = CascadeType.ALL)
	public Map getMap() {
		return map;
	}
	
	public void setMap(Map map) {
		this.map = map;
	}
	
	@JoinColumn(name = "weatherIdNo")
	@ManyToOne(targetEntity = Weather.class)
	public Weather getWeather() {
		return weather;
	}
	
	public void setWeather(Weather weather) {
		this.weather = weather;
	}
	
	@Lob
	public byte[] getAudio() {
		return audio;
	}
	
	public void setAudio(byte[] audio) {
		this.audio = audio;
	}
	
	public String getAudioFileName() {
		return audioFileName;
	}

	public void setAudioFileName(String audioFileName) {
		this.audioFileName = audioFileName;
	}

	public String getAudioFileType() {
		return audioFileType;
	}

	public void setAudioFileType(String audioFileType) {
		this.audioFileType = audioFileType;
	}
	
	public Long getAudioFileSize() {
		return audioFileSize;
	}
	
	public void setAudioFileSize(Long audioFileSize) {
		this.audioFileSize = audioFileSize;
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
}
