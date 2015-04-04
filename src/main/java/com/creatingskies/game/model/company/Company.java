package com.creatingskies.game.model.company;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.creatingskies.game.model.IAuditRecord;

@Entity(name="gCompany")
public class Company implements IAuditRecord{

	private static final long serialVersionUID = -3366937978920317888L;
	
	private Integer idNo;
	private String name;
	
	private String entryBy;
	private Date entryDate;
	private String editBy;
	private Date editDate;
	
	private List<Group> groups = new ArrayList<Group>();
	
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
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="company",targetEntity=Group.class,orphanRemoval=true)
	public List<Group> getGroups() {
		return groups;
	}
	
	public void setGroups(List<Group> groups) {
		this.groups = groups;
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
