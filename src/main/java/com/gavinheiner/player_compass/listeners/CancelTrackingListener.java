package com.gavinheiner.player_compass.listeners;

import com.gavinheiner.player_compass.PlayerCompass;
import com.gavinheiner.player_compass.runnables.TrackingTask;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CancelTrackingListener implements Listener {
    private static final List<TrackingTask> tasks = CompassListener.getTasks();;
    private final PlayerCompass plugin;

    @EventHandler
    public void onTargetDisconnect(PlayerQuitEvent event) {
        cancelTasksTrackingTarget(event.getPlayer());
    }

    @EventHandler
    public void onTargetChangeWorld(PlayerChangedWorldEvent event) {
        cancelTasksTrackingTarget(event.getPlayer());
    }

    private void cancelTasksTrackingTarget(Player target) {
        List<TrackingTask> tasksToCancel = new ArrayList<>();

        tasks.stream().filter(task -> target.equals(task.getTarget()))
                .forEach(tasksToCancel::add);

        tasksToCancel.forEach(task -> {
            task.cancel();
            tasks.remove(task);

            new TrackingTask(task.getTracker(), null, task.getItem(), plugin);
            task.getTracker().sendMessage("Tracked target has left your world.");
        });
    }
}
