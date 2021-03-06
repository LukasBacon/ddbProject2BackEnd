package com.lukas.ddbProject2BackEnd.clingo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Trieda sa stara o spustenie logickeho programu na vstupnom subore pomocou clingo.exe.
 * 
 * @author lukas
 *
 */
public class Clingo {
	
	/**
	 * Cesta ku clingo.exe.
	 */
	private final String pathToClingoExecutable = "/usr/local/Cellar/clingo/5.3.0/bin/clingo";
	
	/**
	 * Vystupne riadky logickeho programu.
	 */
	private String[] outputLines;
	
	public Clingo() { }
	
	/**
	 * Spusti clingo na vstupnom subore <b>inputFile</b> a vystupne riadky da do <b>outputLines</b>.
	 * @param inputFile
	 */
	public void run(File inputFile) {
		String output = runClingoExecutable(inputFile.getAbsolutePath());
		outputLines = output.split("\n");
	}
	
	/**
	 * @return Vrati subor, do ktoreho sa bude zadavat logicky program.
	 */
	public File getInputFile() {
		return new File("input.txt");
	}

	/**
	 * @return Z vystupu vrati zoznam literalov.
	 */
	public List<String> getResultLiterals() {
		String outputLiteralsString = outputLines[4];
		if (StringUtils.strip(outputLiteralsString).isEmpty()) {
			return new ArrayList<>();
		}
		String[] splitedoutputLiterals = outputLiteralsString.split(" ");
		return Arrays.asList(splitedoutputLiterals);
	}

	/**
	 * Spusti clingo na vstupnom subore <b>pathToInputFile</b> a vrati vystupny retazec.
	 * @param pathToInputFile
	 * @return
	 */
	private String runClingoExecutable(String pathToInputFile) {
		try {
			ProcessBuilder processBuilder = new ProcessBuilder(pathToClingoExecutable, pathToInputFile);
			Process process = processBuilder.start();
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
			return IOUtils.toString(stdInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
		
}
