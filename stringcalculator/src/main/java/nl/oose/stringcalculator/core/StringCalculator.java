package nl.oose.stringcalculator.core;

import java.util.stream.IntStream;

class StringCalculator implements StringAdder {

    @Override
    public int add(String numberString) {
        NumberStringParser numberStringParser = new NumberStringParser(numberString);
        int[] parsedNumbers = numberStringParser.parse();
        return IntStream.of(parsedNumbers).sum();
    }
}
