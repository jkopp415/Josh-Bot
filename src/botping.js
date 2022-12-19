// Import nodejs file functions
const path = require('path');

// The gif that the bot uses to react to the user
const whoPingedMeGif = path.join(__dirname, '/assets/who_pinged_me.gif');

// Export consts/functions to index.js
module.exports = { whoPingedMeGif };