package com.koppj.joshbot;

import java.io.InputStream;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.utils.FileUpload;

public class BotCommands extends ListenerAdapter
{
	public BotCommands()
	{
		// The list of commands that the bot will have
		JoshBot.getJoshBot().updateCommands().addCommands(
				Commands.slash("joshping", "Pong!"),
				Commands.slash("joshshutdown", "Shuts down the bot (if you have permission)"),
				Commands.slash("joshspacefact", "Gives a random space fact"),
				Commands.slash("joshquote", "Gives a fun inspirational quote")
		).queue();
	}
	
	@Override
	public void onSlashCommandInteraction(SlashCommandInteractionEvent event)
	{
		// Ping command
		if (event.getName().equals("joshping"))
			event.reply("Pong!").queue();
		
		// Shutdown bot command
		if (event.getName().equals("joshshutdown"))
		{
			// If the user is not the dev, don't let them shut the bot down
			if (!event.getUser().getName().equals(JoshBot.getShutdownUser()))
			{
				event.reply("You don't have the right permissions, stop it.").setEphemeral(true).queue();
				return;
			}
		
			// Replies to the user so the response isn't empty
			event.reply("Shutting down...").setEphemeral(true).queue();
			
			// Sends the shutdown message
			InputStream goodbyeCatStream = JoshBot.loadAsset("assets/goodbye_cat.jpg");
			event.getChannel().sendMessage("Shutting down. Bye!")
				.addFiles(FileUpload.fromData(goodbyeCatStream, "goodbye_cat.jpg")).queue();
			JoshBot.shutdownBot();
		}
		
		// Space fact command
		if (event.getName().equals("joshspacefact"))
			event.reply(SpaceFacts.getRandomSpaceFact()).queue();
		
		if (event.getName().equals("joshquote"))
			event.reply(Quotes.getRandomQuote()).queue();
	}
}
