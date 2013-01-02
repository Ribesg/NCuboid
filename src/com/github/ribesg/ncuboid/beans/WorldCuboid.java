package com.github.ribesg.ncuboid.beans;

import java.util.EnumMap;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.World;

import com.github.ribesg.ncore.nodes.cuboid.beans.Flag;

// This is a really different Cuboid type.
public class WorldCuboid extends GeneralCuboid {

    public WorldCuboid(final World world) {
        super(world, CuboidType.WORLD);
    }

    public WorldCuboid(
            final World world,
            final Set<String> allowedPlayers,
            final Set<String> allowedGroups,
            final Set<String> disallowedPlayers,
            final Set<String> disallowedGroups,
            final Set<String> disallowedCommands,
            final int priority,
            final EnumMap<Flag, Boolean> flags,
            final Integer healQuantity,
            final Integer healTimer,
            final Integer healMinimumPlayerHealth,
            final Integer healMaximumPlayerHealth,
            final Integer feedQuantity,
            final Integer feedTimer,
            final Integer feedMinimumPlayerFood,
            final Integer feedMaximumPlayerFood) {
        super(world, CuboidType.WORLD,
                allowedPlayers,
                allowedGroups,
                disallowedPlayers,
                disallowedGroups,
                disallowedCommands,
                priority,
                flags,
                healQuantity,
                healTimer,
                healMinimumPlayerHealth,
                healMaximumPlayerHealth,
                feedQuantity,
                feedTimer,
                feedMinimumPlayerFood,
                feedMaximumPlayerFood);
    }

    @Override
    public boolean contains(final Location loc) {
        return getWorld().getName().equals(loc.getWorld().getName());
    }
}
