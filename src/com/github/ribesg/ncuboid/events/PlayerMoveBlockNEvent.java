package com.github.ribesg.ncuboid.events;

import lombok.Getter;

import org.bukkit.event.player.PlayerMoveEvent;

import com.github.ribesg.ncuboid.beans.CuboidDB;
import com.github.ribesg.ncuboid.beans.GeneralCuboid;

public class PlayerMoveBlockNEvent extends PlayerMoveEvent {

    @Getter private final GeneralCuboid fromCuboid;
    @Getter private final GeneralCuboid toCuboid;

    public PlayerMoveBlockNEvent(final PlayerMoveEvent event) {
        super(event.getPlayer(), event.getFrom(), event.getTo());
        fromCuboid = CuboidDB.getInstance().get(getFrom());
        toCuboid = CuboidDB.getInstance().get(getTo());
    }
}
