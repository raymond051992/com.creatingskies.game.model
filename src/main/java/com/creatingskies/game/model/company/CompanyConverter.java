package com.creatingskies.game.model.company;

import javafx.util.StringConverter;

public class CompanyConverter extends StringConverter<Company>{

	@Override
	public String toString(Company object) {
		if(object == null){
			return null;
		}else{
			return object.getName();
		}
	}

	@Override
	public Company fromString(String string) {
		return null;
	}

}
