package com.github.ribesg.ncuboid.listeners.flag;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.github.ribesg.ncore.nodes.cuboid.beans.Flag;
import com.github.ribesg.ncuboid.NCuboid;
import com.github.ribesg.ncuboid.events.EventExtensionHandler;
import com.github.ribesg.ncuboid.events.extensions.PlayerJoinEventExtension;
import com.github.ribesg.ncuboid.events.extensions.PlayerMoveEventExtension;
import com.github.ribesg.ncuboid.listeners.AbstractListener;

public class GodFlagListener extends AbstractListener {

    private final Set<Player> godPlayers;

    public GodFlagListener(final NCuboid instance) {
        super(instance);
        godPlayers = new HashSet<Player>();
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPlayerMove(final PlayerMoveEvent event) {
        if (EventExtensionHandler.containsEvent(event)) {
            final PlayerMoveEventExtension ext = (PlayerMoveEventExtension) EventExtensionHandler.get(event);
            if (!ext.isCustomCancelled()) {
                if (godPlayers.contains(event.getPlayer())) {
                    if (ext.getToCuboid() == null || ext.getToCuboid() != null && !ext.getToCuboid().getFlag(Flag.GOD)) {
                        godPlayers.remove(event.getPlayer());
                    }
                } else if (ext.getToCuboid() != null && ext.getToCuboid().getFlag(Flag.GOD)) {
                    godPlayers.add(event.getPlayer());
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onEntityDamage(final EntityDamageEvent event) {
        if (event.getEntityType() == EntityType.PLAYER) {
            if (godPlayers.contains(event.getEntity())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlayerJoin(final PlayerJoinEvent event) {
        final PlayerJoinEventExtension ext = (PlayerJoinEventExtension) EventExtensionHandler.get(event);
        if (ext.getCuboid() != null && ext.getCuboid().getFlag(Flag.INVISIBLE)) {
            godPlayers.add(event.getPlayer());
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerQuit(final PlayerQuitEvent event) {
        if (godPlayers.contains(event.getPlayer())) {
            godPlayers.remove(event.getPlayer());
        }
    }
}
