package com.creatingskies.game.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.creatingskies.game.classes.UserManager;

@Transactional
public abstract class GenericDAO implements Serializable{

	private static final long serialVersionUID = 8959532670136780528L;
	
	public Session openSession(){
		return HibernateSessionManager.openSession(); 
	}
	
	@SuppressWarnings("unchecked")
	public List<? extends IRecord> findAll(Class<? extends IRecord> recordClass){
		Session session = HibernateSessionManager.openSession();
		List<IRecord> records = session.createCriteria(recordClass).list();
		session.close();
		return records;
	}
	
	@SuppressWarnings("unchecked")
	public List<? extends IRecord> findAll(
			Class<? extends IRecord> recordClass, Criterion... criterion) {
		Session session = HibernateSessionManager.openSession();
		Criteria criteria = session.createCriteria(recordClass);
		if(criterion != null){
			for(Criterion c : criterion){
				criteria.add(c);
			}
		}
		List<IRecord> records = criteria.list();
		session.close();
		return records;
	}
	
	public IRecord find(Class<? extends IRecord> recordClass,Integer IdNo){
		Session session = HibernateSessionManager.openSession();
		IRecord record = (IRecord) session.createCriteria(recordClass)
							.add(Restrictions.eq("idNo", IdNo))
							.uniqueResult();
		session.close();
		return record;
	}
	
	public IRecord find(Class<? extends IRecord> recordClass,Criterion... criterion){
		Session session = HibernateSessionManager.openSession();
		Criteria criteria = session.createCriteria(recordClass);
		if(criterion != null){
			for(Criterion c : criterion){
				criteria.add(c);
			}
		}
		IRecord record = (IRecord) criteria.uniqueResult();
		session.close();
		return record;
	}
	
	public void save(IRecord record){
		record = setAuditDetail(record);
		Session session = HibernateSessionManager.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(record);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			session.close();
		}
	}
	
	public void saveOrUpdate(IRecord record){
		record = setAuditDetail(record);
		Session session = HibernateSessionManager.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(record);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void delete(IRecord record) throws Exception {
		Session session = HibernateSessionManager.openSession();
		Transaction tx = null;
		
		try{
			IRecord fetchedRecord = find(record.getClass(), record.getIdNo());
			
			if(fetchedRecord == null){
				throw new Exception("Unable to delete. No records found with idNo = " + record.getIdNo());
			}

			tx = session.beginTransaction();
			session.delete(fetchedRecord);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			session.close();
		}
	}
	
	public IRecord setAuditDetail(IRecord record){
		if(record instanceof IAuditRecord){
			if(UserManager.getCurrentUser() != null){
				if(record.getIdNo() == null){
					((IAuditRecord) record).setEntryBy(UserManager.getCurrentUser().getUsername());
					((IAuditRecord) record).setEntryDate(new Date());
				} else {
					((IAuditRecord) record).setEditBy(UserManager.getCurrentUser().getUsername());
					((IAuditRecord) record).setEditDate(new Date());
				}
			}
		}
		return record;
	}
}
