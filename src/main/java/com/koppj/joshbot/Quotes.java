package com.koppj.joshbot;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.io.IOUtils;

public class Quotes
{
	private static List<String> quotes;
	
	public static void initQuotes()
	{
		InputStream quotesStream = JoshBot.loadAsset("assets/quotes.txt");
		
		try {
			quotes = IOUtils.readLines(quotesStream, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getRandomQuote()
	{
		int randInt = (int)(Math.random() * quotes.size());
		return quotes.get(randInt);
	}
}
