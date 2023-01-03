// Import nodejs file functions
const fs = require('fs');
const path = require('path');

// Import DiscordJS variables
const { SlashCommandBuilder } = require('discord.js');

const spaceFactsFile = fs.readFileSync(path.join(__dirname, '../assets/space_facts.txt'), 'utf-8');
const spaceFacts = spaceFactsFile.split('\n');

function getRandomSpaceFact() {
    return spaceFacts[Math.floor(Math.random() * spaceFacts.length)];
}

function getSpecificSpaceFact(factNum) {
    return spaceFacts[factNum];
}

module.exports = {
    data: new SlashCommandBuilder()
        .setName('joshspacefact')
        .setDescription('Gives a random space fact'),
    async execute(interaction) {

        await interaction.reply(getRandomSpaceFact());

    },
};