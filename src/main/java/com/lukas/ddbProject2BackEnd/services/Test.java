package com.lukas.ddbProject2BackEnd.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.lukas.ddbProject2BackEnd.entities.Predmet;
import com.lukas.ddbProject2BackEnd.entities.Technologia;
import com.lukas.ddbProject2BackEnd.entities.TematickyOkruh;
import com.lukas.ddbProject2BackEnd.entities.Vyucujuci;

@Path("test")
public class Test {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    
    @GET
    @Path(value = "1") 
    @Produces(MediaType.TEXT_PLAIN + ";charset=utf-8")
    public String getFirstPredmetName() {
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		String hql = "FROM Predmet WHERE id = 104229" ;
		Query<?> query = session.createQuery(hql);
		List<?> results = query.list();
		Predmet predmet = (Predmet) results.get(0);
		
		String result = "Nazov = " + predmet.getNazov();
		for (Predmet podmPredmet : predmet.getPodmienujucePredmety()) {
			result += " \n " + podmPredmet.getNazov();
		}
		for (Predmet vylucPredmet : predmet.getVylucujucePredmety()) {
			result += " \n " + vylucPredmet.getNazov();
		}
		for (Vyucujuci vyucujuci : predmet.getVyucujuci()) {
			result += " \n " + vyucujuci.getMeno();
		}
		for (Technologia technologia : predmet.getTechnologie()) {
			result += " \n " + technologia.getNazov();
		}
		for (TematickyOkruh okruh : predmet.getTematickeOkruhy()) {
			result += " \n " + okruh.getNazov();
		}
 
		session.getTransaction().commit();
		session.close();
		return result;
    }
}
