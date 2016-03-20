package com.euromoney.text;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MiddleLettersMaskingFormatterTest {

    WordFormatter formatter = new MiddleLettersMaskingFormatter("#");

    @Test
    public void aSingleLetterIsNotFormatted() {
        assertThat("Single letter should not be formatted", formatter.format("a"), equalTo("a"));
    }

    @Test
    public void twoLettersAreNotFormatted() {
        assertThat("Two letters should not be formatted", formatter.format("ab"), equalTo("ab"));
    }

    @Test
    public void middeCharactersAreReplacedWithTheMaskText() {
        final String input = "Hello";
        final String expected = "H###o";
        assertThat("Middle chars shoud be masked", formatter.format(input), equalTo(expected));
    }
}