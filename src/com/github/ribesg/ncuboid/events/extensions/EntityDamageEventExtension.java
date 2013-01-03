package com.github.ribesg.ncuboid.events.extensions;

import java.util.Set;

import lombok.Getter;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import com.github.ribesg.ncuboid.beans.CuboidDB;
import com.github.ribesg.ncuboid.beans.PlayerCuboid;
import com.github.ribesg.ncuboid.events.EventExtension;

public class EntityDamageEventExtension extends EventExtension {

    @Getter private final Set<PlayerCuboid> entityCuboids;
    @Getter private Set<PlayerCuboid>       damagerCuboids;
    @Getter private final PlayerCuboid      entityCuboid;
    @Getter private PlayerCuboid            damagerCuboid;

    public EntityDamageEventExtension(final EntityDamageEvent event) {
        super(event);
        entityCuboids = CuboidDB.getInstance().getAllByLoc(event.getEntity().getLocation());
        entityCuboid = CuboidDB.getInstance().getPrior(entityCuboids);
        if (event instanceof EntityDamageByEntityEvent) {
            damagerCuboids = CuboidDB.getInstance().getAllByLoc(((EntityDamageByEntityEvent) event).getDamager().getLocation());
            damagerCuboid = CuboidDB.getInstance().getPrior(damagerCuboids);
        }
    }

}
