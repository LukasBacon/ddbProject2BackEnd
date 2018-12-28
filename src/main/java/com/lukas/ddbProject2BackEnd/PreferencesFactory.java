package com.lukas.ddbProject2BackEnd;

import java.util.List;

import com.lukas.ddbProject2BackEnd.controllers.TechnologiaController;
import com.lukas.ddbProject2BackEnd.controllers.TematickyOkruhController;
import com.lukas.ddbProject2BackEnd.controllers.VyucujuciController;
import com.lukas.ddbProject2BackEnd.entities.Preferences;
import com.lukas.ddbProject2BackEnd.entities.Technologia;
import com.lukas.ddbProject2BackEnd.entities.TematickyOkruh;
import com.lukas.ddbProject2BackEnd.entities.Vyucujuci;

/**
 * Trieda sluzi na vytvorenie objetu triedy Preferences zo stringu pomocou hlavnej metody <b>loadPreferencesFromString</b>.
 * 
 * @author lukas
 *
 */
public class PreferencesFactory {
	
	public PreferencesFactory() { }
	
	/** 
	 * @param preferencesString
	 * @return Vrati objet triedy Preferences, ktory nacita zo stringu.
	 */
	public Preferences loadPreferencesFromString(String preferencesString) {
		Preferences preferences = new Preferences();
		fillVyucujuci(preferences, preferencesString);
		fillTechnologie(preferences, preferencesString);
		fillOkruhy(preferences, preferencesString);
		fillHodnotenie(preferences, preferencesString);
		fillNarocnost(preferences, preferencesString);
		fillZaujimavost(preferences, preferencesString);
		return preferences;
	}
	
	private void fillVyucujuci(Preferences preferences, String preferencesString) {
		List<Integer> ids = ParseUtils.getNthArrayFromStringArray(0, preferencesString);
		List<Vyucujuci> vyucujuci = new VyucujuciController().getAllWithIds(ids);
		preferences.setVyucujuci(vyucujuci);
	}
	
	private void fillTechnologie(Preferences preferences, String preferencesString) {
		List<Integer> ids = ParseUtils.getNthArrayFromStringArray(1, preferencesString);
		List<Technologia> technologie = new TechnologiaController().getAllWithIds(ids);
		preferences.setTechnologie(technologie);
	}
	
	private void fillOkruhy(Preferences preferences, String preferencesString) {
		List<Integer> ids = ParseUtils.getNthArrayFromStringArray(2, preferencesString);
		List<TematickyOkruh> tematickeOkruhy = new TematickyOkruhController().getAllWithIds(ids);
		preferences.setTematickeOkruhy(tematickeOkruhy);
	}
	
	private void fillHodnotenie(Preferences preferences, String preferencesString) {
		preferences.setCelkoveHodnotenie(ParseUtils.getNthNumberFromStringArray(0, preferencesString));
	}
	
	private void fillNarocnost(Preferences preferences, String preferencesString) {
		preferences.setNarocnost(ParseUtils.getNthNumberFromStringArray(1, preferencesString));
	}
	
	private void fillZaujimavost(Preferences preferences, String preferencesString) {
		preferences.setZaujimavost(ParseUtils.getNthNumberFromStringArray(2, preferencesString));
	}
}
