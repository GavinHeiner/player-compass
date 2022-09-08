package com.gavinheiner.player_compass;

import com.gavinheiner.player_compass.listeners.CancelTrackingListener;
import com.gavinheiner.player_compass.listeners.CompassListener;
import com.gavinheiner.player_compass.listeners.GiveCompassListener;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerCompass extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new CompassListener(this), this);
        getServer().getPluginManager().registerEvents(new CancelTrackingListener(this), this);
        getServer().getPluginManager().registerEvents(new GiveCompassListener(), this);

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[PlayerCompass] Plugin enabled!");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[PlayerCompass] Plugin disabled!");
    }
}
