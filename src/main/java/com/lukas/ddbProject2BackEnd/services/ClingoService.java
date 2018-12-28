package com.lukas.ddbProject2BackEnd.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.lukas.ddbProject2BackEnd.clingo.ClingoChecker;
import com.lukas.ddbProject2BackEnd.clingo.ClingoFilter;
import com.lukas.ddbProject2BackEnd.entities.Predmet;

/**
 * Trieda obsahuje webservisy pre clingo funkcie - filtrovanie predmetov podla preferencii a kontrola, ci predmety spnaju pravidla.
 * 
 * @author lukas
 *
 */
@Path(value = "clingo")
public class ClingoService {
	
    @POST
    @Path(value = "filter") 
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public List<Predmet> filter(String preferences) {
    	return new ClingoFilter().getPredmety(preferences);
    }
    
    @POST
    @Path(value = "check")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public boolean check(String selectedPredmetyIds) {
    	return new ClingoChecker().getAnswer(selectedPredmetyIds);
    }
}
