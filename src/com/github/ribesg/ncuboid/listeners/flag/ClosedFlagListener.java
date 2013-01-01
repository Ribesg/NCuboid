package com.github.ribesg.ncuboid.listeners.flag;

import org.bukkit.event.EventHandler;

import com.github.ribesg.ncore.nodes.cuboid.beans.Flag;
import com.github.ribesg.ncuboid.NCuboid;
import com.github.ribesg.ncuboid.events.PlayerMoveBlockNEvent;
import com.github.ribesg.ncuboid.listeners.AbstractListener;

public class ClosedFlagListener extends AbstractListener {

    public ClosedFlagListener(final NCuboid instance) {
        super(instance);
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerMoveBlock(final PlayerMoveBlockNEvent event) {
        if (event.getFromCuboid() != null && event.getFromCuboid().getFlag(Flag.CLOSED) && !event.getFromCuboid().equals(event.getToCuboid())) {
            event.setCancelled(true);
        }
    }
}
