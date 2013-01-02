package com.github.ribesg.ncuboid.events.extensions;

import java.util.Set;

import lombok.Getter;

import org.bukkit.event.hanging.HangingBreakEvent;

import com.github.ribesg.ncuboid.beans.CuboidDB;
import com.github.ribesg.ncuboid.beans.PlayerCuboid;
import com.github.ribesg.ncuboid.events.EventExtension;

public class HangingBreakEventExtension extends EventExtension {

    @Getter private final PlayerCuboid      cuboid;
    @Getter private final Set<PlayerCuboid> cuboids;

    public HangingBreakEventExtension(final HangingBreakEvent event) {
        super(event);
        cuboids = CuboidDB.getInstance().getAllByLoc(event.getEntity().getLocation());
        cuboid = CuboidDB.getPrior(cuboids);
    }

}
