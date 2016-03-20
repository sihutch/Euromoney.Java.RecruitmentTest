package com.euromoney.text;

public class EchoingWordFormatter implements WordFormatter {

    @Override
    public String format(final String word) {
        return word;
    }
}