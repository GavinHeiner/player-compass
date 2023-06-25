package com.gavinheiner.player_compass.listeners;

import com.gavinheiner.player_compass.PlayerCompass;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public class GiveCompassListener implements Listener {
    private final PlayerCompass plugin;

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();

        if (plugin.getConfiguration().getBoolean("giveCompassOnRespawn"))
            player.getInventory().addItem(new ItemStack(Material.COMPASS));
    }

    @EventHandler
    public void onPlayerFirstJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!player.hasPlayedBefore() && plugin.getConfiguration().getBoolean("giveCompassToNewPlayers"))
            player.getInventory().addItem(new ItemStack(Material.COMPASS));
    }
}
