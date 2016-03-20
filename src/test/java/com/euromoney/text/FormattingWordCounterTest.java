package com.euromoney.text;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FormattingWordCounterTest {

    @InjectMocks
    FormattingWordCounter counter;

    @Mock
    WordProvider wordProvider;

    @Mock
    WordFormatter wordFormatter;

    @Before
    public void setup() {
        when(wordFormatter.format(anyString())).thenAnswer(invocation -> {
            final Object[] args = invocation.getArguments();
            return (String)args[0];
        });
    }

    private void assertWordCount(final String[] wordsToCount, final String input, final int expectedCount) {
        when(wordProvider.provideWords()).thenReturn(wordsToCount);
        final WordCountResult result = counter.count(wordProvider, input);
        assertThat("Unexpected word count", result.getCount(), equalTo(expectedCount));
    }

    @Test
    public void correctlyCountsSingleWord() {
        assertWordCount(new String[] { "test" }, "test", 1);
    }

    @Test
    public void correctlyCountsTheSameWordMultipleTimes() {
        assertWordCount(new String[] { "test" }, "test test test", 3);
    }

    @Test
    public void correctlyCountsMultipleDifferentWords() {
        assertWordCount(new String[] { "test", "foo", "bar" }, "Start test foo bar end", 3);
    }

    @Test
    public void matchShouldBeCaseInsensitive() {
        assertWordCount(new String[] { "TEST" }, "test", 1);
    }

    @Test
    public void noMatchingWordsShouldReturnACountOfZero() {
        assertWordCount(new String[0], "test", 0);
    }

    @Test
    public void nullMatchingArrayShouldReturnZero() {
        assertWordCount(null, "test", 0);
    }

    @Test
    public void nullInputShouldReturnZero() {
        assertWordCount(new String[0], null, 0);
    }

    @Test
    public void formattingIsAppliedToMatchIfAFormatterIsProvided() {
        final String word = "test";
        final String expectedFormattedWord = "t##t";
        final String[] wordsToCount = new String[] { word };
        when(wordProvider.provideWords()).thenReturn(wordsToCount);
        when(wordFormatter.format(word)).thenReturn(expectedFormattedWord);
        final WordCountResult result = counter.count(wordProvider, word);
        assertThat("Matched word should have been formatted", result.getInput(), equalTo(expectedFormattedWord));
    }
}