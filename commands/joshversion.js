// Import DiscordJS variables
const { SlashCommandBuilder } = require('discord.js');

// Import the package.json file
const pjson = require('../package.json')

module.exports = {
    data: new SlashCommandBuilder()
        .setName('joshversion')
        .setDescription('Returns the current version of the bot'),
    async execute(interaction) {

        await interaction.reply(`I'm currently on version ${pjson.version}`)

    },
};