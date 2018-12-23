package com.lukas.ddbProject2BackEnd.controllers;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.lukas.ddbProject2BackEnd.DbUtil;

public abstract class ControllerBase<T> {
	
	final Class<T> typeClass;
	
	public ControllerBase(Class<T> typeClass) {
        this.typeClass = typeClass;
    }

	@SuppressWarnings("unchecked")
	public List<T> getAll(){
		SessionFactory sessionFactory = DbUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		String tableName = typeClass.getName();
		String hql = "FROM " + tableName ;
		Query<?> query = session.createQuery(hql);
		List<T> result = (List<T>) query.list();
		session.getTransaction().commit();
		session.close();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAllWithIds(List<Integer> ids) {
		SessionFactory sessionFactory = DbUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		String tableName = typeClass.getName();
		List<T> result = new ArrayList<>();
		for (Integer id : ids) {
			String hql = "FROM " + tableName + " WHERE id = " + id ;
			Query<?> query = session.createQuery(hql);
			T res = (T) query.uniqueResult();
			result.add(res);
		}
		session.getTransaction().commit();
		session.close();
		return result;
	}
}
