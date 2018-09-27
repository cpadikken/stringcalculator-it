package nl.oose.stringcalculator.core;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Create a simple String calculator with a method int Add(string numbers). The method can take 0, 1 or 2 numbers, and will return their sum (for an empty string it will return 0) for example “” or “1” or “1,2”
 */
public class StringCalculatorTest {
    private StringAdder stringAdder;

    @Before
    public void setup()
    {
        stringAdder = new StringCalculator();
    }

    @Test
    public void emptyStringReturnsZero()
    {
        assertEquals(0, stringAdder.add(""));
    }

    @Test
    public void stringNumberOneReturnsOne()
    {
        assertEquals(1, stringAdder.add("1"));
    }

    @Test
    public void stringNumberTwoReturnsTwo()
    {
        assertEquals(2, stringAdder.add("2"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void stringWithoutValidNumberShouldThrowIllegalArgumentException()
    {
        stringAdder.add("a");
    }

    @Test
    public void stringWithTwoNumbersSeparatedByACommaReturnsSum()
    {
        assertEquals(3, stringAdder.add("1,2"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void stringWithTwoNumbersSeparatedByADifferentSeparatorThrowsIllegalArgumentException()
    {
        stringAdder.add("1;2");
    }

    @Test
    public void stringWithThreeNumbersSeparatedByACommaReturnsSum()
    {
        assertEquals(6, stringAdder.add("1,2,3"));
    }

    @Test
    public void stringWithThreeNumbersAndTwoSeparatorsReturnsSum()
    {
        assertEquals(6, stringAdder.add("1\n2,3"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void stringWithOneNumberAndTwoSeparatorsThrowsIllegalArgumentException()
    {
        stringAdder.add("1,\n");
    }

    @Test
    public void stringWithTwoNumbersAndSemicolonACustomDelimiterReturnsSum()
    {
        assertEquals(3, stringAdder.add("//;\n1;2"));
    }

    @Test
    public void stringWithThreeNumbersAndExclamationMarkAsCustomDelimiterReturnsSum()
    {
        assertEquals(6, stringAdder.add("//!\n1!2!3"));
    }

    @Test
    public void stringWithThreeNumbersAndDollarSignAndNewLineAsCustomDelimiterReturnsSum()
    {
        assertEquals(6, stringAdder.add("//$\n1$2\n3"));
    }

    @Test
    public void stringNumberNegativeTwoReturnsThrowsIlllegalArgumentExceptionContainingWrongNumber()
    {
        try {
            stringAdder.add("-2");
            fail();
        }
        catch(IllegalArgumentException e)
        {
            assertTrue(e.getMessage().contains("negatives not allowed"));
            assertTrue(e.getMessage().contains("-2"));
        }
    }

    @Test
    public void stringNumberTwoNegativesReturnsThrowsIlllegalArgumentExceptionContainingWrongNumbers()
    {
        try {
            stringAdder.add("-2,-3");
            fail();
        }
        catch(IllegalArgumentException e)
        {
            assertTrue(e.getMessage().contains("negatives not allowed"));
            assertTrue(e.getMessage().contains("-2"));
            assertTrue(e.getMessage().contains("-3"));
        }
    }
}
