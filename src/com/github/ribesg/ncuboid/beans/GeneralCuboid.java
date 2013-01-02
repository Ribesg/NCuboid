package com.github.ribesg.ncuboid.beans;

import java.util.EnumMap;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.github.ribesg.ncore.Permissions;
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
    @Getter @Setter private Set<String>  disallowedPlayers;
    @Getter @Setter private Set<String>  disallowedGroups;
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
        setType(type);
        setPriority(0);
        flags = Flag.getDefaultFlagMap();
    }

    public GeneralCuboid(
            final World world,
            final CuboidType type,
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
        setWorld(world);
        setType(type);
        setAllowedPlayers(allowedPlayers);
        setAllowedGroups(allowedGroups);
        setDisallowedPlayers(disallowedPlayers);
        setDisallowedGroups(disallowedGroups);
        setDisallowedCommands(disallowedCommands);
        setPriority(priority);
        this.flags = flags;
        setHealQuantity(healQuantity);
        setHealTimer(healTimer);
        setHealMinimumPlayerHealth(healMinimumPlayerHealth);
        setHealMaximumPlayerHealth(healMaximumPlayerHealth);
        setFeedQuantity(feedQuantity);
        setFeedTimer(feedTimer);
        setFeedMinimumPlayerFood(feedMinimumPlayerFood);
        setFeedMaximumPlayerFood(feedMaximumPlayerFood);
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

    // Permissions check
    public boolean isAllowed(final Player p) {
        // TODO Handle Groups
        return p.isOp() || p.hasPermission(Permissions.ADMIN) || isAllowedPlayerName(p.getName());
    }

    public boolean isAllowedPlayerName(final String playerName) {
        return (disallowedPlayers == null || disallowedPlayers.contains(playerName)) && (allowedPlayers == null || allowedPlayers.contains(playerName));
    }

    public boolean isAllowedGroupName(final String groupName) {
        return (disallowedGroups == null || disallowedGroups.contains(groupName)) && (allowedGroups == null || allowedGroups.contains(groupName));
    }
}
