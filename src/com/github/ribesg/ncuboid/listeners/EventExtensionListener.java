package com.github.ribesg.ncuboid.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import com.github.ribesg.ncuboid.NCuboid;
import com.github.ribesg.ncuboid.events.PlayerInteractNEvent;
import com.github.ribesg.ncuboid.events.PlayerMoveBlockNEvent;

public class EventExtensionListener extends AbstractListener {

    public EventExtensionListener(final NCuboid instance) {
        super(instance);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPlayerMove(final PlayerMoveEvent event) {
        final Location from = event.getFrom(), to = event.getTo();
        if (from.getBlockX() != to.getBlockX() || from.getBlockY() != to.getBlockY() || from.getBlockZ() != to.getBlockZ()) {
            final PlayerMoveBlockNEvent newEvent = new PlayerMoveBlockNEvent(event);
            Bukkit.getServer().getPluginManager().callEvent(newEvent);
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPlayerInteract(final PlayerInteractEvent event) {
        Bukkit.getServer().getPluginManager().callEvent(new PlayerInteractNEvent(event));
    }
}
