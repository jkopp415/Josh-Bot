// Import DiscordJS variables
const { SlashCommandBuilder } = require('discord.js');

module.exports = {
    data: new SlashCommandBuilder()
        .setName('joshping')
        .setDescription('Pong!'),
    async execute(interaction) {

        await interaction.reply('Pong!')

    },
};