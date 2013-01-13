package com.github.ribesg.ncuboid.listeners.flag;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.github.ribesg.ncore.nodes.cuboid.beans.Flag;
import com.github.ribesg.ncuboid.NCuboid;
import com.github.ribesg.ncuboid.events.extensions.ExtendedPlayerInteractEntityEvent;
import com.github.ribesg.ncuboid.events.extensions.ExtendedPlayerInteractEvent;
import com.github.ribesg.ncuboid.listeners.AbstractListener;

public class UseFlagListener extends AbstractListener {

    private static Set<EntityType> denyUseEntities;
    private static Set<Material>   denyUseMaterials;

    private static Set<EntityType> getDenyUseEntity() {
        if (denyUseEntities == null) {
            denyUseEntities = new HashSet<EntityType>();
            denyUseEntities.add(EntityType.BOAT);
            denyUseEntities.add(EntityType.ITEM_FRAME);
            denyUseEntities.add(EntityType.MINECART);
        }
        return denyUseEntities;
    }

    private static Set<Material> getDenyUseMaterials() {
        if (denyUseMaterials == null) {
            denyUseMaterials = new HashSet<Material>();
            denyUseMaterials.add(Material.ANVIL);
            denyUseMaterials.add(Material.BED_BLOCK);
            denyUseMaterials.add(Material.CAKE_BLOCK);
            denyUseMaterials.add(Material.CAULDRON);
            denyUseMaterials.add(Material.DRAGON_EGG);
            denyUseMaterials.add(Material.ENCHANTMENT_TABLE);
            denyUseMaterials.add(Material.ENDER_CHEST);
            denyUseMaterials.add(Material.ENDER_PORTAL_FRAME);
            denyUseMaterials.add(Material.FENCE_GATE);
            denyUseMaterials.add(Material.FLOWER_POT);
            denyUseMaterials.add(Material.GLOWING_REDSTONE_ORE);
            denyUseMaterials.add(Material.JUKEBOX);
            denyUseMaterials.add(Material.LEVER);
            denyUseMaterials.add(Material.NOTE_BLOCK);
            denyUseMaterials.add(Material.REDSTONE_ORE);
            denyUseMaterials.add(Material.SIGN_POST);
            denyUseMaterials.add(Material.SOIL);
            denyUseMaterials.add(Material.STONE_BUTTON);
            denyUseMaterials.add(Material.TRAP_DOOR);
            denyUseMaterials.add(Material.WALL_SIGN);
            denyUseMaterials.add(Material.WOOD_BUTTON);
            denyUseMaterials.add(Material.WOOD_DOOR);
            denyUseMaterials.add(Material.WOODEN_DOOR);
            denyUseMaterials.add(Material.WORKBENCH);
        }
        return denyUseMaterials;
    }

    public UseFlagListener(final NCuboid instance) {
        super(instance);
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlayerInteract(final ExtendedPlayerInteractEvent ext) {
        final PlayerInteractEvent event = (PlayerInteractEvent) ext.getBaseEvent();
        if (event.hasBlock()) {
            if (ext.getCuboid() != null && ext.getCuboid().getFlag(Flag.USE) && !ext.getCuboid().isAllowedPlayer(event.getPlayer())
                    && getDenyUseMaterials().contains(event.getClickedBlock().getType())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlayerInteractEntity(final ExtendedPlayerInteractEntityEvent ext) {
        final PlayerInteractEntityEvent event = (PlayerInteractEntityEvent) ext.getBaseEvent();
        if (ext.getCuboid() != null && ext.getCuboid().getFlag(Flag.USE) && !ext.getCuboid().isAllowedPlayer(event.getPlayer())
                && getDenyUseEntity().contains(event.getRightClicked().getType())) {
            event.setCancelled(true);
        }
    }
}
