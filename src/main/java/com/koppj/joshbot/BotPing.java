package com.koppj.joshbot;

import java.io.InputStream;
import java.util.List;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.FileUpload;

public class BotPing extends ListenerAdapter
{
	@Override
	public void onMessageReceived(MessageReceivedEvent event)
	{
		// Get the user that sent the message
		User user = event.getAuthor();
		
		// Check if the bot is in dev mode
		if (JoshBot.isDevMode() && !user.getName().equals(JoshBot.getDevName())) return;
		
		// Create a list of users mentioned in the message
		List<User> mentionedUsers = event.getMessage().getMentions().getUsers();
		
		for (User mentionedUser : mentionedUsers)
		{
			// If this bot has been mentioned, send the gif
			if (mentionedUser.getDiscriminator().equals(JoshBot.getUserId()))
			{
				InputStream whoPingedMeStream = JoshBot.loadAsset("assets/who_pinged_me.gif");
				event.getMessage().replyFiles(FileUpload.fromData(whoPingedMeStream, "who_pinged_me.gif")).queue();
			}
		}
	}
}
