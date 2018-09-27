package nl.oose.stringcalculator.core;

class RegexBuilder {

    public static final String DEFAULT_ESCAPE_CHAR = "\\";

    public String build(String[] separators) {
        StringBuilder builder = new StringBuilder();
        for(String separator:separators)
        {
            buildRegEx(builder, separator);
        }

        return removeTrailingPipeFromRegex(builder.toString());
    }

    private void buildRegEx(StringBuilder builder, String separator) {
        String escape = "";
        if (!separator.startsWith(DEFAULT_ESCAPE_CHAR))
            escape = DEFAULT_ESCAPE_CHAR;
        builder.append(escape);
        builder.append(separator);
        builder.append("|");
    }

    private String removeTrailingPipeFromRegex(String regex) {
        return regex.substring(0, regex.length() - 1);
    }
}
