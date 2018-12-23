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

public class Clingo {
	
	private final String pathToClingoExecutable = "/usr/local/Cellar/clingo/5.3.0/bin/clingo";
	private String[] outputLines;
	
	public Clingo() { }
	
	public void run(File inputFile) {
		String output = runClingoExecutable(inputFile.getAbsolutePath());
		outputLines = output.split("\n");
	}
	
	public File getInputFile() {
		return new File("input.txt");
	}

	public List<String> getResultLiterals() {
		String outputLiteralsString = outputLines[4];
		if (StringUtils.strip(outputLiteralsString).isEmpty()) {
			return new ArrayList<>();
		}
		String[] splitedoutputLiterals = outputLiteralsString.split(" ");
		return Arrays.asList(splitedoutputLiterals);
	}

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
