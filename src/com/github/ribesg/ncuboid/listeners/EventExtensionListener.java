package com.github.ribesg.ncuboid.listeners;

import org.bukkit.Bukkit;
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
import com.github.ribesg.ncuboid.events.extensions.ExtendedEntityDamageEvent;
import com.github.ribesg.ncuboid.events.extensions.ExtendedEntityExplodeEvent;
import com.github.ribesg.ncuboid.events.extensions.ExtendedHangingBreakEvent;
import com.github.ribesg.ncuboid.events.extensions.ExtendedPlayerDropItemEvent;
import com.github.ribesg.ncuboid.events.extensions.ExtendedPlayerInteractEntityEvent;
import com.github.ribesg.ncuboid.events.extensions.ExtendedPlayerInteractEvent;
import com.github.ribesg.ncuboid.events.extensions.ExtendedPlayerJoinEvent;
import com.github.ribesg.ncuboid.events.extensions.ExtendedPlayerMoveEvent;
import com.github.ribesg.ncuboid.events.extensions.ExtendedPlayerTeleportEvent;

public class EventExtensionListener extends AbstractListener {

    public EventExtensionListener(final NCuboid instance) {
        super(instance);
    }

    // PlayerMoveEvent
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerMove(final PlayerMoveEvent event) {
        final Location from = event.getFrom(), to = event.getTo();
        if (from.getBlockX() != to.getBlockX() || from.getBlockY() != to.getBlockY() || from.getBlockZ() != to.getBlockZ()) {
            Bukkit.getPluginManager().callEvent(new ExtendedPlayerMoveEvent(event));
        }
    }

    // PlayerTeleportEvent
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerTeleport(final PlayerTeleportEvent event) {
        final Location from = event.getFrom(), to = event.getTo();
        if (from.getBlockX() != to.getBlockX() || from.getBlockY() != to.getBlockY() || from.getBlockZ() != to.getBlockZ() || !from.getWorld().getName().equals(to.getWorld().getName())) {
            Bukkit.getPluginManager().callEvent(new ExtendedPlayerTeleportEvent(event));
        }
    }

    // PlayerInteractEvent
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
    public void onPlayerInteract(final PlayerInteractEvent event) {
        Bukkit.getPluginManager().callEvent(new ExtendedPlayerInteractEvent(event));
    }

    // PlayerInteractEntityEvent
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerInteract(final PlayerInteractEntityEvent event) {
        Bukkit.getPluginManager().callEvent(new ExtendedPlayerInteractEntityEvent(event));
    }

    // HangingBreakEvent
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onHangingBreak(final HangingBreakEvent event) {
        Bukkit.getPluginManager().callEvent(new ExtendedHangingBreakEvent(event));
    }

    // EntityExplodeEvent
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onEntityExplode(final EntityExplodeEvent event) {
        Bukkit.getPluginManager().callEvent(new ExtendedEntityExplodeEvent(event));
    }

    // PlayerDropItemEvent
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerDropItem(final PlayerDropItemEvent event) {
        Bukkit.getPluginManager().callEvent(new ExtendedPlayerDropItemEvent(event));
    }

    // EntityDamageEvent
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onEntityDamage(final EntityDamageEvent event) {
        Bukkit.getPluginManager().callEvent(new ExtendedEntityDamageEvent(event));
    }

    // PlayerJoinEvent
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerJoin(final PlayerJoinEvent event) {
        Bukkit.getPluginManager().callEvent(new ExtendedPlayerJoinEvent(event));
    }
}
