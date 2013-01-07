package com.github.ribesg.ncuboid.events.extensions;

import java.util.Set;

import lombok.Getter;

import org.bukkit.event.player.PlayerInteractEntityEvent;

import com.github.ribesg.ncuboid.beans.CuboidDB;
import com.github.ribesg.ncuboid.beans.PlayerCuboid;
import com.github.ribesg.ncuboid.events.EventExtension;

public class PlayerInteractEntityEventExtension extends EventExtension {

    @Getter private final PlayerCuboid      cuboid;
    @Getter private final Set<PlayerCuboid> cuboids;

    public PlayerInteractEntityEventExtension(final PlayerInteractEntityEvent event) {
        super(event);
        cuboids = CuboidDB.getInstance().getAllByLoc(event.getRightClicked().getLocation());
        cuboid = CuboidDB.getInstance().getPrior(cuboids);
    }

}
