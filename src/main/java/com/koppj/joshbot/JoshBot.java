package com.koppj.joshbot;

import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class JoshBot extends ListenerAdapter
{
	private static ClassLoader loader;						// Loads assets from the classpath
	
	private static Configuration config;					// The config object
	private static boolean devMode;							// The developer mode flag
	
	private static String token;							// The bot's token
	private static boolean enableComeBot;					// The Come Bot flag
	private static boolean enableSpaceFactsBot;				// The Space Facts Bot flag
	
	private static JDA joshBot;
	
	public static void main(String[] args) throws Exception
	{
		// Initialize the class loader
		loader = JoshBot.class.getClassLoader();
		
		// Get the config file from the classpath
		try {
			File configFile = Paths.get(loader.getResource("config.properties").toURI()).toFile();
			config = new Configurations().properties(configFile);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		// Read in some of the basic configs
		token = config.getString("joshbot.token");
		devMode = config.getBoolean("joshbot.devmode");	
		enableComeBot = config.getBoolean("joshbot.enablecomebot");
		enableSpaceFactsBot = config.getBoolean("joshbot.enablespacefactsbot");
		
		// Create the bot with the token, giving it the MESSAGE_CONTENT intent
		joshBot = JDABuilder.createLight(token)
				.addEventListeners(new SimpleCommands())
				.enableIntents(GatewayIntent.MESSAGE_CONTENT)
				.build();
		
		// Enable Come Bot based on the flag in the config
		if (enableComeBot) joshBot.addEventListener(new ComeBot());
		
		// Enable Space Facts Bot based on the flag in the config
		if (enableSpaceFactsBot) joshBot.addEventListener(new SpaceFactsBot());
	}
	
	public static InputStream getAsset(String asset) { return JoshBot.class.getClassLoader().getResourceAsStream(asset); }
	
	public static boolean isDevMode() { return devMode; }
	
	public static void shutdownBot() { joshBot.shutdown(); }
}