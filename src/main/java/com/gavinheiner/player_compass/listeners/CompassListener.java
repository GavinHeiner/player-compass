package com.gavinheiner.player_compass.listeners;

import com.gavinheiner.player_compass.PlayerCompass;
import com.gavinheiner.player_compass.runnables.TrackingTask;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CompassListener implements Listener {
    @Getter private static final List<TrackingTask> tasks = new ArrayList<>();
    private final PlayerCompass plugin;

    private List<Player> targets;
    private Player currentTarget;
    private Player tracker;
    private ItemStack item;

    @EventHandler
    public void onCompassClick(PlayerInteractEvent event) {
        if (event.getItem() == null || event.getItem().getType() != Material.COMPASS) return;

        tracker = event.getPlayer();
        targets = getTargets();
        item = event.getItem();

        cancelCurrentTaskForTracker();
        track();
    }

    private List<Player> getTargets() {
        List<Player> players = tracker.getWorld().getPlayers();
        players.remove(tracker);

        return players;
    }

    private void cancelCurrentTaskForTracker() {
        TrackingTask task = getTaskForTracker(tracker);

        if (task != null) {
            task.cancel();
            tasks.remove(getTaskForTracker(tracker));

            currentTarget = task.getTarget();
        }
        else currentTarget = null;
    }

    private TrackingTask getTaskForTracker(Player tracker) {
        for (TrackingTask task : tasks)
            if (task.getTracker().equals(tracker))
                return task;

        return null;
    }

    private void track() {
        if (currentTarget == null) trackFirstTarget();
        else trackNextTarget();
    }

    private void trackFirstTarget() {
        try {
            createTrackingTaskForTarget(targets.get(0));
        } catch (IndexOutOfBoundsException exception) {
            createTrackingTaskForTarget(null);
            tracker.sendMessage("Could not find a player to track in your world.");
        }
    }

    private void trackNextTarget() {
        try {
            createTrackingTaskForTarget(targets.get(targets.indexOf(currentTarget) + 1));
        } catch (IndexOutOfBoundsException exception) {
            createTrackingTaskForTarget(null);
        }
    }

    private void createTrackingTaskForTarget(Player target) {
        TrackingTask task = new TrackingTask(tracker, target, item, plugin);

        if (target != null)
            tasks.add(task);
    }
}
