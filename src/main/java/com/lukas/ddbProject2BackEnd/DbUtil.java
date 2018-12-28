package com.lukas.ddbProject2BackEnd;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Singleton, ktory si drzi spojenie s databazou.
 * 
 * @author lukas
 *
 */
public class DbUtil {
	
	private static final SessionFactory sessionFactory;

	static {
	    try {
	        sessionFactory = new Configuration().configure().buildSessionFactory();
	    } catch (Throwable ex) {
	        System.err.println("Initial SessionFactory creation failed." + ex);
	        throw new ExceptionInInitializerError(ex);
	    }
	}

	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
}
