package com.creatingskies.game.model.company;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.creatingskies.game.model.GenericDAO;
import com.creatingskies.game.model.HibernateSessionManager;

public class CompanyDAO extends GenericDAO{

	private static final long serialVersionUID = -1464594627674490498L;

//	@SuppressWarnings("unchecked")
//	public List<Company> findAllCompanies(){
//		return (List<Company>) findAll(Company.class);
//	}
	
	@SuppressWarnings("unchecked")
	public List<Company> findAllCompanies(boolean showArchives){
		Session session = HibernateSessionManager.openSession();
		Criteria c = session.createCriteria(Company.class);
		
		if(!showArchives){
			c.add(Restrictions.eqOrIsNull("archived", Boolean.FALSE));
		}
		
		List<Company> records = c.list();
		session.close();
		return records;
	}
	
	@SuppressWarnings("unchecked")
	public List<Group> findAllGroupsForCompany(Company company,boolean showArchives){
		Session session = openSession();
		try{
			Criteria c = session.createCriteria(Group.class);
			if(!showArchives){
				c.add(Restrictions.eqOrIsNull("archived", Boolean.FALSE));
			}
			List<Group> groups =  c
					.add(Restrictions.eq("company", company))
					.addOrder(Order.asc("idNo"))
					.list();
				return groups;
		}finally{
			session.close();
		}
	}
	
	public Group findGroup(Integer idNo){
		Session session = openSession();
		try{
			Group group = (Group) session.createCriteria(Group.class)
								.add(Restrictions.eq("idNo", idNo))
								.setFetchMode("teams", FetchMode.JOIN)
								.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
								.uniqueResult(); 
			
			return group;
		}finally{
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Team> findAllTeamByCompany(Integer companyIdNo){
		Session session = openSession();
		try{
			return session.createCriteria(Team.class)
					.createAlias("group", "group")
					.createAlias("group.company", "company")
					.add(Restrictions.eq("company.idNo", companyIdNo))
					.list();
		}finally{
			session.close();
		}
	}
	
	public Team findTeam(Integer idNo){
		Session session = openSession();
		try{
			return (Team) session.createCriteria(Team.class)
					.add(Restrictions.eq("idNo", idNo))
					.setFetchMode("players", FetchMode.JOIN)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.uniqueResult();
		}finally{
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Team> findAllTeamsForGroup(Group group){
		return (List<Team>) findAll(Team.class, Restrictions.eq("group", group));
	}
	
	@SuppressWarnings("unchecked")
	public List<Player> findAllPlayersForTeam(Team team){
		return (List<Player>) findAll(Player.class, Restrictions.eq("team", team));
	}
}
