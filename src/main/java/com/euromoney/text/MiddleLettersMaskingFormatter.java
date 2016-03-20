package com.euromoney.text;

public class MiddleLettersMaskingFormatter implements WordFormatter {

    private final String mask;

    public MiddleLettersMaskingFormatter(final String mask) {
        this.mask = mask;
    }

    @Override
    public String format(final String word) {
        String formattedWord = word;
        if (word != null && word.length() > 2) {
            formattedWord = maskMiddleCharacters(formattedWord);
        }
        return formattedWord;
    }

    private String maskMiddleCharacters(final String input) {
        final StringBuilder sb = new StringBuilder();
        sb.append(input.charAt(0));
        for (int i = 1; i < input.length() - 1; i++) {
            sb.append(mask);
        }
        sb.append(input.charAt(input.length() - 1));
        return sb.toString();
    }
}