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
import com.github.ribesg.ncuboid.events.extensions.ExtendedEntityDamageEvent;
import com.github.ribesg.ncuboid.events.extensions.ExtendedPotionSplashEvent;
import com.github.ribesg.ncuboid.listeners.AbstractListener;

public class PVPFlagListener extends AbstractListener {

    public PVPFlagListener(final NCuboid instance) {
        super(instance);
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onEntityDamageByEntity(final ExtendedEntityDamageEvent ext) {
        if (ext.getBaseEvent() instanceof EntityDamageByEntityEvent) {
            final EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) ext.getBaseEvent();
            if (event.getEntityType() == EntityType.PLAYER && (event.getDamager().getType() == EntityType.PLAYER || ext.isDamagerProjectile() && ((Projectile) event.getDamager()).getShooter().getType() == EntityType.PLAYER)) {
                if (ext.getEntityCuboid() != null && ext.getEntityCuboid().getFlag(Flag.PVP) || ext.getDamagerCuboid() != null && ext.getDamagerCuboid().getFlag(Flag.PVP)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPotionSplash(final ExtendedPotionSplashEvent ext) {
        final PotionSplashEvent event = (PotionSplashEvent) ext.getBaseEvent();
        if (event.getPotion().getShooter().getType() == EntityType.PLAYER) {
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
