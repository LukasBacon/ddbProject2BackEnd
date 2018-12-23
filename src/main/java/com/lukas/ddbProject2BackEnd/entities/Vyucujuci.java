package com.lukas.ddbProject2BackEnd.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vyucujuci")
public class Vyucujuci {
	
	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "meno")
	private String meno;

	public int getId() {
		return id;
	}

	public String getMeno() {
		return meno;
	}

	public String toLiteral() {
		return "vyucujuci(" + id + ").";
	} 
}
