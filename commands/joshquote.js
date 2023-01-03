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
        .setDescription('Gives a fun inspirational quote')
        .addIntegerOption(option =>
            option.setName('number')
                .setDescription(`The specific quote you wish to print, between 1 and ${quotes.length + 1}`)
                .setRequired(false)
                .setMinValue(1)
                .setMaxValue(quotes.length + 1)
        ),
    async execute(interaction) {

        const quoteNum = interaction.options.getInteger('number');
        if (quoteNum != null) {
            await interaction.reply(getSpecificQuote(quoteNum - 1));
        } else {
            await interaction.reply(getRandomQuote());
        }

    },
};