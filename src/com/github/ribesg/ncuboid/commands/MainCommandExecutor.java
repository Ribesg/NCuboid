package com.github.ribesg.ncuboid.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.github.ribesg.ncuboid.NCuboid;

public class MainCommandExecutor implements CommandExecutor {

    private final NCuboid plugin;

    public MainCommandExecutor(final NCuboid instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd, final String cmdLabel, final String[] args) {
        if (cmd.getName().equals("cuboid")) {
            if (args.length == 0) {
                return cmdDefault(sender);
            } else {
                // TODO Better way to handle subcommand aliases ?
                switch (args[0]) {
                    case "reload":
                    case "rld":
                        return new ReloadSubcmdExecutor(plugin, sender, args).exec();
                    default:
                        return false;
                }
            }
        } else {
            return false;
        }
    }

    private boolean cmdDefault(final CommandSender sender) {
        // TODO
        return false;
    }
}
