package com.github.ribesg.ncuboid.listeners.flag;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerDropItemEvent;

import com.github.ribesg.ncore.nodes.cuboid.beans.Flag;
import com.github.ribesg.ncuboid.NCuboid;
import com.github.ribesg.ncuboid.events.EventExtensionHandler;
import com.github.ribesg.ncuboid.events.extensions.PlayerDropItemEventExtension;
import com.github.ribesg.ncuboid.listeners.AbstractListener;

public class DropFlagListener extends AbstractListener {

    public DropFlagListener(final NCuboid instance) {
        super(instance);
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlayerDropItem(final PlayerDropItemEvent event) {
        final PlayerDropItemEventExtension ext = (PlayerDropItemEventExtension) EventExtensionHandler.get(event);
        if (ext.getPlayerCuboid() != null && ext.getPlayerCuboid().getFlag(Flag.DROP) && !ext.getPlayerCuboid().isAllowedPlayer(event.getPlayer())) {
            event.setCancelled(true);
        }
    }
}
