const { REST, Routes } = require('discord.js');
const { token, clientId, guildId } = require('./config.json');
const fs = require('node:fs');

const commands = [];
const commandFiles = fs.readdirSync('./commands').filter(file => file.endsWith('.js'));

for (const file of commandFiles) {
    const command = require(`./commands/${file}`);
    commands.push(command.data.toJSON());
}

const rest = new REST({ version: '10' }).setToken(token);

(async () => {
   try {
       console.log(`Started refreshing ${commands.length} slash commands.`);

       const guildData = await rest.put(
               Routes.applicationGuildCommands(clientId, guildId),
               { body: commands },
       );

       const globalData = await rest.put(
               Routes.applicationCommands(clientId),
               { body: [] }
       );

       console.log(`Successfully reloaded ${guildData.length} guild slash commands and ${globalData.length} global slash commands.`);
   } catch (error) {
       console.error(error);
   }
})();