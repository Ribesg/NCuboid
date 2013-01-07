package com.github.ribesg.ncuboid.listeners.flag;

import org.bukkit.entity.StorageMinecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import com.github.ribesg.ncore.nodes.cuboid.beans.Flag;
import com.github.ribesg.ncuboid.NCuboid;
import com.github.ribesg.ncuboid.events.EventExtensionHandler;
import com.github.ribesg.ncuboid.events.extensions.PlayerDropItemEventExtension;
import com.github.ribesg.ncuboid.events.extensions.PlayerInteractEntityEventExtension;
import com.github.ribesg.ncuboid.events.extensions.PlayerInteractEventExtension;
import com.github.ribesg.ncuboid.events.extensions.PlayerMoveEventExtension;
import com.github.ribesg.ncuboid.listeners.AbstractListener;

public class CreativeFlagListener extends AbstractListener {

    public CreativeFlagListener(final NCuboid instance) {
        super(instance);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPlayerMoveBlock(final PlayerMoveEvent event) {
        if (EventExtensionHandler.containsEvent(event)) {
            final PlayerMoveEventExtension ext = (PlayerMoveEventExtension) EventExtensionHandler.get(event);
            if (!ext.isCustomCancelled()) {
                // TODO Handle player GameMode
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    // We don't care if hasBlock()==false, so ignoreCancelled is true
    public void onPlayerInteract(final PlayerInteractEvent event) {
        if (event.hasBlock()) {
            final PlayerInteractEventExtension ext = (PlayerInteractEventExtension) EventExtensionHandler.get(event);
            if (ext.getCuboid() != null && ext.getCuboid().getFlag(Flag.CREATIVE)) {
                switch (event.getClickedBlock().getType()) {
                    case CHEST:
                    case DISPENSER:
                    case FURNACE:
                    case BURNING_FURNACE:
                    case BREWING_STAND:
                    case BEACON:
                        event.setCancelled(true);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlayerInteractEntity(final PlayerInteractEntityEvent event) {
        final PlayerInteractEntityEventExtension ext = (PlayerInteractEntityEventExtension) EventExtensionHandler.get(event);
        if (ext.getCuboid() != null && ext.getCuboid().getFlag(Flag.CHEST)) {
            switch (event.getRightClicked().getType()) {
                case ITEM_FRAME:
                    event.setCancelled(true);
                    break;
                case MINECART:
                    event.setCancelled(event.getRightClicked() instanceof StorageMinecart);
                    break;
                default:
                    break;
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlayerDropItem(final PlayerDropItemEvent event) {
        final PlayerDropItemEventExtension ext = (PlayerDropItemEventExtension) EventExtensionHandler.get(event);
        if (ext.getPlayerCuboid() != null && ext.getPlayerCuboid().getFlag(Flag.CREATIVE)) {
            event.setCancelled(true);
        }
    }
}
