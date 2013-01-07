package com.github.ribesg.ncuboid.beans;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

import org.bukkit.Location;
import org.bukkit.World;

import com.github.ribesg.ncore.nodes.cuboid.beans.FlagAttributes;
import com.github.ribesg.ncore.nodes.cuboid.beans.Flags;
import com.github.ribesg.ncore.nodes.cuboid.beans.Rights;

public class RectCuboid extends PlayerCuboid {
    @Getter @Setter private Location minCorner, maxCorner;
    @Getter @Setter private int      minX, maxX, minY, maxY, minZ, maxZ;

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
            final Rights rights,
            final int priority,
            final Flags flags,
            final FlagAttributes flagAtts,
            final Location minCorner,
            final Location maxCorner) {

        super(cuboidName,
                ownerName,
                world,
                state,
                totalSize,
                welcomeMessage,
                farewellMessage,
                chunks,
                CuboidType.RECT,
                rights,
                priority,
                flags,
                flagAtts);

        setMinCorner(minCorner);
        setMaxCorner(maxCorner);
        setMinX(minCorner.getBlockX());
        setMaxX(maxCorner.getBlockX());
        setMinY(minCorner.getBlockY());
        setMaxY(maxCorner.getBlockY());
        setMinZ(minCorner.getBlockZ());
        setMaxZ(maxCorner.getBlockZ());
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
        return maxX - minX + 1 + "x" + (maxY - minY + 1) + "x" + (maxZ - minZ + 1);
    }
}
