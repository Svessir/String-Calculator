package is.ru.stringcalculator;

import java.util.regex.*;

public class Calculator {

	private static String defaultDelim = ",";
	private static String delimiterSpecifier = "//";
	private static int maxInt = 1000;

	public static int add(String text) {

		if(text.isEmpty()) return 0;

		text = fixInput(text); 

		String[] numbers = splitIntoStringNumbers(text);

		return sum(numbers);
	}

	private static int sum(String[] stringNumbers) {
		int sum = 0, num;
		String negetiveNumbers = "";

		for(String number : stringNumbers) {
			num = toInt(number);

			if(isNegetive(num)) {
				negetiveNumbers = (negetiveNumbers == "") ? negetiveNumbers : negetiveNumbers + ",";
				negetiveNumbers += number;
			}

			sum += (num > maxInt) ? 0 : num;
		}
		
		if(!negetiveNumbers.isEmpty())
			throw new IllegalArgumentException("Negatives not allowed: " + negetiveNumbers);

		return sum;
	}

	private static String[] splitIntoStringNumbers(String text) {
		return text.split("\\r?\\n|" + defaultDelim );
	}

	private static int toInt(String text) {
		if(text.isEmpty()) return 0;
		
		try{
			return Integer.parseInt(text);
		}
		catch(NumberFormatException e) {
			throw new IllegalArgumentException(findIllegalDelimiters(text));
		}
	}

	private static boolean isCustomDelimiter(String text) {
		return text.startsWith(delimiterSpecifier);
	}

	private static String fixInput(String text) {
		if(!isCustomDelimiter(text)) return text;

		// Split into two strings on newline, where delimAndNumbers[0] is the delimiter specifications
		// and delimAndNumbers[1] is the numbers that should be added calculated
		String[] delimAndNumbers = text.split("\\r?\\n", 2);
		text = delimAndNumbers[1];

		Matcher matcher = Pattern.compile("\\[(.*?)\\]").matcher(delimAndNumbers[0]);

		String customDelimiter = null;

		while(matcher.find()) {
			customDelimiter = matcher.group();
			customDelimiter = customDelimiter.substring(1, customDelimiter.length() - 1);
			text = replaceCustomDelimiter(text, customDelimiter);	
		}

		if(customDelimiter == null) 
			text = replaceCustomDelimiter(text, delimAndNumbers[0].substring(2,3));
		
		return text;
	}

	private static boolean isNegetive(int number) {
		return number < 0;
	}

	private static String replaceCustomDelimiter( String text, String customDelimiter ) {
		return text.replaceAll(Pattern.quote(customDelimiter), defaultDelim);
	}

	private static String findIllegalDelimiters(String illegalString) {
		String errorMessage = "";
		boolean isMultiple = false, isComma = false;

		for(int i = 0; i < illegalString.length(); i++) {
			char symbol = illegalString.charAt(i);

			if(!Character.isDigit(symbol)) {
				errorMessage += (isComma == true) ? ", " + symbol : symbol;
				isMultiple = (isComma == true) ? true : isMultiple;
				isComma = false;
			}
			else if(errorMessage.length() > 0) isComma = true;
		}

		return errorMessage + ((isMultiple == true) ? " are not specified delimiters." : " is not a specified delimiter.");
	}
}
