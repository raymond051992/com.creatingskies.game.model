package com.creatingskies.game.core;

import javafx.util.StringConverter;

public class GameConverter extends StringConverter<Game>{

	@Override
	public String toString(Game object) {
		if(object == null){
			return null;
		}else{
			return object.getTitle();
		}
	}

	@Override
	public Game fromString(String string) {
		return null;
	}

}
