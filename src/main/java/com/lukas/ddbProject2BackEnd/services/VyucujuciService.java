package com.lukas.ddbProject2BackEnd.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.lukas.ddbProject2BackEnd.controllers.VyucujuciController;
import com.lukas.ddbProject2BackEnd.entities.Vyucujuci;

/**
 * Trieda obsahuje webservisy pre entitu vyucujuci.
 * 
 * @author lukas
 *
 */
@Path(value = "vyucujuci")
public class VyucujuciService {

    @GET
    @Path(value = "all") 
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public List<Vyucujuci> all() {
    	return new VyucujuciController().getAll();
    }
}
