package com.github.ribesg.ncuboid.beans;

import lombok.Delegate;
import lombok.Getter;
import lombok.Setter;

import org.bukkit.Location;
import org.bukkit.World;

import com.github.ribesg.ncore.nodes.cuboid.beans.Cuboid;
import com.github.ribesg.ncore.nodes.cuboid.beans.FlagAttributes;
import com.github.ribesg.ncore.nodes.cuboid.beans.Flags;
import com.github.ribesg.ncore.nodes.cuboid.beans.Rights;

public abstract class GeneralCuboid extends Cuboid {

    public static enum CuboidType {
        RECT, // RectCuboid
        WORLD, // WorldCuboid
    }

    // Identification / informations related
    @Getter @Setter private World          world;
    @Getter @Setter private CuboidType     type;

    // Protection related
    @Delegate private final Rights         rights;
    @Getter @Setter private int            priority;

    // Flags related
    @Delegate private final Flags          flags;
    @Delegate private final FlagAttributes flagAtts;

    // Create a new Cuboid, when user select points etc
    public GeneralCuboid(final World world, final CuboidType type) {
        setWorld(world);
        setType(type);
        rights = new Rights();
        setPriority(0);
        flags = new Flags();
        flagAtts = new FlagAttributes();
    }

    public GeneralCuboid(
            final World world,
            final CuboidType type,
            final Rights rights,
            final int priority,
            final Flags flags,
            final FlagAttributes flagAtts) {
        setWorld(world);
        setType(type);
        this.rights = rights;
        setPriority(priority);
        this.flags = flags;
        this.flagAtts = flagAtts;
    }

    // Location check
    public abstract boolean contains(final Location loc);
}
