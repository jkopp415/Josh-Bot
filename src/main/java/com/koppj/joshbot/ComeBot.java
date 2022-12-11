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
	
	public ComeBot()
	{
		// Load in the different assets from the classpath
		InputStream comeWordsStream = JoshBot.loadAsset("assets/come_words.txt");
		
		// Read the come words to the comeWords list
		try {
			comeWords = IOUtils.readLines(comeWordsStream, StandardCharsets.UTF_8);
			comeWordsStream.close();
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
		if (JoshBot.isDevMode() && !user.getName().equals(JoshBot.getDevName())) return;
		
		// Store the messsage and content
		Message message = event.getMessage();
		String content = message.getContentRaw();
		
		// If the content matches any of the come words, reply with the cat image
		if (checkComeWords(content))
		{
			InputStream nerdCatStream = JoshBot.loadAsset("assets/nerd_cat.jpg");
			message.replyFiles(FileUpload.fromData(nerdCatStream, "nerd_cat.jpg")).queue();
		}
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
