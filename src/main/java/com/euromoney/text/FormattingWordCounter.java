package com.euromoney.text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormattingWordCounter implements WordCounter {
    private static final String WORD_MATCHER_TEMPLATE = "\\b%s\\b";
    private WordFormatter formatter = new EchoingWordFormatter();

    public FormattingWordCounter(final WordFormatter formatter) {
        if (formatter != null) {
            this.formatter = formatter;
        }
    }

    public FormattingWordCounter() {

    }

    @Override
    public WordCountResult count(final WordProvider wordProvider, final String input) {
        WordCountResult result = new WordCountResult(0, input);
        if (wordProvider != null && wordProvider.provideWords() != null && input != null) {
            for (final String word : wordProvider.provideWords()) {
                result = countAndReplaceMatches(result, word);
            }
        }
        return result;
    }

    private WordCountResult countAndReplaceMatches(final WordCountResult result, final String word) {
        int count = result.getCount();
        final String input = result.getInput();
        final StringBuffer sb = new StringBuffer(input.length());
        final Pattern pattern = Pattern.compile(String.format(WORD_MATCHER_TEMPLATE, word), Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(input);
        final String replacement = formatter.format(word);
        while (matcher.find()) {
            count++;
            matcher.appendReplacement(sb, replacement);
        }
        matcher.appendTail(sb);
        return new WordCountResult(count, sb.toString());
    }
}