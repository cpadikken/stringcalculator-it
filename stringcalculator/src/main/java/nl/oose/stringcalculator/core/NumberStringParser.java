package nl.oose.stringcalculator.core;

import java.util.stream.Stream;

class NumberStringParser {
    private String[] separators = {",", "\n"};

    public static final String EMPTY_STRING = "";
    private String listWithNumbersToParse;

    public NumberStringParser(String listWithNumbersToParse)
    {
        this.listWithNumbersToParse = listWithNumbersToParse;
    }

    public int[] parse() {
        if (prefixedWithDifferentSeparator(listWithNumbersToParse))
        {
            separators[0] = listWithNumbersToParse.substring(2,3);
            stripPrefixedSeparator();
        }
        return parseWithSeparators(listWithNumbersToParse);
    }

    private void stripPrefixedSeparator() {
        listWithNumbersToParse = listWithNumbersToParse.substring(
                listWithNumbersToParse.indexOf('\n') + 1,
                listWithNumbersToParse.length());
    }

    private boolean prefixedWithDifferentSeparator(String listWithNumbersToParse) {
        return listWithNumbersToParse.startsWith("//");
    }

    private int[] parseWithSeparators(String listWithNumbersToParse) {
        String regEx = new RegexBuilder().build(separators);
        String[] tokens = listWithNumbersToParse.split(regEx);
        if (tokens.length == 1) {
            if (separatorsAreNotUsedAsDelimiters(listWithNumbersToParse))
                throw new IllegalArgumentException();
            if (isEmptyString(tokens[0]))
                return new int[]{0};
            else
                return new int[]{parseNumber(tokens[0])};
        }

        throwExceptionWhenListContainsNegatives(tokens);

        return Stream.of(tokens).mapToInt(Integer::parseInt).toArray();
    }

    private void throwExceptionWhenListContainsNegatives(String[] tokens) {
        Object[] negatives = Stream.of(tokens).filter(e -> Integer.parseInt(e) < 0).toArray();
        String exceptionMessage = "negatives not allowed ";
        if (negatives.length >0) {
            StringBuilder builder = new StringBuilder();
            builder.append(exceptionMessage);
            for(Object negative: negatives){
                builder.append(negative);
            }
            throw new IllegalArgumentException(builder.toString());
        }
    }

    private int parseNumber(String token) {

        int number = Integer.parseInt(token);
        if (number >= 0) return number;
        else throw new IllegalArgumentException("negatives not allowed " + number);
    }

    private boolean isEmptyString(String token) {
        return token == EMPTY_STRING;
    }

    private boolean separatorsAreNotUsedAsDelimiters(String listWithNumbersToParse) {
        return listWithNumbersToParse.length() > 2;
    }
}
