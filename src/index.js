// Import neccessary discord.js functions
const { Client, GatewayIntentBits, Routes } = require('discord.js');
const { REST } = require('@discordjs/rest');

// Import config items
const { 
    token, 
    clientId, 
    guildId, 
    shutdownUser, 
    enableComeBot, 
    enableBotPing 
} = require('../config.json');

// Import external functions
const botshutdown = require('./botshutdown.js');
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
        name: 'joshping',
        description: 'You know what it does...',
    },
    {
        name: 'joshshutdown',
        description: 'Shuts down the bot (if you have permission)',
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

    // Ping command
    if (interaction.commandName == 'joshping')
        interaction.reply('Pong!');

    // Bot shutdown command
    if (interaction.commandName == 'joshshutdown') {
        if (!interaction.user.name == shutdownUser)
            interaction.reply({ content: 'You don\'t have the right permissions, stop it.', ephemeral: true });
        else {
            interaction.reply({ content: 'Shutting down...', ephemeral: true });
            interaction.channel.send({ content: 'Shutting down. Bye!', files: [botshutdown.goodbyeCat] }).then(() => {
                client.destroy();
            })
        }
    }
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