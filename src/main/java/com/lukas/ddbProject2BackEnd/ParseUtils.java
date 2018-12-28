package com.lukas.ddbProject2BackEnd;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * Trieda obsahuje funkcie na parsovanie.
 * 
 * @author lukas
 *
 */
public class ParseUtils {

	/**
	 * @param n
	 * @param array
	 * @return Vrati zoznam ktory je <b>n-ty</b> v poradi vo vstupnom poli <b>array</b>, ktore je v stringu. 
	 */
	public static List<Integer> getNthArrayFromStringArray(int n, String array) {
		int indexFirst = StringUtils.ordinalIndexOf(array, "[", n+2);
		int indexLast = StringUtils.ordinalIndexOf(array, "]", n+1);
		String[] nthArray = array.substring(indexFirst+1, indexLast).split("\\s*,\\s*");
		List<Integer> result = new ArrayList<>();
		for (String string : nthArray) {
			if (!string.isEmpty()) {
				result.add(Integer.parseInt(string));
			}
		}
		return result;
	}

	/**
	 * @param n
	 * @param array
	 * @return Vrati cislo kto je <b>n-ty</b> v poradi vo vstupnom poli <b>array</b>, ktore je v stringu. 
	 */
	public static Integer getNthNumberFromStringArray(int n, String array) {
		int indexFirst = StringUtils.lastOrdinalIndexOf(array, "]", 2);
		int indexLast = StringUtils.lastOrdinalIndexOf(array, "]", 1);
		String numbersString = array.substring(indexFirst+2, indexLast);
		String[] numbersArray = numbersString.split("\\s*,\\s*");
		return Integer.parseInt(numbersArray[n]);
	}

	/**
	 * @param zvolenePredmetyIdsString
	 * @return Vrati zoznam cisel z vstupneho pole, ktore je v stringu.
	 */
	public static List<Integer> getListOfIntsFromString(String zvolenePredmetyIdsString) {
		zvolenePredmetyIdsString = StringUtils.strip(zvolenePredmetyIdsString, "]");
		zvolenePredmetyIdsString = StringUtils.strip(zvolenePredmetyIdsString, "[");
		String[] numbersArray = zvolenePredmetyIdsString.split("\\s*,\\s*");
		List<Integer> result = new ArrayList<>();
		for (String string : numbersArray) {
			if (!string.isEmpty()) {
				result.add(Integer.parseInt(string));
			}
		}
		return result;
	}
	
}
