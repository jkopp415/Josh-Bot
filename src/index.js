// Import neccessary discord.js functions
const { Client, GatewayIntentBits, Routes } = require('discord.js');
const { REST } = require('@discordjs/rest');

// Import config items
const { token, clientId, guildId } = require('../config.json');

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
});