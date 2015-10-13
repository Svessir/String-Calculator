package is.ru.stringcalculator;

public class Calculator {

	public static int add(String text) {

		if(text == "") return 0;

		String[] numbers = split(text);

		return sum(numbers);
	}

	private static int sum(String[] stringNumbers)
	{
		int sum = 0;

		for(String number : stringNumbers)
			sum += toInt(number);

		return sum;
	}

	private static String[] split(String text)
	{
		return text.split("(\\r?\\n)|(,)");
	}

	private static int toInt(String text)
	{
		if(text == "") return 0;
		return Integer.parseInt(text);
	}
}
