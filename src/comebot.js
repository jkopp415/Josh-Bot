// Import nodejs file functions
const fs = require('fs');
const path = require('path');

// Read in and parse the list of come words
const comeWordsFile = fs.readFileSync(path.join(__dirname, '/assets/come_words.txt'), 'utf-8');
const comeWords = comeWordsFile.split('\n');

// The image that the bot uses to react to the user
const nerdCatImg = path.join(__dirname, '/assets/nerd_cat.jpg');

// Check if the given message was found in the come words list
function checkComeWords(message) {
    return comeWords.includes(message + '\r');
}

module.exports = { nerdCatImg, checkComeWords }