package com.github.ribesg.ncuboid.listeners.flag;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.event.world.StructureGrowEvent;

import com.github.ribesg.ncore.nodes.cuboid.beans.Flag;
import com.github.ribesg.ncuboid.NCuboid;
import com.github.ribesg.ncuboid.beans.CuboidDB;
import com.github.ribesg.ncuboid.beans.PlayerCuboid;
import com.github.ribesg.ncuboid.events.EventExtensionHandler;
import com.github.ribesg.ncuboid.events.extensions.HangingBreakEventExtension;
import com.github.ribesg.ncuboid.events.extensions.PlayerInteractEventExtension;
import com.github.ribesg.ncuboid.lang.Messages.MessageId;
import com.github.ribesg.ncuboid.listeners.AbstractListener;

public class BuildFlagListener extends AbstractListener {

    public BuildFlagListener(final NCuboid instance) {
        super(instance);
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlayerBucketEmpty(final PlayerBucketEmptyEvent event) {
        final PlayerCuboid cuboid = CuboidDB.getInstance().getPriorByLoc(event.getBlockClicked().getRelative(event.getBlockFace()).getLocation());
        if (cuboid == null) {
            return;
        } else if (cuboid.getFlags().get(Flag.BUILD) && !cuboid.getRights().isAllowedPlayer(event.getPlayer())) {
            getPlugin().sendMessage(event.getPlayer(), MessageId.actionCancelledByCuboid, cuboid.getCuboidName());
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlayerBucketFill(final PlayerBucketFillEvent event) {
        final PlayerCuboid cuboid = CuboidDB.getInstance().getPriorByLoc(event.getBlockClicked().getLocation());
        if (cuboid == null) {
            return;
        } else if (cuboid.getFlags().get(Flag.BUILD) && !cuboid.getRights().isAllowedPlayer(event.getPlayer())) {
            getPlugin().sendMessage(event.getPlayer(), MessageId.actionCancelledByCuboid, cuboid.getCuboidName());
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    // We don't care if hasBlock()==false, so ignoreCancelled is true
    public void onPlayerInteract(final PlayerInteractEvent event) {
        if (event.hasBlock()) {
            if (event.hasItem() && event.getItem().getType() == Material.STICK) {
                // Handled in PlayerStickListener
                return;
            }
            final PlayerInteractEventExtension ext = (PlayerInteractEventExtension) EventExtensionHandler.get(event);
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.hasItem()) {
                // Fire or vehicle
                if (event.getItem().getType() == Material.FLINT_AND_STEEL || event.getItem().getType() == Material.FIREBALL
                        || event.getItem().getType() == Material.MINECART || event.getItem().getType() == Material.BOAT) {
                    final PlayerCuboid cuboid = CuboidDB.getInstance().getPriorByLoc(event.getClickedBlock().getRelative(event.getBlockFace()).getLocation());
                    if (cuboid != null && cuboid.getFlags().get(Flag.BUILD) && !cuboid.getRights().isAllowedPlayer(event.getPlayer())) {
                        getPlugin().sendMessage(event.getPlayer(), MessageId.actionCancelledByCuboid, cuboid.getCuboidName());
                        event.setCancelled(true);
                        return;
                    }
                    // Disc
                } else if (event.getClickedBlock().getType() == Material.JUKEBOX && event.getItem().getTypeId() >= 2256 && event.getItem().getTypeId() <= 2267) {
                    if (ext.getCuboid() != null && ext.getCuboid().getFlags().get(Flag.BUILD) && !ext.getCuboid().getRights().isAllowedPlayer(event.getPlayer())) {
                        getPlugin().sendMessage(event.getPlayer(), MessageId.actionCancelledByCuboid, ext.getCuboid().getCuboidName());
                        event.setCancelled(true);
                        return;
                    }
                }
                // Repeater
                if (event.getAction() == Action.RIGHT_CLICK_BLOCK && (event.getClickedBlock().getType() == Material.DIODE_BLOCK_OFF || event.getClickedBlock().getType() == Material.DIODE_BLOCK_ON)) {
                    if (ext.getCuboid() != null && ext.getCuboid().getFlags().get(Flag.BUILD) && !ext.getCuboid().getRights().isAllowedPlayer(event.getPlayer())) {
                        getPlugin().sendMessage(event.getPlayer(), MessageId.actionCancelledByCuboid, ext.getCuboid().getCuboidName());
                        event.setCancelled(true);
                        return;
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onBlockBreak(final BlockBreakEvent event) {
        final PlayerCuboid cuboid = CuboidDB.getInstance().getPriorByLoc(event.getBlock().getLocation());
        if (cuboid != null && cuboid.getFlags().get(Flag.BUILD) && !cuboid.getRights().isAllowedPlayer(event.getPlayer())) {
            getPlugin().sendMessage(event.getPlayer(), MessageId.actionCancelledByCuboid, cuboid.getCuboidName());
            event.setCancelled(true);
            return;
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onBlockPlace(final BlockPlaceEvent event) {
        final PlayerCuboid cuboid = CuboidDB.getInstance().getPriorByLoc(event.getBlock().getLocation());
        if (cuboid != null && cuboid.getFlags().get(Flag.BUILD) && !cuboid.getRights().isAllowedPlayer(event.getPlayer())) {
            getPlugin().sendMessage(event.getPlayer(), MessageId.actionCancelledByCuboid, cuboid.getCuboidName());
            event.setCancelled(true);
            return;
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onBlockDamage(final BlockDamageEvent event) {
        final PlayerCuboid cuboid = CuboidDB.getInstance().getPriorByLoc(event.getBlock().getLocation());
        if (cuboid != null && cuboid.getFlags().get(Flag.BUILD) && !cuboid.getRights().isAllowedPlayer(event.getPlayer())) {
            getPlugin().sendMessage(event.getPlayer(), MessageId.actionCancelledByCuboid, cuboid.getCuboidName());
            event.setCancelled(true);
            return;
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onHangingBreakByEntity(final HangingBreakByEntityEvent event) {
        final HangingBreakEventExtension ext = (HangingBreakEventExtension) EventExtensionHandler.get(event);
        if (event.getRemover().getType() == EntityType.PLAYER) {
            final Player player = (Player) event.getRemover();
            if (ext.getCuboid() != null && ext.getCuboid().getFlags().get(Flag.BUILD) && !ext.getCuboid().getRights().isAllowedPlayer(player)) {
                getPlugin().sendMessage(player, MessageId.actionCancelledByCuboid, ext.getCuboid().getCuboidName());
                event.setCancelled(true);
                return;
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onHangingPlace(final HangingPlaceEvent event) {
        final PlayerCuboid cuboid = CuboidDB.getInstance().getPriorByLoc(event.getEntity().getLocation());
        if (cuboid != null && cuboid.getFlags().get(Flag.BUILD) && !cuboid.getRights().isAllowedPlayer(event.getPlayer())) {
            getPlugin().sendMessage(event.getPlayer(), MessageId.actionCancelledByCuboid, cuboid.getCuboidName());
            event.setCancelled(true);
            return;
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onVehicleDestroy(final VehicleDestroyEvent event) {
        if (event.getAttacker().getType() == EntityType.PLAYER) {
            final Player player = (Player) event.getAttacker();
            final PlayerCuboid cuboid = CuboidDB.getInstance().getPriorByLoc(event.getVehicle().getLocation());
            if (cuboid != null && cuboid.getFlags().get(Flag.BUILD) && !cuboid.getRights().isAllowedPlayer(player)) {
                getPlugin().sendMessage(player, MessageId.actionCancelledByCuboid, cuboid.getCuboidName());
                event.setCancelled(true);
                return;
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onStructureGrow(final StructureGrowEvent event) {
        if (event.isFromBonemeal()) {
            final PlayerCuboid cuboid = CuboidDB.getInstance().getPriorByLoc(event.getLocation());
            if (cuboid != null && cuboid.getFlags().get(Flag.BUILD) && !cuboid.getRights().isAllowedPlayer(event.getPlayer())) {
                getPlugin().sendMessage(event.getPlayer(), MessageId.actionCancelledByCuboid, cuboid.getCuboidName());
                event.setCancelled(true);
                return;
            }
        }
    }
}
