package com.euromoney.ConsoleContent;

import java.io.IOException;

import com.euromoney.text.BasicWordProvider;
import com.euromoney.text.EchoingWordFormatter;
import com.euromoney.text.FormattingWordCounter;
import com.euromoney.text.MiddleLettersMaskingFormatter;
import com.euromoney.text.WordCountResult;
import com.euromoney.text.WordCounter;
import com.euromoney.text.WordFormatter;
import com.euromoney.text.WordProvider;

public class Program {

    private static final String NEGATIVE_WORD_TEMAPLTE = "Total Number of negative words: %d";

    /**
     * Initialises the application in the console.
     *
     * @throws IOException
     */
    public static void main(final String[] args) throws IOException {

        final String[] negativeWords = { "swine", "bad", "nasty", "horrible" };
        final String content = "The weather in Manchester in winter is bad. It rains all the time - it must be horrible for people visiting.";

        final boolean removeMask = args.length > 0 && args[0].equals("-d");

        final WordFormatter formatter = removeMask ? new EchoingWordFormatter() : new MiddleLettersMaskingFormatter("#");
        final WordCounter counter = new FormattingWordCounter(formatter);
        final WordProvider provider = new BasicWordProvider(negativeWords);
        final WordCountResult result = counter.count(provider, content);

        System.out.println("Scanned the text:");
        System.out.println(result.getInput());
        System.out.println(String.format(NEGATIVE_WORD_TEMAPLTE, result.getCount()));

        System.out.println("\nPress ENTER to exit!\n");
        System.in.read();
        System.out.println("\nExiting Application!\n");
    }
}