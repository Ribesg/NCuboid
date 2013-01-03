package com.github.ribesg.ncuboid.events.extensions;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

import org.bukkit.event.player.PlayerMoveEvent;

import com.github.ribesg.ncuboid.beans.CuboidDB;
import com.github.ribesg.ncuboid.beans.PlayerCuboid;
import com.github.ribesg.ncuboid.events.EventExtension;

//This event is called when a Player change block while moving (other PlayerMoveEvent's are ignored)
//It also contains TO and FROM cuboids.
public class PlayerMoveEventExtension extends EventExtension {

    @Getter private final PlayerCuboid      fromCuboid;
    @Getter private final Set<PlayerCuboid> fromCuboids;
    @Getter private final PlayerCuboid      toCuboid;
    @Getter private final Set<PlayerCuboid> toCuboids;
    @Setter private boolean                 customCancelled;

    public PlayerMoveEventExtension(final PlayerMoveEvent event) {
        super(event);
        fromCuboids = CuboidDB.getInstance().getAllByLoc(event.getFrom());
        fromCuboid = CuboidDB.getInstance().getPrior(fromCuboids);
        toCuboids = CuboidDB.getInstance().getAllByLoc(event.getTo());
        toCuboid = CuboidDB.getInstance().getPrior(toCuboids);
        customCancelled = false;
    }

    public boolean isCustomCancelled() {
        return customCancelled;
    }

}
