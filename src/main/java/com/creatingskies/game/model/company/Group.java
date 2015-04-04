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

@Entity(name="gGroup")
public class Group implements IRecord{

	private static final long serialVersionUID = -5148301875452635155L;

	private Integer idNo;
	private String name;
	private Company company;
	
	private List<Team> teams = new ArrayList<Team>();
	
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
	
	@JoinColumn(name="companyIdNo",nullable=false)
	@ManyToOne(targetEntity=Company.class)
	public Company getCompany() {
		return company;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="group",targetEntity=Team.class,orphanRemoval=true)
	public List<Team> getTeams() {
		return teams;
	}
	
	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
}
