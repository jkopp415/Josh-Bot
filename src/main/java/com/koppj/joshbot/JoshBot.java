package com.koppj.joshbot;

import java.io.File;
import java.io.InputStream;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class JoshBot
{
	private static File configFile;							// The config file
	private static Configuration config;					// The config object
	
	private static String token;							// The bot's token
	private static String userId;							// The bot's user id
	private static String shutdownUser;						// The username of who is allowed to shut the bot down
	private static boolean enableComeBot;					// The Come Bot flag
	private static boolean enableBotPing;					// The bot ping flag
	
	private static JDA joshBot;
	
	public static void main(String[] args) throws Exception
	{
		// TODO: Better error handling here
		// Open the config file
		try {
			configFile = new File("config.properties");
			config = new Configurations().properties(configFile);
		}  catch (NullPointerException e) {
			System.out.println("config.properties not found.");
		}
		
		// Read in some of the basic configs
		token = config.getString("joshbot.token");
		userId = config.getString("joshbot.userid");
		shutdownUser = config.getString("joshbot.shutdown.user");
		enableComeBot = config.getBoolean("joshbot.enablecomebot");
		enableBotPing = config.getBoolean("joshbot.enablebotping");
		
		// Create the bot with the token, giving it the MESSAGE_CONTENT intent
		joshBot = JDABuilder.createLight(token)
				.enableIntents(GatewayIntent.MESSAGE_CONTENT)
				.build();
		
		// Adds a list of commands to the bot
		joshBot.addEventListener(new BotCommands());
		
		// Enable Come Bot based on the flag in the config
		if (enableComeBot) joshBot.addEventListener(new ComeBot());
		
		// Enable bot ping based on the flag in the config
		if (enableBotPing) joshBot.addEventListener(new BotPing());
	}
	
	public static JDA getJoshBot() { return joshBot; }
	
	public static String getUserId() { return userId; }
	
	public static String getShutdownUser() { return shutdownUser; }
	
	public static InputStream loadAsset(String asset) { return JoshBot.class.getClassLoader().getResourceAsStream(asset); }
	
	public static void shutdownBot() { joshBot.shutdown(); }
}