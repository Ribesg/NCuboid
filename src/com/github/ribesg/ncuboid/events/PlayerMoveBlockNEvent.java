package com.github.ribesg.ncuboid.events;

import lombok.Getter;

import org.bukkit.event.player.PlayerMoveEvent;

import com.github.ribesg.ncuboid.beans.CuboidDB;
import com.github.ribesg.ncuboid.beans.PlayerCuboid;

// This event is called when a Player change block while moving.
// It also contains TO and FROM cuboids.
public class PlayerMoveBlockNEvent extends PlayerMoveEvent {

    @Getter private final PlayerCuboid fromCuboid;
    @Getter private final PlayerCuboid toCuboid;

    public PlayerMoveBlockNEvent(final PlayerMoveEvent event) {
        super(event.getPlayer(), event.getFrom(), event.getTo());
        fromCuboid = CuboidDB.getInstance().getPriorByLoc(getFrom());
        toCuboid = CuboidDB.getInstance().getPriorByLoc(getTo());
    }
}
