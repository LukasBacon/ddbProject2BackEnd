package com.lukas.ddbProject2BackEnd;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class ParseUtils {

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

	public static Integer getNthNumberFromStringArray(int n, String array) {
		int indexFirst = StringUtils.lastOrdinalIndexOf(array, "]", 2);
		int indexLast = StringUtils.lastOrdinalIndexOf(array, "]", 1);
		String numbersString = array.substring(indexFirst+2, indexLast);
		String[] numbersArray = numbersString.split("\\s*,\\s*");
		return Integer.parseInt(numbersArray[n]);
	}

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
