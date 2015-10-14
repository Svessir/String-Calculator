package is.ru.stringcalculator;

import java.util.regex.*;

public class Calculator {

	private static String defaultDelim = ",";
	private static String delimiterSpecifier = "//";
	private static int maxInt = 1000;

	public static int add(String text) {

		if(text == "") return 0;

		text = fixInput(text); 

		String[] numbers = splitIntoStringNumbers(text);

		return sum(numbers);
	}

	private static int sum(String[] stringNumbers)
	{
		int sum = 0, num;
		String negetiveNumbers = "";

		for(String number : stringNumbers)
		{
			num = toInt(number);

			if(isNegetive(num)) {
				negetiveNumbers = (negetiveNumbers == "") ? negetiveNumbers : negetiveNumbers + ",";
				negetiveNumbers += number;
			}

			sum += (num > maxInt) ? 0 : num;
		}
		
		if(negetiveNumbers != "") 
			throw new IllegalArgumentException("Negatives not allowed: " + negetiveNumbers);

		return sum;
	}

	private static String[] splitIntoStringNumbers(String text)
	{
		return text.split("\\r?\\n|" + defaultDelim );
	}

	private static int toInt(String text)
	{
		if(text == "") return 0;
		return Integer.parseInt(text);
	}

	private static boolean isCustomDelimiter(String text)
	{
		return text.startsWith(delimiterSpecifier);
	}

	private static String fixInput(String text)
	{
		if(!isCustomDelimiter(text)) return text;

		String[] delimAndNumbers = text.split("\\r?\\n", 2);
		text = delimAndNumbers[1];

		Matcher matcher = Pattern.compile("\\[(.*?)\\]").matcher(delimAndNumbers[0]);

		String customDelimiter = null;

		while(matcher.find())
		{
			customDelimiter = matcher.group();
			customDelimiter = customDelimiter.substring(1, customDelimiter.length() - 1);
			text = replaceCustomDelimiter(text, customDelimiter);	
		}

		if(customDelimiter == null) 
			text = replaceCustomDelimiter(text, delimAndNumbers[0].substring(2,3));
		
		return text;
	}

	private static boolean isNegetive(int number)
	{
		return number < 0;
	}

	private static String replaceCustomDelimiter( String text, String customDelimiter )
	{
		return text.replaceAll(Pattern.quote(customDelimiter), defaultDelim);
	}
}
