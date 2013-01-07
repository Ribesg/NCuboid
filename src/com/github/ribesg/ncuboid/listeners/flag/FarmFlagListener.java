package com.github.ribesg.ncuboid.listeners.flag;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;

import com.github.ribesg.ncore.nodes.cuboid.beans.Flag;
import com.github.ribesg.ncuboid.NCuboid;
import com.github.ribesg.ncuboid.beans.CuboidDB;
import com.github.ribesg.ncuboid.beans.PlayerCuboid;
import com.github.ribesg.ncuboid.events.EventExtensionHandler;
import com.github.ribesg.ncuboid.events.extensions.EntityDamageEventExtension;
import com.github.ribesg.ncuboid.events.extensions.PlayerInteractEventExtension;
import com.github.ribesg.ncuboid.listeners.AbstractListener;

public class FarmFlagListener extends AbstractListener {

    private static Set<EntityType> animals;

    private static Set<EntityType> getAnimals() {
        if (animals == null) {
            animals = new HashSet<EntityType>();
            animals.add(EntityType.BAT);
            animals.add(EntityType.CHICKEN);
            animals.add(EntityType.COW);
            animals.add(EntityType.IRON_GOLEM);
            animals.add(EntityType.MUSHROOM_COW);
            animals.add(EntityType.OCELOT);
            animals.add(EntityType.PIG);
            animals.add(EntityType.SHEEP);
            animals.add(EntityType.SNOWMAN);
            animals.add(EntityType.SQUID);
            animals.add(EntityType.VILLAGER);
            animals.add(EntityType.WOLF);
        }
        return animals;
    }

    public FarmFlagListener(final NCuboid instance) {
        super(instance);
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onEntityDamageByEntity(final EntityDamageByEntityEvent event) {
        Player p;
        if (event.getDamager().getType() == EntityType.PLAYER) {
            p = (Player) event.getDamager();
        } else if (event.getDamager() instanceof Projectile && ((Projectile) event.getDamager()).getShooter().getType() == EntityType.PLAYER) {
            p = (Player) ((Projectile) event.getDamager()).getShooter();
        } else {
            return;
        }
        final EntityDamageEventExtension ext = (EntityDamageEventExtension) EventExtensionHandler.get(event);
        if (getAnimals().contains(event.getEntityType()) && ext.getEntityCuboid() != null && ext.getEntityCuboid().getFlag(Flag.FARM) && !ext.getEntityCuboid().isAllowedPlayer(p)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlayerInteractEvent(final PlayerInteractEvent event) {
        if (event.getAction() == Action.PHYSICAL) {
            final PlayerInteractEventExtension ext = (PlayerInteractEventExtension) EventExtensionHandler.get(event);
            if (ext.getCuboid() != null && ext.getCuboid().getFlag(Flag.FARM) && !ext.getCuboid().isAllowedPlayer(event.getPlayer())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlayerShearEntity(final PlayerShearEntityEvent event) {
        final PlayerCuboid cuboid = CuboidDB.getInstance().getPriorByLoc(event.getEntity().getLocation());
        if (cuboid != null && cuboid.getFlag(Flag.FARM) && !cuboid.isAllowedPlayer(event.getPlayer())) {
            event.setCancelled(true);
        }
    }
}
