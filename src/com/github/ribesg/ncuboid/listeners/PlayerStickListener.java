package com.github.ribesg.ncuboid.listeners;

import java.util.Arrays;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;

import com.github.ribesg.ncore.Utils;
import com.github.ribesg.ncuboid.NCuboid;
import com.github.ribesg.ncuboid.beans.CuboidDB;
import com.github.ribesg.ncuboid.beans.PlayerCuboid;
import com.github.ribesg.ncuboid.beans.PlayerCuboid.CuboidState;
import com.github.ribesg.ncuboid.events.PlayerInteractNEvent;
import com.github.ribesg.ncuboid.lang.Messages;
import com.github.ribesg.ncuboid.lang.Messages.MessageId;

public class PlayerStickListener extends AbstractListener {

    public PlayerStickListener(final NCuboid instance) {
        super(instance);
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerInteract(final PlayerInteractNEvent event) {
        if (event.hasItem() && event.getItem().getType() == Material.STICK) {
            final Action action = event.getAction();
            final Player p = event.getPlayer();
            switch (action) {
                case RIGHT_CLICK_BLOCK:
                    // Selection tool
                    final PlayerCuboid selection = event.getCuboid();
                    if (selection == null) {
                        getPlugin().sendMessage(p, MessageId.firstPointSelected, Utils.toString(event.getClickedBlockLocation()));
                    } else if (selection.getState() == CuboidState.TMPSTATE1) {
                        getPlugin().sendMessage(p, MessageId.secondPointSelected, Utils.toString(event.getClickedBlockLocation()), selection.getSizeString());
                    } else if (selection.getState() == CuboidState.TMPSTATE2) {
                        if (selection.contains(event.getClickedBlockLocation())) {
                            getPlugin().sendMessage(p, MessageId.blockInSelection);
                        } else {
                            getPlugin().sendMessage(p, MessageId.blockNotInSelection);
                        }
                    }
                    break;
                case LEFT_CLICK_BLOCK:
                    // Info tool
                    final Set<PlayerCuboid> cuboids = event.getCuboids();
                    if (cuboids == null || cuboids.size() == 0) {
                        getPlugin().sendMessage(p, MessageId.blockNotProtected);
                    } else if (cuboids.size() == 1) {
                        final PlayerCuboid cuboid = cuboids.iterator().next();
                        getPlugin().sendMessage(p, MessageId.blockProtectedOneCuboid, cuboid.getInfoLine());
                    } else {
                        final String[] strings = new String[cuboids.size()];
                        int i = 0;
                        for (final PlayerCuboid c : cuboids) {
                            strings[i++] = c.getInfoLine();
                        }
                        Arrays.sort(strings);
                        getPlugin().sendMessage(p, MessageId.blockProtectedMultipleCuboids, Messages.merge(strings));
                    }
                    break;
                case RIGHT_CLICK_AIR:
                case LEFT_CLICK_AIR:
                    // Selection reset
                    final PlayerCuboid deletedCuboid = CuboidDB.getInstance().delTmp(p.getName());
                    if (deletedCuboid == null) {
                        getPlugin().sendMessage(p, MessageId.selectionReset);
                    } else {
                        getPlugin().sendMessage(p, MessageId.noSelection);
                    }
                    break;
                default:
                    return;
            }
            event.setCancelled(true);
        }
    }
}
