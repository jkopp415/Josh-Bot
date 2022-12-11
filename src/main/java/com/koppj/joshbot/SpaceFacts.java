package com.koppj.joshbot;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.apache.commons.io.IOUtils;

public class SpaceFacts
{	
	private static List<String> spaceFacts;
	
	public SpaceFacts()
	{
		InputStream spaceFactsStream = JoshBot.loadAsset("assets/space_facts.txt");
		
		try {
			spaceFacts = IOUtils.readLines(spaceFactsStream, StandardCharsets.UTF_8);
			spaceFactsStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getRandomSpaceFact()
	{
		int randInt = (int)(Math.random() * spaceFacts.size());
		return spaceFacts.get(randInt);
	}
}
