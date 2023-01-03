// Import DiscordJS variables
const { SlashCommandBuilder } = require('discord.js');

module.exports = {
    data: new SlashCommandBuilder()
        .setName('joshping')
        .setDescription('You know what it does...'),
    async execute(interaction) {

        await interaction.reply('Pong!')

    },
};