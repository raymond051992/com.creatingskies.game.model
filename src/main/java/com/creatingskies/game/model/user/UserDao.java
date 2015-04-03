package com.creatingskies.game.model.user;

import org.hibernate.criterion.Restrictions;

import com.creatingskies.game.model.GenericDAO;

public class UserDao extends GenericDAO{

	private static final long serialVersionUID = -618180944858765016L;
	
	public User findUser(String username){
		return (User) find(User.class,
							Restrictions.eq("username", username));
	}
	
	public SecurityQuestion findSecurityQuestion(String code){
		return (SecurityQuestion) find(SecurityQuestion.class,
										Restrictions.eq("code", code));
	}

}
