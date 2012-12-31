package com.github.ribesg.ncuboid.events;

import lombok.Getter;

import org.bukkit.event.player.PlayerInteractEvent;

import com.github.ribesg.ncuboid.beans.CuboidDB;
import com.github.ribesg.ncuboid.beans.GeneralCuboid;

public class PlayerInteractNEvent extends PlayerInteractEvent {
    @Getter private final GeneralCuboid cuboid;

    public PlayerInteractNEvent(final PlayerInteractEvent event) {
        super(event.getPlayer(), event.getAction(), event.getItem(), event.getClickedBlock(), event.getBlockFace());
        cuboid = event.hasBlock() ? CuboidDB.getInstance().get(event.getClickedBlock().getLocation()) : null;
    }
}
