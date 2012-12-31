package com.github.ribesg.ncuboid.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;

import com.github.ribesg.ncuboid.events.PlayerInteractNEvent;

public class PlayerStickListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onPlayerInteract(final PlayerInteractNEvent event) {
        if (event.hasItem() && event.getItem().getType() == Material.STICK) {
            final Action action = event.getAction();
            switch (action) {
                case RIGHT_CLICK_BLOCK:
                    break;
                case LEFT_CLICK_BLOCK:
                    break;
                case RIGHT_CLICK_AIR:
                case LEFT_CLICK_AIR:
                    break;
                default:
                    return;
            }
        }
    }
}
