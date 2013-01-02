package com.github.ribesg.ncuboid.events.extensions;

import java.util.Set;

import lombok.Getter;

import org.bukkit.event.player.PlayerInteractEvent;

import com.github.ribesg.ncuboid.beans.CuboidDB;
import com.github.ribesg.ncuboid.beans.PlayerCuboid;
import com.github.ribesg.ncuboid.events.EventExtension;

public class PlayerInteractEventExtension extends EventExtension {

    @Getter private final PlayerCuboid      cuboid;
    @Getter private final Set<PlayerCuboid> cuboids;

    // Called only if event.hasBlock()
    public PlayerInteractEventExtension(final PlayerInteractEvent event) {
        super(event);
        cuboids = CuboidDB.getInstance().getAllByLoc(event.getClickedBlock().getLocation());
        cuboid = CuboidDB.getPrior(cuboids);
    }

}
