package com.creatingskies.game.model.company;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.creatingskies.game.model.IRecord;

@Entity(name="gPlayer")
public class Player implements IRecord{

	private static final long serialVersionUID = -7553940115714433813L;

	private Integer idNo;
	private String name;
	private Team team;
	
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
	
	@JoinColumn(name="teamIdNo",nullable=false)
	@ManyToOne(targetEntity=Team.class)
	public Team getTeam() {
		return team;
	}
	
	public void setTeam(Team team) {
		this.team = team;
	}
}
