package com.ludmylla.spring.loja.useful;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.regex.Pattern;

public class Useful {
	
	public static boolean compareCaseSensitiveAndCheckToQuote(String compare1, String compare2) {
		boolean isAddressViacepIsEqualAddress = Pattern
		.compile(Pattern.quote(removeAccents(compare1)), Pattern.CASE_INSENSITIVE)
		.matcher(removeAccents(compare2)).find();
		return isAddressViacepIsEqualAddress;
	}
	
	private static String removeAccents(String text) {
		return text == null ? null
				: Normalizer.normalize(text, Form.NFD)
				.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}
	
	public static void compareAddressIsCorrect(boolean compareBlank, String compare1, String compare2,
			boolean compareCaseInsensitiveAndContains, String msg) {
		
		if(!compareBlank && !compare1.equalsIgnoreCase(compare2)
				&& !compareCaseInsensitiveAndContains) {
			
			 throw new IllegalArgumentException(msg);
		}
	}
	
	
	
}
