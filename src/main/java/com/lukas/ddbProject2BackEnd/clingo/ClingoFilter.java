package com.lukas.ddbProject2BackEnd.clingo;

import java.io.IOException;
import java.util.List;

import com.lukas.ddbProject2BackEnd.PreferencesFactory;
import com.lukas.ddbProject2BackEnd.entities.Predmet;
import com.lukas.ddbProject2BackEnd.entities.Preferences;

public class ClingoFilter {
	
	private final String pathToClingoExecutable = "/usr/local/Cellar/clingo/5.3.0/bin/clingo";
	String file = "/Users/lukas/auta.lp";

	public List<Predmet> getPredmety(String preferencesString) {
		Preferences preferences = new PreferencesFactory().loadPreferencesFromString(preferencesString);
		try {
			Process process = new ProcessBuilder(pathToClingoExecutable,"param1").start();
			System.out.println("ok");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
		// https://stackoverflow.com/questions/21782535/running-exe-file-with-arguments-from-java-program-and-sending-output-to-text-fil
		// http://commons.apache.org/proper/commons-exec/tutorial.html
	}
	
	/**
	 * Tu si len nachystam ten subor a z vysledku ziskam result.
	 * Spravim triedu Clingo a vnej sa bude clingo spustat
	 */
}
