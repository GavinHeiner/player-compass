package com.gavinheiner.player_compass.runnables;

import com.gavinheiner.player_compass.PlayerCompass;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class TrackingTask {
    private final PlayerCompass plugin;
    private BukkitRunnable task;

    @Getter private final Player tracker;
    @Getter private final Player target;
    @Getter private ItemStack item;

    public TrackingTask(Player tracker, Player target, ItemStack item, PlayerCompass plugin) {
        this.plugin = plugin;
        this.tracker = tracker;
        this.target = target;
        this.item = item;

        if (target == null) trackWorldSpawn();
        else trackTarget();
    }

    private void trackWorldSpawn() {
        task = new BukkitRunnable() {
            public void run() {
                if (item != null && item.getType() == Material.COMPASS) stopTrackingWithCompass();
            }
        };

        task.runTask(plugin);
    }

    private void trackTarget() {
        task = new BukkitRunnable() {
            @Override
            public void run() {
                if (item != null && item.getType() == Material.COMPASS) startTrackingWithCompass(target.getDisplayName());
            }
        };

        task.runTaskTimer(plugin, 0L, 1L);
    }

    private void startTrackingWithCompass(String displayName) {
        CompassMeta meta = (CompassMeta) item.getItemMeta();

        if (meta != null) {
            meta.setLodestone(target.getLocation());
            meta.setLodestoneTracked(true);
            meta.addEnchant(Enchantment.LUCK, 1, true);
            meta.setDisplayName("§bTracking: " + displayName);
            meta.setLore(List.of(getLocationAsString(target.getLocation())));
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        }

        updateItemMeta(meta);
    }

    private void stopTrackingWithCompass() {
        CompassMeta meta = (CompassMeta) item.getItemMeta();

        if (meta != null) {
            meta.setLodestone(tracker.getWorld().getSpawnLocation());
            meta.setLodestoneTracked(false);
            meta.removeEnchant(Enchantment.LUCK);
            meta.setDisplayName("§fCompass");
            meta.setLore(null);
        }

        updateItemMeta(meta);
    }

    private String getLocationAsString(Location location) {
        return "§f" + Math.floor(location.getX()) + " " +
                Math.floor(location.getY()) + " " +
                Math.floor(location.getZ());
    }

    private void updateItemMeta(ItemMeta meta) {
        ItemStack[] inventory = tracker.getInventory().getContents();
        for (ItemStack itemStack : inventory) {
            if (itemStack != null && itemStack.equals(item)) {
                itemStack.setItemMeta(meta);
                item = itemStack;
            }
        }
    }

    public void cancel() {
        task.cancel();
    }
}
