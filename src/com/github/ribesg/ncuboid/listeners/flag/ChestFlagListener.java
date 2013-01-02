package com.github.ribesg.ncuboid.listeners.flag;

import org.bukkit.block.Block;
import org.bukkit.entity.StorageMinecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.github.ribesg.ncore.nodes.cuboid.beans.Flag;
import com.github.ribesg.ncuboid.NCuboid;
import com.github.ribesg.ncuboid.events.EventExtensionHandler;
import com.github.ribesg.ncuboid.events.extensions.EntityExplodeEventExtension;
import com.github.ribesg.ncuboid.events.extensions.PlayerInteractEntityEventExtension;
import com.github.ribesg.ncuboid.events.extensions.PlayerInteractEventExtension;
import com.github.ribesg.ncuboid.listeners.AbstractListener;

public class ChestFlagListener extends AbstractListener {

    public ChestFlagListener(final NCuboid instance) {
        super(instance);
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    // We don't care if hasBlock()==false, so ignoreCancelled is true
    public void onPlayerInteract(final PlayerInteractEvent event) {
        if (event.hasBlock()) {
            final PlayerInteractEventExtension ext = (PlayerInteractEventExtension) EventExtensionHandler.get(event);
            if (ext.getCuboid() != null && ext.getCuboid().getFlags().get(Flag.CHEST) && !ext.getCuboid().getRights().isAllowedPlayer(event.getPlayer())) {
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
        if (ext.getCuboid() != null && ext.getCuboid().getFlags().get(Flag.CHEST) && !ext.getCuboid().getRights().isAllowedPlayer(event.getPlayer())) {
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
    public void onEntityExplode(final EntityExplodeEvent event) {
        final EntityExplodeEventExtension ext = (EntityExplodeEventExtension) EventExtensionHandler.get(event);
        for (final Block b : ext.getBlockCuboidsMap().keySet()) {
            if (ext.getBlockCuboidsMap().get(b).getFlags().get(Flag.CHEST)) {
                switch (b.getType()) {
                    case CHEST:
                    case DISPENSER:
                    case FURNACE:
                    case BURNING_FURNACE:
                    case BREWING_STAND:
                    case BEACON:
                        event.blockList().remove(b);
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
