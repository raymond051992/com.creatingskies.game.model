package com.creatingskies.game.common;

import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TableRowViewButton extends Button{
	
	private static final Image image = 
			new Image(MainLayout.class.getResourceAsStream("/images/search_ffffff_32.png"),16,16,true,true);
	
	public TableRowViewButton() {
		super("a",new ImageView(image));
		getStyleClass().add("table-row-button");
		setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
	}
}
