package com.lukas.ddbProject2BackEnd.clingo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.lukas.ddbProject2BackEnd.ParseUtils;
import com.lukas.ddbProject2BackEnd.controllers.PredmetController;
import com.lukas.ddbProject2BackEnd.entities.Predmet;

/**
 * Trieda sa stara o overenie, ci zvolene predmety splnaju pravidla studijneho programu.
 * 
 * @author lukas
 *
 */
public class ClingoChecker {
	
	/**
	 * Parametre pravidiel.
	 */
	private final int SUM_OF_CREDITS_TOTAL = 150;
	private final int SUM_OF_CREDITS_B = 18;
	
	/**
	 * Pravidla.
	 */
	private String[] rules = {
			"pocet_kreditov_total(POC) :- POC = #sum{ K, ID : zvoleny_predmet(ID, _, K)}.",
			"dostatocny_pocet_kreditov_total :- pocet_kreditov_total(POC), POC >= " + SUM_OF_CREDITS_TOTAL + ".",
			"pocet_kreditov_b(POC) :- POC = #sum{ K, ID : zvoleny_predmet(ID, \"B\", K)}.",
			"dostatocny_pocet_kreditov_b :- pocet_kreditov_b(POC), POC >= " + SUM_OF_CREDITS_B + ".",
			"chyba_ackovy :- predmet(ID, \"A\", _), not zvoleny_predmet(ID, _, _). ",
			"existuje_predmet_bez_podmienujuceho :- zvoleny_predmet(ID, _, _), predmet_podm_predmet(ID, PID), not zvoleny_predmet(PID, _, _).",
			"existuje_predmet_s_vylucujucim :- zvoleny_predmet(ID, _, _), predmet_vyluc_predmet(ID, VID), zvoleny_predmet(VID, _, _).",
			"ok :- dostatocny_pocet_kreditov_total, dostatocny_pocet_kreditov_b, not chyba_ackovy, "
				+ "not existuje_predmet_bez_podmienujuceho, not existuje_predmet_s_vylucujucim.",
			"#show ok/0."
	};
	
	/**
	 * Hlavna metoda triedy.
	 * @param zvolenePredmetyIdsString Zoznam idciek zvolenych predmetov.
	 * @return Vrati true, ak predmety splnaju pravidla.
	 */
	public boolean getAnswer(String zvolenePredmetyIdsString) {
		List<Integer> zvolenePredmetyIds = ParseUtils.getListOfIntsFromString(zvolenePredmetyIdsString);
		List<Predmet> zvolenePredmety = new PredmetController().getAllWithIds(zvolenePredmetyIds);
		Clingo clingo = new Clingo();
		File inputFile = clingo.getInputFile();
		fillInputFile(inputFile, zvolenePredmety);
		clingo.run(inputFile);
		List<String> resultLiterals = clingo.getResultLiterals();		
		return isOk(resultLiterals);
	}

	/**
	 * Naplni vstupny subor <b>inputFile</b>.
	 * @param inputFile	
	 * @param zvolenePredmety
	 */
	private void fillInputFile(File inputFile, List<Predmet> zvolenePredmety) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(inputFile.getAbsolutePath(), "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		writeData(writer, zvolenePredmety);
		writeRules(writer);
		writer.close();		
	}

	/**
	 * Do <b>writera</b> zapise data ako zvolene predmety, ich podmienujuce a vylucujuce predmety.
	 * @param writer
	 * @param zvolenePredmety
	 */
	private void writeData(PrintWriter writer, List<Predmet> zvolenePredmety) {
		for (Predmet predmet : new PredmetController().getAll()) {
			writer.println(predmet.toCheckerLiteral("predmet"));
		}
		for (Predmet predmet : zvolenePredmety) {
			writer.println(predmet.toCheckerLiteral("zvoleny_predmet"));
			for (Predmet podmPredmet : predmet.getPodmienujucePredmety()) {
				writer.println("predmet_podm_predmet(" + predmet.getId() + ", " + podmPredmet.getId() + ").");
			}
			for (Predmet vylucPredmet : predmet.getVylucujucePredmety()) {
				writer.println("predmet_vyluc_predmet(" + predmet.getId() + ", " + vylucPredmet.getId() + ").");
			}
		}
	}
	
	/**
	 * Do <b>writera</b> zapise pravidla logickeho programu.
	 * @param writer
	 */
	private void writeRules(PrintWriter writer) {
		for (String rule : rules) {
			writer.println(rule);
		}	
	}
	
	/**
	 * 
	 * @param literals
	 * @return Vrati true, ak zoznam literalov <b>literals</b> obsahuje retazec ok.
	 */
	private boolean isOk(List<String> literals) {
		for (String string : literals) {
			if (string.equals("ok")) {
				return true;
			}
		}
		return false;
	}
	
}
