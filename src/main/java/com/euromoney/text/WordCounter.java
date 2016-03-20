package com.euromoney.text;

public interface WordCounter {
    WordCountResult count(WordProvider provider, final String input);
}