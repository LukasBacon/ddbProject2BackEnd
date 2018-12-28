package com.lukas.ddbProject2BackEnd.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.lukas.ddbProject2BackEnd.controllers.TechnologiaController;
import com.lukas.ddbProject2BackEnd.entities.Technologia;

/**
 * Trieda obsahuje webservisy pre entitu technologia.
 * 
 * @author lukas
 *
 */
@Path(value = "technologia")
public class TechnologiaService {

    @GET
    @Path(value = "all") 
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public List<Technologia> all() {
    	return new TechnologiaController().getAll();
    }
}
