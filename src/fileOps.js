// Import nodejs file functions
const fs = require('fs');
const path = require('path');

// ---------- COME BOT ----------

// Read in and parse the list of come words
const comeWordsFile = fs.readFileSync(path.join(__dirname, '/assets/come_words.txt'), 'utf-8');
const comeWords = comeWordsFile.split('\n');

// The reaction image for the come message
const nerdCatImg = path.join(__dirname, '/assets/nerd_cat.jpg');

// Check if the given message was found in the come words list
function checkComeWords(message) {
    return comeWords.includes(message + '\r');
}

// ---------- BOT PING ----------

// The reaction image that is sent when the bot is pinged
const whoPingedMeGif = path.join(__dirname, '/assets/who_pinged_me.gif');

// ---------- BOT SHUTDOWN ----------

// The image that is sent before being shut down
const goodbyeCat = path.join(__dirname, '/assets/goodbye_cat.jpg');

// ---------- EXPORT ----------

// Export consts/functions to index.js
module.exports = { 
    nerdCatImg, checkComeWords,
    whoPingedMeGif,
    goodbyeCat,
}