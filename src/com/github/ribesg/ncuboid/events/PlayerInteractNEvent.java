package com.github.ribesg.ncuboid.events;

import java.util.Set;

import lombok.Getter;

import org.bukkit.Location;
import org.bukkit.event.player.PlayerInteractEvent;

import com.github.ribesg.ncuboid.beans.CuboidDB;
import com.github.ribesg.ncuboid.beans.PlayerCuboid;

// This event contains informations about cuboids clicked
public class PlayerInteractNEvent extends PlayerInteractEvent {
    @Getter private final Location          clickedBlockLocation;
    @Getter private final PlayerCuboid      cuboid;
    @Getter private final Set<PlayerCuboid> cuboids;

    public PlayerInteractNEvent(final PlayerInteractEvent event) {
        super(event.getPlayer(), event.getAction(), event.getItem(), event.getClickedBlock(), event.getBlockFace());
        if (event.hasBlock()) {
            clickedBlockLocation = event.getClickedBlock().getLocation();
            cuboids = CuboidDB.getInstance().getAllByLoc(clickedBlockLocation);
            cuboid = CuboidDB.getPrior(cuboids);
        } else {
            clickedBlockLocation = null;
            cuboids = null;
            cuboid = null;
        }
    }
}
