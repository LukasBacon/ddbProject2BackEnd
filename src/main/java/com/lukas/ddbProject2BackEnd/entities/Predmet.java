package com.lukas.ddbProject2BackEnd.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "predmety")
public class Predmet{

	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "typ")
	private String typ; 
	
	@Column(name = "kod")
	private String kod;
	
	@Column(name = "nazov")
	private String nazov;
	
	@Column(name = "kredit")
	private int kredit;
	
	@Column(name = "celkove_hodnotenie")
	private Double celkoveHodnotenie;
	
	@Column(name = "narocnost")
	private Double narocnost;
	
	@Column(name = "zaujimavost")
	private Double zaujimavost;
	
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "vyucuje", 
        joinColumns = { @JoinColumn(name = "predmet_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "vyucujuci_id") }
    )
	private Set<Vyucujuci> vyucujuci = new HashSet<>();
	
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "ma_technologiu", 
        joinColumns = { @JoinColumn(name = "predmet_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "technologia_id") }
    )
	private Set<Technologia> technologie = new HashSet<>();
    
	
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "ma_tematicky_okruh", 
        joinColumns = { @JoinColumn(name = "predmet_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "okruh_id") }
    )
	private Set<TematickyOkruh> tematickeOkruhy = new HashSet<>();
		
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "ma_podmienujuci_predmet", 
        joinColumns = { @JoinColumn(name = "predmet_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "podm_predmet_id") }
    )
	private Set<Predmet> podmienujucePredmety = new HashSet<>();
    
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "ma_vylucujuci_predmet", 
        joinColumns = { @JoinColumn(name = "predmet_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "vyluc_predmet_id") }
    )
	private Set<Predmet> vylucujucePredmety = new HashSet<>();

	public int getId() {
		return id;
	}

	public String getTyp() {
		return typ;
	}

	public String getKod() {
		return kod;
	}

	public String getNazov() {
		return nazov;
	}

	public int getKredit() {
		return kredit;
	}

	public Double getCelkoveHodnotenie() {
		return celkoveHodnotenie;
	}

	public Double getNarocnost() {
		return narocnost;
	}

	public Double getZaujimavost() {
		return zaujimavost;
	}

	public Set<Vyucujuci> getVyucujuci() {
		return vyucujuci;
	}

	public Set<Technologia> getTechnologie() {
		return technologie;
	}

	public Set<TematickyOkruh> getTematickeOkruhy() {
		return tematickeOkruhy;
	}

	public Set<Predmet> getPodmienujucePredmety() {
		return podmienujucePredmety;
	}

	public Set<Predmet> getVylucujucePredmety() {
		return vylucujucePredmety;
	}
    
}
