package com.creatingskies.game.model.company;

import java.util.List;

import com.creatingskies.game.model.GenericDAO;

public class CompanyDAO extends GenericDAO{

	private static final long serialVersionUID = -1464594627674490498L;

	@SuppressWarnings("unchecked")
	public List<Company> findAll(){
		return (List<Company>) findAll(Company.class);
	}
	
}
