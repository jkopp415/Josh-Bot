// Import neccessary discord.js functions
const { Client, GatewayIntentBits, Routes } = require('discord.js');
const { REST } = require('@discordjs/rest');

// Import config items
const { 
    token, 
    clientId, 
    guildId, 
    enableComeBot, 
    enableBotPing 
} = require('../config.json');

// Import external functions
const comebot = require('./comebot.js');
const botping = require('./botping.js');

// Create the bot client
const client = new Client({
    intents: [
        GatewayIntentBits.Guilds,
        GatewayIntentBits.GuildMessages,
        GatewayIntentBits.MessageContent,
    ], 
});

// Rest function? Something with the slash commands
const rest = new REST({ version: '10' }).setToken(token);

// The list of commands
const commands = [
    {
    },
];

// Reload the list of commands
(async () => {
    try {
        console.log('Started refreshing slash commands.');
        await rest.put(Routes.applicationGuildCommands(clientId, guildId), {
            body: commands,
        });
        console.log('Successfully reloaded application (/) commands.');
    } catch (err) {
        console.log(err);
    }
})();

// Log the bot into the server
client.login(token);

// When the bot logs in, log it's username
client.on('ready', () => console.log(`The bot has logged in as "${client.user.tag}"`));

// Handles slash command reactions
client.on('interactionCreate', interaction => {
    if (!interaction.isChatInputCommand) return;
});

// Handles normal message reactions
client.on("messageCreate", message => {
    // ComeBot
    if (enableComeBot && comebot.checkComeWords(message.content))
        message.reply({ files: [comebot.nerdCatImg] });

    // Bot Ping
    if (enableBotPing && message.mentions.has(clientId))
        message.reply({ files: [botping.whoPingedMeGif] });
});