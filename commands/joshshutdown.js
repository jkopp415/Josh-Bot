// Import nodejs file functions
const path = require('path');

// Import DiscordJS variables
const { SlashCommandBuilder } = require('discord.js');

// Import config properties
const { shutdownUser } = require('../config.json');

// The image that is sent before being shut down
const goodbyeCat = path.join(__dirname, '../assets/goodbye_cat.jpg');

module.exports = {
    data: new SlashCommandBuilder()
        .setName('joshshutdown')
        .setDescription('Shuts down the bot (if you have permission)'),
    async execute(interaction) {

        // If the user's name equals the "shutdownUser" property, then the shutdown command will work
        if (!interaction.user.name === shutdownUser) {
            interaction.reply({ content: 'You don\'t have the right permissions, stop it.', ephemeral: true });
        } else {
            interaction.reply({ content: 'Shutting down...', ephemeral: true });
            interaction.channel.send({ content: 'Shutting down. Bye!', files: [goodbyeCat] }).then(() => {
                process.exit();
            });
        }

    },
};