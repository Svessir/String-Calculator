package is.ru.stringcalculator;

public class Calculator {

	public static int add(String text) {

		if(text == "") return 0;

		String[] numbers = text.split(",");
		int sum = 0;

		for(String number : numbers)
			sum += toInt(number);

		return sum;
	}

	private static int toInt(String text)
	{
		return Integer.parseInt(text);	
	}
}
