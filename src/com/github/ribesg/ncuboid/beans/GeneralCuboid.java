package com.github.ribesg.ncuboid.beans;

import java.util.EnumMap;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

import org.bukkit.Location;
import org.bukkit.World;

import com.github.ribesg.ncore.nodes.cuboid.beans.Cuboid;
import com.github.ribesg.ncore.nodes.cuboid.beans.Flag;

public abstract class GeneralCuboid extends Cuboid {

    public static enum CuboidType {
        RECT, // RectCuboid
        WORLD, // WorldCuboid
    }

    // Identification / informations related
    @Getter @Setter private World        world;
    @Getter @Setter private CuboidType   type;

    // Protection related
    @Getter @Setter private Set<String>  allowedPlayers;
    @Getter @Setter private Set<String>  allowedGroups;
    @Getter @Setter private Set<String>  disallowedCommands;
    @Getter @Setter private int          priority;

    // Flags related
    private final EnumMap<Flag, Boolean> flags;

    // Heal flag related
    @Getter @Setter private Integer      healQuantity;
    @Getter private Integer              healTimer;
    @Getter private Integer              healMinimumPlayerHealth;
    @Getter private Integer              healMaximumPlayerHealth;

    // Feed flag related
    @Getter @Setter private Integer      feedQuantity;
    @Getter private Integer              feedTimer;
    @Getter private Integer              feedMinimumPlayerFood;
    @Getter private Integer              feedMaximumPlayerFood;

    // Create a new Cuboid, when user select points etc
    public GeneralCuboid(final World world, final CuboidType type) {
        setWorld(world);
        setAllowedPlayers(null);
        setAllowedGroups(null);
        setDisallowedCommands(null);
        setPriority(0);
        flags = Flag.getDefaultFlagMap();
        setHealQuantity(null);
        setHealTimer(null);
        setHealMinimumPlayerHealth(null);
        setHealMaximumPlayerHealth(null);
        setFeedQuantity(null);
        setFeedTimer(null);
        setFeedMinimumPlayerFood(null);
        setFeedMaximumPlayerFood(null);
    }

    // About boolean flags
    public boolean getFlag(final Flag f) {
        return flags.get(f);
    }

    public void setFlag(final Flag f, final boolean b) {
        flags.put(f, b);
    }

    // About Heal/Feed flags values
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

    // Location check
    public abstract boolean contains(final Location loc);
}
