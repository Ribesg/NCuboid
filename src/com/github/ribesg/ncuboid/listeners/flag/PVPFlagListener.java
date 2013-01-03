package com.github.ribesg.ncuboid.listeners.flag;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PotionSplashEvent;

import com.github.ribesg.ncore.nodes.cuboid.beans.Flag;
import com.github.ribesg.ncuboid.NCuboid;
import com.github.ribesg.ncuboid.beans.PlayerCuboid;
import com.github.ribesg.ncuboid.events.EventExtensionHandler;
import com.github.ribesg.ncuboid.events.extensions.EntityDamageEventExtension;
import com.github.ribesg.ncuboid.events.extensions.PotionSplashEventExtension;
import com.github.ribesg.ncuboid.listeners.AbstractListener;

public class PVPFlagListener extends AbstractListener {

    public PVPFlagListener(final NCuboid instance) {
        super(instance);
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onEntityDamageByEntity(final EntityDamageByEntityEvent event) {
        final EntityDamageEventExtension ext = (EntityDamageEventExtension) EventExtensionHandler.get(event);
        if (event.getEntityType() == EntityType.PLAYER && (event.getDamager().getType() == EntityType.PLAYER || ext.isDamagerProjectile() && ((Projectile) event.getDamager()).getShooter().getType() == EntityType.PLAYER)) {
            if (ext.getEntityCuboid() != null && ext.getEntityCuboid().getFlag(Flag.PVP) || ext.getDamagerCuboid() != null && ext.getDamagerCuboid().getFlag(Flag.PVP)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPotionSplash(final PotionSplashEvent event) {
        if (event.getPotion().getShooter().getType() == EntityType.PLAYER) {
            final PotionSplashEventExtension ext = (PotionSplashEventExtension) EventExtensionHandler.get(event);
            if (ext.hasNegativeEffect()) {
                PlayerCuboid c;
                for (final LivingEntity e : ext.getEntityCuboidsMap().keySet()) {
                    if (e.getType() == EntityType.PLAYER) {
                        c = ext.getEntityCuboidsMap().get(e);
                        if (c != null && c.getFlag(Flag.PVP)) {
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }
    }
}
