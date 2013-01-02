package com.github.ribesg.ncuboid.events.extensions;

import lombok.Getter;

import org.bukkit.event.player.PlayerMoveEvent;

import com.github.ribesg.ncuboid.beans.CuboidDB;
import com.github.ribesg.ncuboid.beans.PlayerCuboid;
import com.github.ribesg.ncuboid.events.EventExtension;

//This event is called when a Player change block while moving (other PlayerMoveEvent's are ignored)
//It also contains TO and FROM cuboids.
public class PlayerMoveEventExtension extends EventExtension {

    @Getter private final PlayerCuboid fromCuboid;
    @Getter private final PlayerCuboid toCuboid;

    public PlayerMoveEventExtension(final PlayerMoveEvent event) {
        super(event);
        fromCuboid = CuboidDB.getInstance().getPriorByLoc(event.getFrom());
        toCuboid = CuboidDB.getInstance().getPriorByLoc(event.getTo());
    }

}
