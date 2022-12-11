package com.koppj.joshbot;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.apache.commons.io.IOUtils;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.FileUpload;

public class ComeBot extends ListenerAdapter
{	
	private static List<String> comeWords;					// The list of come words
	private static InputStream nerdCatImg;							// The cat image response
	
	public ComeBot()
	{
		// Load in the different assets from the classpath
		InputStream comeWordsFile = JoshBot.getAsset("assets/come_words.txt");
		nerdCatImg = JoshBot.getAsset("assets/nerd_cat.jpg");
		
		// Read the come words to the comeWords list
		try {
			comeWords = IOUtils.readLines(comeWordsFile, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event)
	{
		// Get the user that sent the message
		User user = event.getAuthor();
		
		// Check if the bot is in dev mode
		if (JoshBot.isDevMode() && !user.getName().equals("jkopp415")) return;
		
		// Store the messsage and content
		Message message = event.getMessage();
		String content = message.getContentRaw();
		
		// If the content matches any of the come words, reply with the cat image
		if (checkComeWords(content))
			message.replyFiles(FileUpload.fromData(nerdCatImg, "nerd_cat.jpg")).queue();
	}
	
	// Compares the input with all of the words in the come words list
	private boolean checkComeWords(String word)
	{
		for (String listWord : comeWords)
		{
			if (listWord.equals(word.toLowerCase()))
				return true;
		}
		return false;
	}
}
