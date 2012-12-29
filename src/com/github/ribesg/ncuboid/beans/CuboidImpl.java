package com.github.ribesg.ncuboid.beans;

import java.util.EnumMap;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

import com.github.ribesg.ncore.nodes.cuboid.Cuboid;
import com.github.ribesg.ncore.nodes.cuboid.Flag;

public abstract class CuboidImpl implements Cuboid {

    public static enum CuboidState {
        NORMAL, // Normal Cuboid
        TMPSTATE1, // First point selected
        TMPSTATE2 // All points selected, waiting for "/cuboid create" command
    }

    // Identification / informations related
    @Getter @Setter private String        cuboidName;
    @Getter @Setter private String        ownerName;
    @Getter @Setter private World         world;
    @Getter @Setter private CuboidState   state;
    @Getter @Setter private long          totalSize;
    @Getter @Setter private String        welcomeMessage;
    @Getter @Setter private String        farewellMessage;
    @Getter @Setter private Set<ChunkKey> chunks;

    // Protection related
    @Getter @Setter private Set<String>   allowedPlayers;
    @Getter @Setter private Set<String>   allowedGroups;
    @Getter @Setter private Set<String>   disallowedCommands;
    @Getter @Setter private int           priority;

    // Flags related (some vars)
    private final EnumMap<Flag, Boolean>  flags;
    @Getter @Setter private Location      passPoint;              // For Pass flag
    @Getter @Setter private Location      closedPoint;            // For Closed flag
    @Getter @Setter private Vector        boosterVector;          // For Booster flag

    // Heal flag related
    @Getter @Setter private Integer       healQuantity;
    @Getter private Integer               healTimer;
    @Getter private Integer               healMinimumPlayerHealth;
    @Getter private Integer               healMaximumPlayerHealth;

    // Feed flag related
    @Getter @Setter private Integer       feedQuantity;
    @Getter private Integer               feedTimer;
    @Getter private Integer               feedMinimumPlayerFood;
    @Getter private Integer               feedMaximumPlayerFood;

    // Create a new Cuboid, when user select points etc
    public CuboidImpl(final String cuboidName, final String ownerName, final World world) {

        setCuboidName(cuboidName);
        setOwnerName(ownerName);
        setWorld(world);
        setState(CuboidState.TMPSTATE1);
        setWelcomeMessage(null);
        setFarewellMessage(null);
        setAllowedPlayers(null);
        setAllowedGroups(null);
        setDisallowedCommands(null);
        setPriority(0);
        flags = Flag.getDefaultFlagMap();
        setPassPoint(null);
        setClosedPoint(null);
        setBoosterVector(null);
        setHealQuantity(null);
        setHealTimer(null);
        setHealMinimumPlayerHealth(null);
        setHealMaximumPlayerHealth(null);
        setFeedQuantity(null);
        setFeedTimer(null);
        setFeedMinimumPlayerFood(null);
        setFeedMaximumPlayerFood(null);
    }

    // Create a Cuboid from a save
    public CuboidImpl(
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
            final Integer feedMaximumPlayerFood) {

        setCuboidName(cuboidName);
        setOwnerName(ownerName);
        setWorld(world);
        setState(state);
        setTotalSize(totalSize);
        setWelcomeMessage(welcomeMessage);
        setFarewellMessage(farewellMessage);
        setChunks(chunks);
        setAllowedPlayers(allowedPlayers);
        setAllowedGroups(allowedGroups);
        setDisallowedCommands(disallowedCommands);
        setPriority(priority);
        this.flags = flags;
        setPassPoint(passPoint);
        setClosedPoint(closedPoint);
        setBoosterVector(boosterVecor);
        setHealQuantity(healQuantity);
        setHealTimer(healTimer);
        setHealMinimumPlayerHealth(healMinimumPlayerHealth);
        setHealMaximumPlayerHealth(healMaximumPlayerFood);
        setFeedQuantity(feedQuantity);
        setFeedTimer(feedTimer);
        setFeedMinimumPlayerFood(feedMinimumPlayerFood);
        setFeedMaximumPlayerFood(feedMinimumPlayerFood);
    }

    public boolean getFlag(final Flag f) {
        return flags.get(f);
    }

    public void setFlag(final Flag f, final boolean b) {
        flags.put(f, b);
    }

    public void setHealTimer(Integer healTimer) {
        if (healTimer >= 5) {
            this.healTimer = healTimer;
        } else {
            healTimer = 5;
        }
    }

    public void setHealMinimumPlayerHealth(final Integer healMinimumPlayerHealth) {
        if (healMinimumPlayerHealth <= 0) {
            this.healMinimumPlayerHealth = 0;
        } else if (healMinimumPlayerHealth >= 20) {
            this.healMinimumPlayerHealth = 20;
        } else {
            this.healMinimumPlayerHealth = healMinimumPlayerHealth;
        }
    }

    public void setHealMaximumPlayerHealth(final Integer healMaximumPlayerHealth) {
        if (healMaximumPlayerHealth <= 0) {
            this.healMaximumPlayerHealth = 0;
        } else if (healMaximumPlayerHealth >= 20) {
            this.healMaximumPlayerHealth = 20;
        } else {
            this.healMaximumPlayerHealth = healMaximumPlayerHealth;
        }
    }

    public void setFeedTimer(final Integer feedTimer) {
        if (feedTimer >= 5) {
            this.feedTimer = feedTimer;
        } else {
            this.feedTimer = 5;
        }
    }

    public void setFeedMinimumPlayerFood(final Integer feedMinimumPlayerFood) {
        if (feedMinimumPlayerFood <= 0) {
            this.feedMinimumPlayerFood = 0;
        } else if (feedMinimumPlayerFood >= 20) {
            this.feedMinimumPlayerFood = 20;
        } else {
            this.feedMinimumPlayerFood = feedMinimumPlayerFood;
        }
    }

    public void setFeedMaximumPlayerFood(final Integer feedMaximumPlayerFood) {
        if (feedMaximumPlayerFood <= 0) {
            this.feedMaximumPlayerFood = 0;
        } else if (feedMaximumPlayerFood >= 20) {
            this.feedMaximumPlayerFood = 20;
        } else {
            this.feedMaximumPlayerFood = feedMaximumPlayerFood;
        }
    }

    public abstract boolean contains(final Location loc);

    public abstract boolean contains(final double x, final double y, final double z);
}
