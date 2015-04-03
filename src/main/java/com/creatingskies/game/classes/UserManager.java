package com.creatingskies.game.classes;

import com.creatingskies.game.model.user.User;


public class UserManager {

	private User currentUser;

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
}
