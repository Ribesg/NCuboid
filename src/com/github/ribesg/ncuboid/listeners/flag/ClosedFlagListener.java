package com.github.ribesg.ncuboid.listeners.flag;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;

import com.github.ribesg.ncore.nodes.cuboid.beans.Flag;
import com.github.ribesg.ncuboid.NCuboid;
import com.github.ribesg.ncuboid.events.EventExtensionHandler;
import com.github.ribesg.ncuboid.events.extensions.PlayerMoveEventExtension;
import com.github.ribesg.ncuboid.listeners.AbstractListener;

public class ClosedFlagListener extends AbstractListener {

    public ClosedFlagListener(final NCuboid instance) {
        super(instance);
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlayerMoveBlock(final PlayerMoveEvent event) {
        if (EventExtensionHandler.containsEvent(event)) {
            final PlayerMoveEventExtension ext = (PlayerMoveEventExtension) EventExtensionHandler.get(event);
            if (ext.getFromCuboid() != null && ext.getFromCuboid().getFlag(Flag.CLOSED) && !ext.getFromCuboid().equals(ext.getToCuboid())) {
                event.setCancelled(true);
            }
        }
    }
}
