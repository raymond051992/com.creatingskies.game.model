package com.creatingskies.game.classes;

import com.creatingskies.game.model.user.User;


public class UserManager {

	private static User currentUser;
	
	private UserManager(){}

	public static User getCurrentUser() {
		return currentUser;
	}

	public static void setCurrentUser(User user) {
		currentUser =  user;
	}
}
