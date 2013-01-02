package com.github.ribesg.ncuboid.beans;

import java.util.EnumMap;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

import com.github.ribesg.ncore.nodes.cuboid.beans.Flag;

public class RectCuboid extends PlayerCuboid {
    @Getter @Setter private Location minCorner, maxCorner;
    @Getter @Setter private long     minX, maxX, minY, maxY, minZ, maxZ;

    // Create a new Rectangular Cuboid
    public RectCuboid(final String cuboidName, final String ownerName, final World world, final Location minCorner) {

        super(cuboidName, ownerName, world, CuboidType.RECT);

        setMinCorner(minCorner);
        setChunks(null);
    }

    // Create a Rectangular Cuboid from a save
    public RectCuboid(
            final String cuboidName,
            final String ownerName,
            final World world,
            final CuboidState state,
            final long totalSize,
            final String welcomeMessage,
            final String farewellMessage,
            final Set<ChunkKey> chunks,
            final Set<String> allowedPlayers,
            final Set<String> allowedGroups,
            final Set<String> disallowedPlayers,
            final Set<String> disallowedGroups,
            final Set<String> disallowedCommands,
            final int priority,
            final EnumMap<Flag, Boolean> flags,
            final Location passPoint,
            final Location closedPoint,
            final Vector boosterVecor,
            final Integer healQuantity,
            final Integer healTimer,
            final Integer healMinimumPlayerHealth,
            final Integer healMaximumPlayerFood,
            final Integer feedQuantity,
            final Integer feedTimer,
            final Integer feedMinimumPlayerFood,
            final Integer feedMaximumPlayerFood,
            final Location minCorner,
            final Location maxCorner,
            final long minX,
            final long maxX,
            final long minY,
            final long maxY,
            final long minZ,
            final long maxZ) {

        super(cuboidName,
                ownerName,
                world,
                state,
                totalSize,
                welcomeMessage,
                farewellMessage,
                chunks,
                CuboidType.RECT,
                allowedPlayers,
                allowedGroups,
                disallowedPlayers,
                disallowedGroups,
                disallowedCommands,
                priority,
                flags,
                passPoint,
                closedPoint,
                boosterVecor,
                healQuantity,
                healTimer,
                healMinimumPlayerHealth,
                healMaximumPlayerFood,
                feedQuantity,
                feedTimer,
                feedMinimumPlayerFood,
                feedMaximumPlayerFood);

        setMinCorner(minCorner);
        setMaxCorner(maxCorner);
        setMinX(minX);
        setMaxX(maxX);
        setMinY(minY);
        setMaxY(maxY);
        setMinZ(minZ);
        setMaxZ(maxZ);
    }

    // The player select the second corner
    public void secondPoint(final Location secondPoint) {
        if (secondPoint.getWorld().getName().equals(getWorld().getName())) {
            setMinX(getMinCorner().getBlockX() < secondPoint.getBlockX() ? getMinCorner().getBlockX() : secondPoint.getBlockX());
            setMinY(getMinCorner().getBlockY() < secondPoint.getBlockY() ? getMinCorner().getBlockY() : secondPoint.getBlockY());
            setMinZ(getMinCorner().getBlockZ() < secondPoint.getBlockZ() ? getMinCorner().getBlockZ() : secondPoint.getBlockZ());
            setMaxX(getMinX() == secondPoint.getBlockX() ? getMinCorner().getBlockX() : secondPoint.getBlockX());
            setMaxY(getMinY() == secondPoint.getBlockY() ? getMinCorner().getBlockY() : secondPoint.getBlockY());
            setMaxZ(getMinZ() == secondPoint.getBlockZ() ? getMinCorner().getBlockZ() : secondPoint.getBlockZ());
            setMinCorner(new Location(getWorld(), getMinX(), getMinY(), getMinZ()));
            setMaxCorner(new Location(getWorld(), getMaxX(), getMaxY(), getMaxZ()));
            setState(CuboidState.TMPSTATE2);
        }
    }

    // Check if <x,y,z> is in a Cuboid
    @Override
    public boolean contains(final double x, final double y, final double z) {
        // Do not use getters here to be faster
        return minX <= x && maxX + 1 > x && minY <= y && maxY + 1 > y && minZ <= z && maxZ + 1 > z;
    }

    @Override
    public String getSizeString() {
        return new StringBuilder().append(maxX - minX).append('x').append(maxY - minY).append('x').append(maxZ - minZ).toString();
    }
}
