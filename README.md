# Player Compass

A plugin for Minecraft 1.20 that implements a player-tracking compass that can be attuned to any player currently on the server, as well as the world spawn. The compass also provides real-time updates for the exact coordinates of the tracked player. 

## How do I use it?

Download the latest release and ensure that it is placed in the `plugins` directory of your Minecraft server.

Once the plugin is in your server's `plugins` directory, you will need to restart your server. Once your server has successfully restarted, you should see a message in the console confirming that the plugin has been enabled. Once a user logs on to your server, they will be given a player-tracking compass that tracks the world spawn by default. To cycle among the players on the server, the user needs to have the compass in their hand and interact with it (right-click). If there are no players to cycle through, the user will be notified through the in-game chat. If the currently-tracked player leaves the server, the user will be notified and the compass will automatically begin tracking the world spawn.

Whenever a player loses the compass (upon death), the player will be given a new compass.

## How do I get help?

If you need help installing the plugin, have any questions, or find a bug, please contact me at: gavinmheiner@gmail.com.
