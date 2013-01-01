package com.github.ribesg.ncuboid.beans;

import org.bukkit.Location;
import org.bukkit.World;

// This is a really different Cuboid type.
public class WorldCuboid extends GeneralCuboid {

    public WorldCuboid(final World world, final CuboidType type) {
        super(world, type);
    }

    @Override
    public boolean contains(final Location loc) {
        return getWorld().getName().equals(loc.getWorld().getName());
    }
}
