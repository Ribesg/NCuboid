package com.github.ribesg.ncuboid.events.extensions;

import lombok.Getter;

import org.bukkit.event.player.PlayerDropItemEvent;

import com.github.ribesg.ncuboid.beans.CuboidDB;
import com.github.ribesg.ncuboid.beans.PlayerCuboid;
import com.github.ribesg.ncuboid.events.EventExtension;

public class PlayerDropItemEventExtension extends EventExtension {

    @Getter PlayerCuboid playerCuboid;

    public PlayerDropItemEventExtension(final PlayerDropItemEvent event) {
        super(event);
        playerCuboid = CuboidDB.getInstance().getPriorByLoc(event.getPlayer().getLocation());
    }

}
