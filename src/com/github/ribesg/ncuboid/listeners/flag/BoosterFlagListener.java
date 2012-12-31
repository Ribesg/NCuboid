package com.github.ribesg.ncuboid.listeners.flag;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.github.ribesg.ncore.nodes.cuboid.beans.Flag;
import com.github.ribesg.ncuboid.events.PlayerMoveBlockNEvent;

public class BoosterFlagListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPlayerMoveBlock(final PlayerMoveBlockNEvent event) {
        if (event.getToCuboid() != null && event.getToCuboid().getFlag(Flag.BOOSTER)) {
            event.getPlayer().setVelocity(event.getToCuboid().getBoosterVector());
        }
    }
}
