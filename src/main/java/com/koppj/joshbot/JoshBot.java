package com.koppj.joshbot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.FileUpload;

public class JoshBot extends ListenerAdapter
{
	private static final String TOKEN = "MTA1MDUyNzAwOTA5ODQ0OTA0OQ.GMOaT0.rMxEykjRulTr-VTcO7BHMOiXhiNd8tMW9HkB2E";
	
	private static File usersList;
	private static File keywordsList;
	private static File catImg;
	
	public static void main(String[] args) throws Exception
	{
		usersList = new File("src/main/resources/users.txt");
		keywordsList = new File("src/main/resources/keywords.txt");
		catImg = new File("src/main/resources/nerd_cat.jpg");
		
		@SuppressWarnings("unused")
		JDA josh_bot = JDABuilder.createDefault(TOKEN)
				.addEventListeners(new JoshBot())
				.enableIntents(GatewayIntent.MESSAGE_CONTENT)
				.build();
	}
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event)
	{
		User user = event.getAuthor();
		if (user.isBot()) return;
		
		Message message = event.getMessage();
		String content = message.getContentRaw();
		
		if (checkFile(user.getName(), usersList) && checkFile(content, keywordsList))
		{
			message.replyFiles(FileUpload.fromData(catImg)).queue();
		}
	}
	
	private boolean checkFile(String word, File file)
	{
		BufferedReader reader;
		
		try 
		{
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			
			while (line != null)
			{
				if (line.equals(word))
				{
					reader.close();
					return true;
				}
				
				line = reader.readLine();
			}
			
			reader.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return false;
	}
}