package com.github.ribesg.ncuboid.events.extensions;

import java.util.Set;

import lombok.Getter;

import org.bukkit.event.player.PlayerTeleportEvent;

import com.github.ribesg.ncuboid.beans.CuboidDB;
import com.github.ribesg.ncuboid.beans.PlayerCuboid;
import com.github.ribesg.ncuboid.events.EventExtension;

public class PlayerTeleportEventExtension extends EventExtension {

    @Getter private final PlayerCuboid      fromCuboid;
    @Getter private final Set<PlayerCuboid> fromCuboids;
    @Getter private final PlayerCuboid      toCuboid;
    @Getter private final Set<PlayerCuboid> toCuboids;

    public PlayerTeleportEventExtension(final PlayerTeleportEvent event) {
        super(event);
        fromCuboids = CuboidDB.getInstance().getAllByLoc(event.getFrom());
        fromCuboid = CuboidDB.getInstance().getPrior(fromCuboids);
        toCuboids = CuboidDB.getInstance().getAllByLoc(event.getTo());
        toCuboid = CuboidDB.getInstance().getPrior(toCuboids);
    }

}
