package com.koppj.joshbot;

import java.io.InputStream;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.FileUpload;

public class SimpleCommands extends ListenerAdapter
{
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
		
		// Fun little ping-pong command
		if (content.equals("!joshping"))
			message.reply("Pong!").queue();
		
		// Shuts the bot down
		else if (content.equals("!joshshutdown") && user.getName().equals(JoshBot.getDevName()))
		{
			InputStream goodbyeCatStream = JoshBot.loadAsset("assets/goodbye_cat.jpg");
			event.getChannel().sendMessage("Shutting down. Bye!")
				.addFiles(FileUpload.fromData(goodbyeCatStream, "goodbye_cat.jpg")).queue();
			JoshBot.shutdownBot();
		}
	}
}
