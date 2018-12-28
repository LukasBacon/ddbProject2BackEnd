package com.lukas.ddbProject2BackEnd.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.lukas.ddbProject2BackEnd.controllers.PredmetController;
import com.lukas.ddbProject2BackEnd.entities.Predmet;

/**
 * Trieda obsahuje webservisy pre entitu predmet.
 * 
 * @author lukas
 *
 */
@Path("predmet")
public class PredmetService {

    @GET
    @Path(value = "all") 
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public List<Predmet> all() {
    	return new PredmetController().getAll();
    }
}
