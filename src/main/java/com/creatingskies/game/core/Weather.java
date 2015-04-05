package com.creatingskies.game.core;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.creatingskies.game.model.IAuditRecord;

@Entity(name="gWeather")
public class Weather implements IAuditRecord{

	private static final long serialVersionUID = -6133448610082703698L;
	
	private Integer idNo;
	private String name;
	private Boolean forRowing = false;
	private Boolean forCycling = false;
	private Integer difficulty = 0;
	
	private byte[] image;
	private String imageFileName;
	private String imageFileType;
	
	private String entryBy;
	private Date entryDate;
	private String editBy;
	private Date editDate;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getIdNo() {
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
	public Boolean getForRowing() {
		return forRowing;
	}
	
	public void setForRowing(Boolean forRowing) {
		this.forRowing = forRowing;
	}
	
	@Column(nullable=false)
	public Boolean getForCycling() {
		return forCycling;
	}
	
	public void setForCycling(Boolean forCycling) {
		this.forCycling = forCycling;
	}
	
	@Column(nullable=false)
	public Integer getDifficulty() {
		return difficulty;
	}
	
	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
	}
	
	@Lob
	@Column(nullable=false)
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Column(nullable=false)
	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	@Column(nullable=false)
	public String getImageFileType() {
		return imageFileType;
	}

	public void setImageFileType(String imageFileType) {
		this.imageFileType = imageFileType;
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
