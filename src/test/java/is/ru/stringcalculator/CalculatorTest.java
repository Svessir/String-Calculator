package is.ru.stringcalculator;

import static org.junit.Assert.*;
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
		assertEquals(2, Calculator.add("2"));
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
	public void testNewLineSeperator() {
		assertEquals(6, Calculator.add("1\n2,3"));
	}

	@Test
	public void testUnknownDelimiter() {
		assertEquals(3,Calculator.add("//;\n1;2"));
		assertEquals(3,Calculator.add("//$\n1$2"));
		assertEquals(3,Calculator.add("//b\n1b2"));
	}

	@Test
	public void testOneNegetiveInput() {
		try {

			Calculator.add("-1,2,3");
			fail("Should have thrown IllegalArgumentException");
		} 
		catch( IllegalArgumentException e ) {
			assertEquals("Negatives not allowed: -1", e.getMessage());
		}
	}

	@Test
	public void testMultipleNegetiveInputs() {
		try {

			Calculator.add("2,-4,3,-5");
			fail("Should have thrown IllegalArgumentException");
		}
		catch( IllegalArgumentException e ) {
			assertEquals("Negatives not allowed: -4,-5", e.getMessage());
		}
	}

	@Test
	public void testIgnoreLargerThanThousand() {
		assertEquals(2, Calculator.add("1001,2"));
	}

	@Test
	public void testCustomDelimiterOfUnknownLength() {
		assertEquals(3, Calculator.add("//[ttt]\n1ttt2"));
		assertEquals(6, Calculator.add("//[***]\n1***2***3"));
	}

	@Test
	public void testMultipleUnknownDelimiters() {
		assertEquals(6, Calculator.add("//[*][%]\n1*2%3"));
	}

	@Test
	public void testMultipleDelimitersOfUnknownLength() {
		assertEquals(6, Calculator.add("//[*****][%%%%%]\n1*****2%%%%%3\n0,0"));
		assertEquals(106, Calculator.add("//[*][%][++++]\n1*2%3++++100"));
	}

	@Test
	public void testNonNumberAndNonDelimiterSymbol()
	{
		try{
			Calculator.add("45,15,5,5,8,1#2");
			fail("Should have thrown IllegalArgumentException");
		}
		catch( IllegalArgumentException e ) {
			assertEquals("# is not a specified delimiter.", e.getMessage());
		}
	}

	@Test
	public void testMultipleNonNumberAndNonDelimiterSymbols()
	{
		try{
			Calculator.add("45,15,5,5,8,1#2!4");
			fail("Should have thrown IllegalArgumentException");
		}
		catch( IllegalArgumentException e ) {
			assertEquals("#, ! are not specified delimiters.", e.getMessage());
		}
	}

	@Test
	public void testLegalDelimitersWithNoNumberInBetween()
	{
		assertEquals(79, Calculator.add("//[!][,%]\n45,,15,,5,%5,8,!1"));
	}
}