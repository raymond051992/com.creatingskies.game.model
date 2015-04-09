package com.creatingskies.game.model.event;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.creatingskies.game.core.Game;
import com.creatingskies.game.model.IAuditRecord;
import com.creatingskies.game.model.company.Company;

@Entity(name="gGameEvent")
public class GameEvent implements IAuditRecord{

	private static final long serialVersionUID = -6724495867688309904L;
	private Integer idNo;
	private Company company;
	private Game game;
	private Date eventDate;
	
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

	@JoinColumn(name="companyIdNo",nullable=false)
	@ManyToOne(targetEntity=Company.class)
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@JoinColumn(name="gameIdNo",nullable=false)
	@ManyToOne(targetEntity=Game.class)
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
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
