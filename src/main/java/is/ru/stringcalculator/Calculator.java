package is.ru.stringcalculator;

import java.util.regex.Pattern;

public class Calculator {

	private static String defaultDelim = ",";
	private static String delimiterSpecifier = "//";
	private static int maxInt = 1000;

	public static int add(String text) {

		if(text == "") return 0;

		if(isCustomDelimiter(text)) text = fixInput(text); 

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
		text = text.replaceFirst(delimiterSpecifier, "");
		String[] delimAndNumbers = text.split("\\r?\\n", 2);
		return delimAndNumbers[1].replaceAll(Pattern.quote(delimAndNumbers[0]), defaultDelim);
	}

	private static boolean isNegetive(int number)
	{
		return number < 0;
	}
}
