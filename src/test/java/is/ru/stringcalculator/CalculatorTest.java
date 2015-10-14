package is.ru.stringcalculator;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CalculatorTest {
	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("is.ru.stringcalculator.CalculatorTest");
	}

	@Test
	public void testEmptyString() {
		assertEquals(0, Calculator.add(""));
	}

	@Test
	public void testOneNumber() {
		assertEquals(1, Calculator.add("1"));
	}

	@Test
	public void testTwoNumbers() {
		assertEquals(3, Calculator.add("1,2"));
	}

	@Test 
	public void testMultipleNumbers()
	{
		assertEquals(10, Calculator.add("1,3,4,2"));
		assertEquals(16, Calculator.add("8,8"));
		assertEquals(79, Calculator.add("45,15,5,5,8,1"));
	}

	@Test
	public void testNewLineSeperator()
	{
		assertEquals(6, Calculator.add("1\n2,3"));
	}

	@Test
	public void testUnknownDelimeter()
	{
		assertEquals(3,Calculator.add("//;\n1;2"));
	}
}