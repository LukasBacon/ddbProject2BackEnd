package com.lukas.ddbProject2BackEnd.clingo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.lukas.ddbProject2BackEnd.PreferencesFactory;
import com.lukas.ddbProject2BackEnd.controllers.PredmetController;
import com.lukas.ddbProject2BackEnd.entities.Predmet;
import com.lukas.ddbProject2BackEnd.entities.Preferences;
import com.lukas.ddbProject2BackEnd.entities.Technologia;
import com.lukas.ddbProject2BackEnd.entities.TematickyOkruh;
import com.lukas.ddbProject2BackEnd.entities.Vyucujuci;

/**
 * Trieda sa stara o vyfiltrovanie predmetov podla vstupnych preferencii.
 * 
 * @author lukas
 *
 */
public class ClingoFilter {
	
	/**
	 * Pravidla.
	 */
	private final String[] rules = {
		"predmet_id(ID) :- predmet(ID, _, _, _).",
		"predmet_s_nechcenym_vyucujucim(PID) :- predmet_id(PID), vyucujuci(VID), predmet_vyucujuci(PID, VID).",
		"predmet_bez_nechceneho_vyucujuceho(PID) :- predmet_id(PID), not predmet_s_nechcenym_vyucujucim(PID).",
		"predmet_bez_chcenej_technologie(PID) :- predmet_id(PID), technologia(TID), not predmet_technologia(PID, TID).",
		"predmet_s_technologiou(PID) :- predmet_id(PID), not predmet_bez_chcenej_technologie(PID).",
		"predmet_bez_chceneho_okruhom(PID) :- predmet_id(PID), tematicky_okruh(TOID), not predmet_tematicky_okruh(PID, TOID).",
		"predmet_s_okruhom(PID) :- predmet_id(PID), not predmet_bez_chceneho_okruhom(PID).",
		"predmet_s_hodnotenim(ID) :- predmet(ID, H1, _, _), celkove_hodnotenie(H2), H1 >= H2.",
		"predmet_s_narocnostou(ID) :- predmet(ID, _, N1, _), narocnost(N2), N1 < N2.",
		"predmet_so_zaujimavostou(ID) :- predmet(ID, _, _, Z1), zaujimavost(Z2), Z1 >= Z2.",
		"predmet_ok(ID) :- predmet_bez_nechceneho_vyucujuceho(ID), predmet_s_technologiou(ID), "
						+ "predmet_s_okruhom(ID), predmet_s_hodnotenim(ID), "
						+ "predmet_s_narocnostou(ID), predmet_so_zaujimavostou(ID).",
		"#show predmet_ok/1."
	};

	/**
	 * 
	 * @param preferencesString
	 * @return Vrati predmety vyhovujuce preferenciam nacitanych z retazca <b>preferencesString</b>.
	 */
	public List<Predmet> getPredmety(String preferencesString) {
		Preferences preferences = new PreferencesFactory().loadPreferencesFromString(preferencesString);
		Clingo clingo = new Clingo();
		File inputFile = clingo.getInputFile();
		fillInputFile(inputFile, preferences);
		clingo.run(inputFile);
		List<String> resultLiterals = clingo.getResultLiterals();
		List<Integer> resultPredmetyIds = parseLiterelsToPredmetyIds(resultLiterals);
		return new PredmetController().getAllWithIds(resultPredmetyIds);
	}

	/**
	 * Naplni vstupny subor.
	 * @param inputFile
	 * @param preferences
	 */
	private void fillInputFile(File inputFile, Preferences preferences) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(inputFile.getAbsolutePath(), "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List<Predmet> predmety = new PredmetController().getAll();
		writeData(writer, predmety, preferences);
		writeRules(writer);
		writer.close();
	}
	
	/**
	 * Do writera zapise data ako predmety z databazy a preferencie.
	 * @param writer
	 * @param predmety
	 * @param preferences
	 */
	private void writeData(PrintWriter writer, List<Predmet> predmety, Preferences preferences) {
		for (Predmet predmet : predmety) {
			writer.println(predmet.toFilterLiteral());
			for (Vyucujuci vyucujuci: predmet.getVyucujuci()) {
				writer.println("predmet_vyucujuci(" + predmet.getId() + ", " + vyucujuci.getId() + ").");
			}
			for (Technologia technologia: predmet.getTechnologie()) {
				writer.println("predmet_technologia(" + predmet.getId() + ", " + technologia.getId() + ").");
			}
			for (TematickyOkruh okruh: predmet.getTematickeOkruhy()) {
				writer.println("predmet_tematicky_okruh(" + predmet.getId() + ", " + okruh.getId() + ").");
			}
		}
		for (Vyucujuci nechcenyVyucujuci : preferences.getVyucujuci()) {
			writer.println(nechcenyVyucujuci.toLiteral());
		}
		for (Technologia technologia : preferences.getTechnologie()) {
			writer.println(technologia.toLiteral());
		}
		for (TematickyOkruh okruh : preferences.getTematickeOkruhy()) {
			writer.println(okruh.toLiteral());
		}
		writer.println("celkove_hodnotenie(" + preferences.getCelkoveHodnotenie() + ").");
		writer.println("narocnost(" + preferences.getNarocnost() + ").");
		writer.println("zaujimavost(" + preferences.getZaujimavost() + ").");
	}
	
	/**
	 * Do writera zapise pravidla.
	 * @param writer
	 */
	private void writeRules(PrintWriter writer) {
		for (String rule : rules) {
			writer.println(rule);
		}	
	}

	/**
	 * @param resultLiterals
	 * @return Z vyslednych literalov <b>resultLiterals</b> preparsuje a vrati idcka predmetov.
	 */
	private List<Integer> parseLiterelsToPredmetyIds(List<String> resultLiterals) {
		List<Integer> result = new ArrayList<>();
		for (String string : resultLiterals) {
			int firstIndex = StringUtils.ordinalIndexOf(string, "(", 1);
			int lastIndex = StringUtils.lastOrdinalIndexOf(string, ")", 1);
			result.add(Integer.parseInt(string.substring(firstIndex+1, lastIndex)));
		}
		return result;
	}
	
}
