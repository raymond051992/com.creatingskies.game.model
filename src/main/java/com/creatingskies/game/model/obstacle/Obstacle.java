package com.creatingskies.game.model.obstacle;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.creatingskies.game.core.Game.Type;
import com.creatingskies.game.model.IAuditRecord;

@Entity(name = "gObstacle")
public class Obstacle implements IAuditRecord {

	private static final long serialVersionUID = 350816085414876366L;
	
	private Integer idNo;
	private String name;
	
	private Type gameType;
	private Integer difficulty;
	private byte[] icon;
	
	private String entryBy;
	private Date entryDate;
	private String editBy;
	private Date editDate;

	@Id
	@Override
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getIdNo() {
		return idNo;
	}
	
	public void setIdNo(Integer idNo) {
		this.idNo = idNo;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
	}

	public byte[] getIcon() {
		return icon;
	}

	public void setIcon(byte[] icon) {
		this.icon = icon;
	}

	@Override
	@Column(nullable = false)
	public String getEntryBy() {
		return entryBy;
	}

	@Override
	public void setEntryBy(String entryBy) {
		this.entryBy = entryBy;
	}

	@Override
	@Column(nullable = false)
	public Date getEntryDate() {
		return entryDate;
	}

	@Override
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	@Override
	public String getEditBy() {
		return editBy;
	}

	@Override
	public void setEditBy(String editBy) {
		this.editBy = editBy;
	}

	@Override
	public Date getEditDate() {
		return editDate;
	}

	@Override
	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

	@Enumerated(EnumType.STRING)
	public Type getGameType() {
		return gameType;
	}

	public void setGameType(Type gameType) {
		this.gameType = gameType;
	}
	
}
