package com.lukas.ddbProject2BackEnd.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.lukas.ddbProject2BackEnd.controllers.TematickyOkruhController;
import com.lukas.ddbProject2BackEnd.entities.TematickyOkruh;

/**
 * Trieda obsahuje webservisy pre entitu tematicky okruh.
 * 
 * @author lukas
 *
 */
@Path(value = "tematicky_okruh")
public class TematickyOkruhService {

    @GET
    @Path(value = "all") 
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public List<TematickyOkruh> all() {
    	return new TematickyOkruhController().getAll();
    }
}
