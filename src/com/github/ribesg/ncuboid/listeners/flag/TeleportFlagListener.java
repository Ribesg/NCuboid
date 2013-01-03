package com.github.ribesg.ncuboid.listeners.flag;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerTeleportEvent;

import com.github.ribesg.ncore.nodes.cuboid.beans.Flag;
import com.github.ribesg.ncuboid.NCuboid;
import com.github.ribesg.ncuboid.events.EventExtensionHandler;
import com.github.ribesg.ncuboid.events.extensions.PlayerTeleportEventExtension;
import com.github.ribesg.ncuboid.listeners.AbstractListener;

public class TeleportFlagListener extends AbstractListener {

    public TeleportFlagListener(final NCuboid instance) {
        super(instance);
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlayerTeleport(final PlayerTeleportEvent event) {
        final PlayerTeleportEventExtension ext = (PlayerTeleportEventExtension) EventExtensionHandler.get(event);
        if (ext.getFromCuboid() != null && ext.getFromCuboid().getFlag(Flag.TELEPORT) || ext.getToCuboid() != null && ext.getToCuboid().getFlag(Flag.TELEPORT)) {
            event.setCancelled(true);
        }
    }
}
