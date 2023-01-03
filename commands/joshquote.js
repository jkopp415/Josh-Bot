// Import nodejs file functions
const fs = require('fs');
const path = require('path');

// Import DiscordJS variables
const { SlashCommandBuilder } = require('discord.js');

const quotesFile = fs.readFileSync(path.join(__dirname, '../assets/quotes.txt'), 'utf-8');
const quotes = quotesFile.split('\n');

function getRandomQuote() {
    return quotes[Math.floor(Math.random() * quotes.length)];
}

function getSpecificQuote(quoteNum) {
    return quotes[quoteNum];
}

module.exports = {
    data: new SlashCommandBuilder()
        .setName('joshquote')
        .setDescription('Gives a fun inspirational quote'),
    async execute(interaction) {

        await interaction.reply(getRandomQuote());

    },
};