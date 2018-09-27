package nl.oose.stringcalculator.core;

public class StringAddedFactory {
    public static StringAdder create()
    {
        return new StringCalculator();
    }
}
