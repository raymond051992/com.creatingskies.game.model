package com.creatingskies.game.model.user;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.creatingskies.game.model.GenericDAO;
import com.creatingskies.game.model.user.User.Status;
import com.creatingskies.game.model.user.User.Type;

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
	
	@SuppressWarnings("unchecked")
	public List<User> findFilteredUsers(Type type, Status status){
		Session session = openSession();
		try{
			List<Criterion> criterions = new ArrayList<Criterion>();
			if(type != null){
				criterions.add(Restrictions.eq("type", type));
			}
			if(status != null){
				criterions.add(Restrictions.eq("status", status));
			}
			
			
			Criteria criteria = session.createCriteria(User.class);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			for(Criterion c : criterions){
				criteria.add(c);
			}
			return criteria.list(); 
		}finally{
			session.close();
		}
		
		
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findUsers(Criterion...criterions){
		Session session = openSession();
		try{
			Criteria criteria = session.createCriteria(User.class);
			
			if(criterions != null && criterions.length > 0){
				for(Criterion c : criterions){
					criteria.add(c);
				}
			}
			
			return criteria.list();
		}finally{
			session.clear();
		}
	}

}
