package com.github.ribesg.ncuboid.beans;

import java.util.EnumMap;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

import com.github.ribesg.ncore.nodes.cuboid.beans.Flag;

public abstract class PlayerCuboid extends GeneralCuboid {

    public static enum CuboidState {
        NORMAL, // Normal Cuboid
        TMPSTATE1, // First point selected
        TMPSTATE2 // All points selected, waiting for "/cuboid create" command
    }

    // Identification / informations related
    @Getter @Setter private String        cuboidName;
    @Getter @Setter private String        ownerName;
    @Getter @Setter private CuboidState   state;
    @Getter @Setter private long          totalSize;
    @Getter @Setter private String        welcomeMessage;
    @Getter @Setter private String        farewellMessage;
    @Getter @Setter private Set<ChunkKey> chunks;

    // Flags related
    @Getter @Setter private Location      passPoint;      // For Pass flag
    @Getter @Setter private Location      closedPoint;    // For Closed flag
    @Getter @Setter private Vector        boosterVector;  // For Booster flag

    // Create a new Cuboid, when user select points etc
    public PlayerCuboid(final String cuboidName, final String ownerName, final World world, final CuboidType type) {
        super(world, type);
        setCuboidName(cuboidName);
        setOwnerName(ownerName);
        setState(CuboidState.TMPSTATE1);
        setWelcomeMessage(null);
        setFarewellMessage(null);
        setPassPoint(null);
        setClosedPoint(null);
        setBoosterVector(null);
    }

    // Create a Cuboid from a save
    public PlayerCuboid(
            final String cuboidName,
            final String ownerName,
            final World world,
            final CuboidState state,
            final long totalSize,
            final String welcomeMessage,
            final String farewellMessage,
            final Set<ChunkKey> chunks,
            final CuboidType type,
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
            final Integer healMaximumPlayerHealth,
            final Integer feedQuantity,
            final Integer feedTimer,
            final Integer feedMinimumPlayerFood,
            final Integer feedMaximumPlayerFood) {

        super(world,
                type,
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
        setCuboidName(cuboidName);
        setOwnerName(ownerName);
        setState(state);
        setTotalSize(totalSize);
        setWelcomeMessage(welcomeMessage);
        setFarewellMessage(farewellMessage);
        setChunks(chunks);
        setPassPoint(passPoint);
        setClosedPoint(closedPoint);
        setBoosterVector(boosterVecor);
    }

    // Location check
    @Override
    public boolean contains(final Location loc) {
        return loc.getWorld().getName().equals(getWorld().getName()) && contains(loc.getX(), loc.getY(), loc.getZ());
    }

    public abstract boolean contains(final double x, final double y, final double z);

    public String getInfoLine() {
        return "- " + getCuboidName() + " (" + getOwnerName() + ") " + getSizeString();
    }

    public abstract String getSizeString();
}
