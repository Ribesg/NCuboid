package com.github.ribesg.ncuboid.listeners.flag;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;

import com.github.ribesg.ncore.nodes.cuboid.beans.Flag;
import com.github.ribesg.ncore.nodes.cuboid.beans.FlagAtt;
import com.github.ribesg.ncuboid.NCuboid;
import com.github.ribesg.ncuboid.events.EventExtensionHandler;
import com.github.ribesg.ncuboid.events.extensions.PlayerMoveEventExtension;
import com.github.ribesg.ncuboid.listeners.AbstractListener;

public class BoosterFlagListener extends AbstractListener {

    public BoosterFlagListener(final NCuboid instance) {
        super(instance);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPlayerMoveBlock(final PlayerMoveEvent event) {
        if (EventExtensionHandler.containsEvent(event)) {
            final PlayerMoveEventExtension ext = (PlayerMoveEventExtension) EventExtensionHandler.get(event);
            if (ext.getToCuboid() != null && ext.getToCuboid().getFlags().get(Flag.BOOSTER)) {
                event.getPlayer().setVelocity(ext.getToCuboid().getFlagAtts().getVect(FlagAtt.BOOSTER_VECTOR));
            }
        }
    }
}
