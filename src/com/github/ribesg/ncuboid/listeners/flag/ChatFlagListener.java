package com.github.ribesg.ncuboid.listeners.flag;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.github.ribesg.ncore.nodes.cuboid.beans.Flag;
import com.github.ribesg.ncuboid.beans.CuboidDB;
import com.github.ribesg.ncuboid.beans.GeneralCuboid;

public class ChatFlagListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onPlayerChat(final AsyncPlayerChatEvent event) {
        final GeneralCuboid c = CuboidDB.getInstance().get(event.getPlayer().getLocation());
        if (c.getFlag(Flag.CHAT)) {
            event.setCancelled(true);
        }
    }
}
