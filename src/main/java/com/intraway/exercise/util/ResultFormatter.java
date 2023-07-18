package com.intraway.exercise.util;

import java.util.Set;
import java.util.stream.Collectors;

import com.intraway.exercise.model.response.GoodRequest;
import com.intraway.exercise.service.result.Result;

public class ResultFormatter {
	public static final String MULTIS_FOUND = "Se encontraron múltiplos de ";
	public static final String MULTIS_NOT_FOUND = "No se encontraron múltiplos";
	public static final String SEPARATOR = ", ";
	public static final String LAST_SEPARATOR = " y";
	public static final String MSG_TEMPLATE = "%s%s%s";
	
	/*
	 * This changes last ',' (SEPARATOR constant) by 'y' (LAST_SEPARATOR constant)
	 * */
	private static String replaceWithLastSeparator(String multisSplitByComma) {
		
		int INVALID_INDEX = -1;
		int lastSeparatorIndex = multisSplitByComma.lastIndexOf(SEPARATOR);
		
		if(INVALID_INDEX == lastSeparatorIndex) {
			String EMPTY = "";
			
			return String.format(
				MSG_TEMPLATE, 
				multisSplitByComma,
				EMPTY,
				EMPTY
			);
		}
		
		int multisSplitByCommaLength = multisSplitByComma.length();
		
		return String.format(
			MSG_TEMPLATE, 
			multisSplitByComma.substring(0, lastSeparatorIndex),
			LAST_SEPARATOR,
			multisSplitByComma.substring(lastSeparatorIndex + 1, multisSplitByCommaLength)
		);
	}
	
	/*
	 * This prints "se encontraron múltiplos de..."
	 * */
	private static String getMultisFoundAsStringMsg(Set<Integer> multis) {
		StringBuilder message = new StringBuilder();
		
		String multisSplitByComma = String.join(
				SEPARATOR, 
				multis
				.stream()
				.map( number -> String.valueOf(number))
				.collect(Collectors.toList())
			);
		
		
		message.append(MULTIS_FOUND);
		message.append(
			replaceWithLastSeparator(multisSplitByComma)
		);
		
		return message.toString();
	}
	
	public static GoodRequest formatResult(Result result) {
		GoodRequest goodRequest = new GoodRequest();
		
		Set<Integer> multis = result.getAvailableMultiples();
		
		StringBuilder message = new StringBuilder();
		
		if(multis.isEmpty()) {
			message.append(MULTIS_NOT_FOUND);
		} else {
			message.append(
				getMultisFoundAsStringMsg(multis)
			);
		}

		goodRequest.setDescription(message.toString());
		goodRequest.setList(
			String.join(SEPARATOR, result.getReplacedNumbers())
		);
		
		return goodRequest;
	}
}
