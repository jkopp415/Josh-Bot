// Import nodejs file functions
const path = require('path');

// The image that the bot sends before being shut down
const goodbyeCat = path.join(__dirname, '/assets/goodbye_cat.jpg');

// Export consts/functions to index.js
module.exports = { goodbyeCat };