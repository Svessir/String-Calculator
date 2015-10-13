package is.ru.stringcalculator;

public class Calculator {

	public static int add(String text) {

		if(text == "") return 0;

		String[] numbers = text.split(",");

		if(numbers.length > 1)
			return Integer.parseInt(numbers[0]) + Integer.parseInt(numbers[1]);

		return Integer.parseInt(numbers[0]);
	}
}
