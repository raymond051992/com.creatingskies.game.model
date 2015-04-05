package com.creatingskies.game.model.user;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import com.creatingskies.game.model.GenericDAO;
import com.creatingskies.game.model.user.User.Status;

public class UserDao extends GenericDAO{

	private static final long serialVersionUID = -618180944858765016L;
	
	@SuppressWarnings("unchecked")
	public List<User> findAll(){
		return (List<User>) findAll(User.class);
	}
	
	public User findUser(String username){
		return (User) find(User.class,
							Restrictions.eq("username", username));
	}
	
	public User findActiveUser(String username){
		return (User) find(User.class,
							Restrictions.eq("username", username),
							Restrictions.eq("status", Status.ACTIVE));
	}
	
	public SecurityQuestion findSecurityQuestion(String code){
		return (SecurityQuestion) find(SecurityQuestion.class,
										Restrictions.eq("code", code));
	}
	
	@SuppressWarnings("unchecked")
	public List<SecurityQuestion> findAllSecurityQuestions(){
		return (List<SecurityQuestion>) findAll(SecurityQuestion.class);
	}

}
