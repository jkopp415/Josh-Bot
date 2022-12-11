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
	private static boolean devMode;							// The developer mode flag
	private static boolean enableComeBot;					// The Come Bot flag
	private static boolean enableSpaceFactsBot;				// The Space Facts Bot flag
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
		devMode = config.getBoolean("joshbot.devmode");	
		enableComeBot = config.getBoolean("joshbot.enablecomebot");
		enableSpaceFactsBot = config.getBoolean("joshbot.enablespacefactsbot");
		enableBotPing = config.getBoolean("joshbot.enablebotping");
		
		// Create the bot with the token, giving it the MESSAGE_CONTENT intent
		joshBot = JDABuilder.createLight(token)
				.addEventListeners(new SimpleCommands())
				.enableIntents(GatewayIntent.MESSAGE_CONTENT)
				.build();
		
		// Enable Come Bot based on the flag in the config
		if (enableComeBot) joshBot.addEventListener(new ComeBot());
		
		// Enable Space Facts Bot based on the flag in the config
		if (enableSpaceFactsBot) joshBot.addEventListener(new SpaceFactsBot());
		
		// Enable bot ping based on the flag in the config
		if (enableBotPing) joshBot.addEventListener(new BotPing());
	}
	
	public static InputStream loadAsset(String asset) { return JoshBot.class.getClassLoader().getResourceAsStream(asset); }
	
	public static String getUserId() { return userId; }
	
	public static boolean isDevMode() { return devMode; }
	
	public static void shutdownBot() { joshBot.shutdown(); }
}