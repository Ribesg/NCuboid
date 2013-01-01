package com.github.ribesg.ncuboid.listeners.flag;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

import com.github.ribesg.ncore.nodes.cuboid.beans.Flag;
import com.github.ribesg.ncuboid.NCuboid;
import com.github.ribesg.ncuboid.events.PlayerMoveBlockNEvent;
import com.github.ribesg.ncuboid.listeners.AbstractListener;

public class BoosterFlagListener extends AbstractListener {

    public BoosterFlagListener(final NCuboid instance) {
        super(instance);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPlayerMoveBlock(final PlayerMoveBlockNEvent event) {
        if (event.getToCuboid() != null && event.getToCuboid().getFlag(Flag.BOOSTER)) {
            event.getPlayer().setVelocity(event.getToCuboid().getBoosterVector());
        }
    }
}
