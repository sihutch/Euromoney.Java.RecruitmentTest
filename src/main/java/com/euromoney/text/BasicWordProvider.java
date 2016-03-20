package com.euromoney.text;

public class BasicWordProvider implements WordProvider {

    private final String[] words;

    public BasicWordProvider(final String[] words) {
        this.words = words;
    }

    @Override
    public String[] provideWords() {
        return words;
    }
}