package com.lukas.ddbProject2BackEnd.entities;

import java.util.List;

/**
 * Trieda reprezentuje preferencie, ktore si uzivatel navolil pri filtrovani.
 * 
 * @author lukas
 *
 */
public class Preferences {
	
	List<Vyucujuci> vyucujuci;
	List<Technologia> technologie;
	List<TematickyOkruh> tematickeOkruhy;
	Integer celkoveHodnotenie;
	Integer narocnost;
	Integer zaujimavost;
	
	public void setVyucujuci(List<Vyucujuci> vyucujuci) {
		this.vyucujuci = vyucujuci;
	}
	
	public void setTechnologie(List<Technologia> technologie) {
		this.technologie = technologie;
	}
	
	public void setTematickeOkruhy(List<TematickyOkruh> tematickeOkruhy) {
		this.tematickeOkruhy = tematickeOkruhy;
	}
	
	public void setCelkoveHodnotenie(Integer celkoveHodnotenie) {
		this.celkoveHodnotenie = celkoveHodnotenie;
	}
	
	public void setNarocnost(Integer narocnost) {
		this.narocnost = narocnost;
	}
	
	public void setZaujimavost(Integer zaujimavost) {
		this.zaujimavost = zaujimavost;
	}

	public List<Vyucujuci> getVyucujuci() {
		return vyucujuci;
	}

	public List<Technologia> getTechnologie() {
		return technologie;
	}

	public List<TematickyOkruh> getTematickeOkruhy() {
		return tematickeOkruhy;
	}

	public Integer getCelkoveHodnotenie() {
		return celkoveHodnotenie;
	}

	public Integer getNarocnost() {
		return narocnost;
	}

	public Integer getZaujimavost() {
		return zaujimavost;
	}
}
