package com.creatingskies.game.model.company;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import com.creatingskies.game.model.GenericDAO;

public class CompanyDAO extends GenericDAO{

	private static final long serialVersionUID = -1464594627674490498L;

	@SuppressWarnings("unchecked")
	public List<Company> findAllCompanies(){
		return (List<Company>) findAll(Company.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Group> findAllGroupsForCompany(Company company){
		return (List<Group>) findAll(Group.class, Restrictions.eq("company", company));
	}
	
}
