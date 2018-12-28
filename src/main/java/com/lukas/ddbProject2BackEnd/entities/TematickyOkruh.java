package com.lukas.ddbProject2BackEnd.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Trieda reprezentuje tematicky okruh. Je namapovana na tabulku tematicke_okruhy v databaze.
 * @author lukas
 *
 */
@Entity
@Table(name = "tematicke_okruhy")
public class TematickyOkruh {
	
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
		return "tematicky_okruh(" + id + ").";
	}  
}
