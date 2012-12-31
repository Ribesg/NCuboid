package com.github.ribesg.ncuboid;

import lombok.Getter;

import org.bukkit.Bukkit;

import com.github.ribesg.ncore.NCore;
import com.github.ribesg.ncore.nodes.cuboid.CuboidNode;

public class NCuboid extends CuboidNode {

    // Core plugin
    @Getter NCore   core;

    // Useful Nodes

    // Set to true by afterEnable() call
    // Prevent multiple calls to afterEnable
    private boolean loadingComplete = false;

    @Override
    public void onEnable() {
        if (!Bukkit.getPluginManager().isPluginEnabled("NCore")) {
            // TODO
        } else {
            // TODO

            afterEnable();
        }
    }

    private void afterEnable() {
        if (!loadingComplete) {
            loadingComplete = true;
            Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {

                @Override
                public void run() {
                    // Interact with other Nodes here

                }
            });
        }
    }

    @Override
    public void onDisable() {

    }

    public void setCore(final NCore core) {
        this.core = core;
        core.setCuboidNode(this);
    }

}
