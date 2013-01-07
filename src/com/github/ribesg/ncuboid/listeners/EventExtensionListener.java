package com.github.ribesg.ncuboid.listeners;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import com.github.ribesg.ncuboid.NCuboid;
import com.github.ribesg.ncuboid.events.EventExtensionHandler;
import com.github.ribesg.ncuboid.events.extensions.EntityDamageEventExtension;
import com.github.ribesg.ncuboid.events.extensions.EntityExplodeEventExtension;
import com.github.ribesg.ncuboid.events.extensions.HangingBreakEventExtension;
import com.github.ribesg.ncuboid.events.extensions.PlayerDropItemEventExtension;
import com.github.ribesg.ncuboid.events.extensions.PlayerInteractEntityEventExtension;
import com.github.ribesg.ncuboid.events.extensions.PlayerInteractEventExtension;
import com.github.ribesg.ncuboid.events.extensions.PlayerJoinEventExtension;
import com.github.ribesg.ncuboid.events.extensions.PlayerMoveEventExtension;
import com.github.ribesg.ncuboid.events.extensions.PlayerTeleportEventExtension;

public class EventExtensionListener extends AbstractListener {

    public EventExtensionListener(final NCuboid instance) {
        super(instance);
    }

    // PlayerMoveEvent
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

    // PlayerTeleportEvent
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerTeleportFirst(final PlayerTeleportEvent event) {
        final Location from = event.getFrom(), to = event.getTo();
        if (from.getBlockX() != to.getBlockX() || from.getBlockY() != to.getBlockY() || from.getBlockZ() != to.getBlockZ() || !from.getWorld().getName().equals(to.getWorld().getName())) {
            final PlayerTeleportEventExtension ext = new PlayerTeleportEventExtension(event);
            EventExtensionHandler.add(ext);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerTeleportFinally(final PlayerTeleportEvent event) {
        EventExtensionHandler.remove(event);
    }

    // PlayerInteractEvent
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
    public void onPlayerInteractFirst(final PlayerInteractEvent event) {
        if (event.hasBlock()) {
            EventExtensionHandler.add(new PlayerInteractEventExtension(event));
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerInteractLast(final PlayerInteractEvent event) {
        EventExtensionHandler.remove(event);
    }

    // PlayerInteractEntityEvent
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerInteractFirst(final PlayerInteractEntityEvent event) {
        EventExtensionHandler.add(new PlayerInteractEntityEventExtension(event));
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerInteractLast(final PlayerInteractEntityEvent event) {
        EventExtensionHandler.remove(event);
    }

    // HangingBreakEvent
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onHangingBreakFirst(final HangingBreakEvent event) {
        EventExtensionHandler.add(new HangingBreakEventExtension(event));
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onHangingBreakLast(final HangingBreakEvent event) {
        EventExtensionHandler.remove(event);
    }

    // EntityExplodeEvent
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onEntityExplodeFirst(final EntityExplodeEvent event) {
        EventExtensionHandler.add(new EntityExplodeEventExtension(event));
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityExplodeLast(final EntityExplodeEvent event) {
        EventExtensionHandler.remove(event);
    }

    // PlayerDropItemEvent
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerDropItemFirst(final PlayerDropItemEvent event) {
        EventExtensionHandler.add(new PlayerDropItemEventExtension(event));
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerDropItemLast(final PlayerDropItemEvent event) {
        EventExtensionHandler.remove(event);
    }

    // EntityDamageEvent
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onEntityDamageFirst(final EntityDamageEvent event) {
        EventExtensionHandler.add(new EntityDamageEventExtension(event));
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityDamageLast(final EntityDamageEvent event) {
        EventExtensionHandler.remove(event);
    }

    // PlayerJoinEvent
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerJoinFirst(final PlayerJoinEvent event) {
        EventExtensionHandler.add(new PlayerJoinEventExtension(event));
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoinLast(final PlayerJoinEvent event) {
        EventExtensionHandler.remove(event);
    }
}
