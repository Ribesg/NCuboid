package com.github.ribesg.ncuboid.listeners;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import com.github.ribesg.ncuboid.NCuboid;
import com.github.ribesg.ncuboid.events.EventExtensionHandler;
import com.github.ribesg.ncuboid.events.extensions.HangingBreakEventExtension;
import com.github.ribesg.ncuboid.events.extensions.PlayerInteractEventExtension;
import com.github.ribesg.ncuboid.events.extensions.PlayerMoveEventExtension;

public class EventExtensionListener extends AbstractListener {

    public EventExtensionListener(final NCuboid instance) {
        super(instance);
    }

    // PlayerMoveBlockEvent
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerMoveFirst(final PlayerMoveEvent event) {
        final Location from = event.getFrom(), to = event.getTo();
        if (from.getBlockX() != to.getBlockX() || from.getBlockY() != to.getBlockY() || from.getBlockZ() != to.getBlockZ()) {
            final PlayerMoveEventExtension ext = new PlayerMoveEventExtension(event);
            EventExtensionHandler.add(ext);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerMoveFinally(final PlayerMoveEvent event) {
        EventExtensionHandler.remove(event);
    }

    // PlayerInteractEvent
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerInteractFirst(final PlayerInteractEvent event) {
        if (event.hasBlock()) {
            EventExtensionHandler.add(new PlayerInteractEventExtension(event));
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerInteractLast(final PlayerInteractEvent event) {
        EventExtensionHandler.remove(event);
    }

    // HangingBreakEvent
    @EventHandler(priority = EventPriority.LOWEST)
    public void onHangingBreakFirst(final HangingBreakEvent event) {
        EventExtensionHandler.add(new HangingBreakEventExtension(event));
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onHangingBreakLast(final HangingBreakEvent event) {
        EventExtensionHandler.remove(event);
    }
}
