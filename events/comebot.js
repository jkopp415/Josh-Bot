// Import nodejs file functions
const fs = require('fs');
const path = require('path');

// Import DiscordJS variables
const { Events } = require('discord.js');

// Import config properties
const { enableComeBot } = require('../config.json');

// Read in and parse the list of come words
const comeWordsFile = fs.readFileSync(path.join(__dirname, '../assets/come_words.txt'), 'utf-8');
const comeWords = comeWordsFile.split('\n');

// The reaction image for the come message
const nerdCatImg = path.join(__dirname, '../assets/nerd_cat.jpg');

// Check if the given message was found in the come words list
function checkComeWords(message) {
    return comeWords.includes(message + '\r');
}

module.exports = {
    name: Events.MessageCreate,
    async execute(interaction) {

        // If ComeBot is enabled and the message is one of the come words, respond with the image
        if (enableComeBot && checkComeWords(interaction.content))
            await interaction.reply({ files: [nerdCatImg] });

    }
}