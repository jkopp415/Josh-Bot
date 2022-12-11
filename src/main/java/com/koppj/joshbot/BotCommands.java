package com.koppj.joshbot;

import java.io.InputStream;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.utils.FileUpload;

public class BotCommands extends ListenerAdapter
{
	public BotCommands()
	{
		JoshBot.getJoshBot().updateCommands().addCommands(
				Commands.slash("joshping", "Pong!"),
				Commands.slash("joshshutdown", "Shuts down the bot (if you're jkopp415)"),
				Commands.slash("joshspacefact", "Gives a random space fact")
		).queue();
	}
	
	@Override
	public void onSlashCommandInteraction(SlashCommandInteractionEvent event)
	{
		if (event.getName().equals("joshping"))
			event.reply("Pong!").queue();
		
		if (event.getName().equals("joshshutdown"))
		{
			InputStream goodbyeCatStream = JoshBot.loadAsset("assets/goodbye_cat.jpg");
			event.getChannel().sendMessage("Shutting down. Bye!")
				.addFiles(FileUpload.fromData(goodbyeCatStream, "goodbye_cat.jpg")).queue();
			JoshBot.shutdownBot();
		}
		
		if (event.getName().equals("joshspacefact"))
			event.reply(SpaceFactsBot.getRandomSpaceFact()).queue();
	}
}
