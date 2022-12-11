package com.koppj.joshbot;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.IOUtils;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SpaceFactsBot extends ListenerAdapter
{
	private static List<String> spaceFacts;
	
	public SpaceFactsBot()
	{
		InputStream spaceFactsStream = JoshBot.loadAsset("assets/space_facts.txt");
		
		try {
			spaceFacts = IOUtils.readLines(spaceFactsStream, StandardCharsets.UTF_8);
			spaceFactsStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event)
	{
		// Get the user that sent the message and make sure they're not a bot
		User user = event.getAuthor();
		if (user.isBot()) return;
		
		// Check if the bot is in dev mode
		if (JoshBot.isDevMode() && !user.getName().equals(JoshBot.getDevName())) return;
		
		// Store the messsage and content
		Message message = event.getMessage();
		String content = message.getContentRaw();
		
		if (content.equals("!joshspacefact"))
		{
			Random rand = new Random();
			int randInt = rand.nextInt(spaceFacts.size());
			message.reply(spaceFacts.get(randInt)).queue();
		}
	}
}
