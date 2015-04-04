package com.creatingskies.game.model.company;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.creatingskies.game.model.IRecord;

@Entity(name="gTeam")
public class Team implements IRecord{

	private static final long serialVersionUID = 1549378163774242118L;

	private Integer idNo;
	private String name;
	private Group group;
	
	private List<Player> players = new ArrayList<Player>();
	
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
	
	@JoinColumn(name="groupIdNo",nullable=false)
	@ManyToOne(targetEntity=Group.class)
	public Group getGroup() {
		return group;
	}
	
	public void setGroup(Group group) {
		this.group = group;
	}
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="team",targetEntity=Player.class,orphanRemoval=true)
	public List<Player> getPlayers() {
		return players;
	}
	
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
}
