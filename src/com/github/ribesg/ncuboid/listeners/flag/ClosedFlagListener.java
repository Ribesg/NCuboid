package com.github.ribesg.ncuboid.listeners.flag;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.github.ribesg.ncore.nodes.cuboid.beans.Flag;
import com.github.ribesg.ncuboid.events.PlayerMoveBlockNEvent;

public class ClosedFlagListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onPlayerMoveBlock(final PlayerMoveBlockNEvent event) {
        if (event.getFromCuboid() != null && event.getFromCuboid().getFlag(Flag.CLOSED) && !event.getFromCuboid().equals(event.getToCuboid())) {
            event.setCancelled(true);
        }
    }
}
