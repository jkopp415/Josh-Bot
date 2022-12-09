package com.koppj.joshbot;

import java.io.InputStream;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.FileUpload;

public class SimpleCommands extends ListenerAdapter
{
	private static InputStream goodbyeCatImg;				// The image the bot sends when it gets shutdown
	
	public SimpleCommands()
	{
		// Load in the goodbye cat image for bot shutdown
		goodbyeCatImg = JoshBot.getAsset("assets/goodbye_cat.jpg");
	}
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event)
	{
		// Get the user that sent the message and make sure they're not a bot
		User user = event.getAuthor();
		if (user.isBot()) return;
		
		// Check if the bot is in dev mode
		if (JoshBot.isDevMode() && !user.getName().equals("jkopp415")) return;
		
		// Store the messsage and content
		Message message = event.getMessage();
		String content = message.getContentRaw();
		
		// Fun little ping-pong command
		if (content.equals("!joshping"))
			message.reply("Pong!").queue();
		
		// Shuts the bot down
		else if (content.equals("!joshshutdown") && user.getName().equals("jkopp415"))
		{
			event.getChannel().sendMessage("Shutting down. Bye!")
				.addFiles(FileUpload.fromData(goodbyeCatImg, "goodbye_cat.jpg")).queue();
			JoshBot.shutdownBot();
		}
	}
}
