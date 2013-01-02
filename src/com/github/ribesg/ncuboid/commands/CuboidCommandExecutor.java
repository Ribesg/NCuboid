package com.github.ribesg.ncuboid.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.github.ribesg.ncuboid.NCuboid;

public class CuboidCommandExecutor implements CommandExecutor {

    private final NCuboid plugin;

    public CuboidCommandExecutor(final NCuboid instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd, final String cmdLabel, final String[] args) {
        switch (cmd.getName()) {
            case "reload":
                return new ReloadSubcmdExecutor(plugin, sender, args).exec();
            default:
                return false;
        }
    }
}
