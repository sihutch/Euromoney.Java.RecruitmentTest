package com.euromoney.text;

public class WordCountResult {

    private final int count;
    private final String input;

    public WordCountResult(final int count, final String input) {
        this.count = count;
        this.input = input;
    }

    public int getCount() {
        return count;
    }

    public String getInput() {
        return input;
    }
}