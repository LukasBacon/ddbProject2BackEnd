package com.lukas.ddbProject2BackEnd.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "technologie")
public class Technologia {
	
	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "nazov")
	private String nazov;

	public int getId() {
		return id;
	}

	public String getNazov() {
		return nazov;
	}

	public String toLiteral() {
		return "technologia(" + id + ").";
	} 
}
