package com.creatingskies.game.classes;

import java.io.IOException;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import com.creatingskies.game.common.MainLayout;

public abstract class ViewController {
	
	public ViewController() {
		((Label)MainLayout.getPrimaryStage()
				.getScene().lookup("#viewTitle")).setText("");
	}
	
	public void initialize(){
		((Label)MainLayout.getPrimaryStage()
				.getScene().lookup("#viewTitle")).setText(getViewTitle());
		
		Button logoutButton = (Button)MainLayout.getPrimaryStage()
				.getScene().lookup("#logoutButton");
		
		Button backToMainButton = (Button)MainLayout.getPrimaryStage()
				.getScene().lookup("#backToMainButton");
		
		try {
			Class<?> mainController = Class.forName("com.creatingskies.game.main.MainController");
			Class<?> loginController = Class.forName("com.creatingskies.game.login.LoginController");
			
			logoutButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	UserManager.setCurrentUser(null);
	            	MainLayout.getRootLayout().getChildren().stream()
	            	.filter(c -> c instanceof Pane)
	            	.collect(Collectors.toList())
	            	.clear();
	            	
					try {
						FXMLLoader loader = new FXMLLoader();
		                loader.setLocation(loginController.getResource("Login.fxml"));
		                AnchorPane login = (AnchorPane) loader.load();
						MainLayout.getRootLayout().setCenter(login);
					} catch (IOException e) {
						e.printStackTrace();
					}
	            }
	        });
			
			
			
			backToMainButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	MainLayout.getRootLayout().getChildren().stream()
	            	.filter(c -> c instanceof Pane)
	            	.collect(Collectors.toList())
	            	.clear();
	            	
					try {
						FXMLLoader loader = new FXMLLoader();
		                loader.setLocation(mainController.getResource("Main.fxml"));
		                AnchorPane main = (AnchorPane) loader.load();
						MainLayout.getRootLayout().setCenter(main);
					} catch (IOException e) {
						e.printStackTrace();
					}
	            }
	        });
			
			if(UserManager.getCurrentUser() == null){
				logoutButton.setVisible(false);
			}else{
				logoutButton.setVisible(true);
			}
			
			if(getClass() == mainController || getClass() == loginController){
				backToMainButton.setVisible(false);
			}else{
				backToMainButton.setVisible(true);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	protected abstract String getViewTitle();
}
