package is.ru.stringcalculator;

public class Calculator {

	private static String defaultDelim = ",";
	private static String delimiterSpecifier = "//";

	public static int add(String text) {

		if(text == "") return 0;

		if(isCustomDelimiter(text)) text = fixInput(text); 

		String[] numbers = splitIntoStringNumbers(text);

		return sum(numbers);
	}

	private static int sum(String[] stringNumbers)
	{
		int sum = 0, num;

		for(String number : stringNumbers)
		{
			num = toInt(number);

			if(isNegetive(num)) throw new IllegalArgumentException("Negatives not allowed: " + number);

			sum += num;
		}
		

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
		String[] split = text.split("\\r?\\n", 2);
		return split[1].replaceAll(split[0], defaultDelim);
	}

	private static boolean isNegetive(int number)
	{
		return number < 0;
	}
}
