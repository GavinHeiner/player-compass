# Player Compass

A plugin for Minecraft 1.20.1 that implements a player-tracking compass that can be attuned to any player currently on the server, as well as the world spawn. The compass also provides real-time updates for the exact coordinates of the tracked player. 

## How do I use it?

Download the latest release and ensure that it is placed in the `plugins` directory of your Minecraft server.

Once the plugin is in your server's `plugins` directory, you will need to restart your server. Once your server has successfully restarted, you should see a message in the console confirming that the plugin has been enabled. To cycle among the players on the server, the user needs to have a compass in their hand and interact with it (right-click). If there are no players to cycle through, the user will be notified through the in-game chat. If the currently-tracked player leaves the server, the user will be notified and the compass will automatically begin tracking the world spawn.

Once you have restarted your server, you should see a `config.yml` with two options: `giveCompassOnRespawn` and `giveCompassToNewPlayers`. Both are `false` by default.

If you set `giveCompassToNewPlayers` to `true`, new players will be given a player-tracking compass that tracks the world spawn by default. 
If you set `giveCompassOnRespawn` to `true`, players will be given a new compass when they respawn after dying.

## How do I get help?

If you need help installing the plugin, have any questions, or find a bug, please contact me at: gavinmheiner@gmail.com.
