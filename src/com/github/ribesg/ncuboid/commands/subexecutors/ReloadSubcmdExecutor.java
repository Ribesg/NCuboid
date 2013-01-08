package com.github.ribesg.ncuboid.commands.subexecutors;

import java.io.IOException;

import org.bukkit.command.CommandSender;

import com.github.ribesg.ncore.Permissions;
import com.github.ribesg.ncuboid.NCuboid;
import com.github.ribesg.ncuboid.commands.AbstractSubcmdExecutor;
import com.github.ribesg.ncuboid.lang.Messages;
import com.github.ribesg.ncuboid.lang.Messages.MessageId;

public class ReloadSubcmdExecutor extends AbstractSubcmdExecutor {

    public ReloadSubcmdExecutor(final NCuboid instance, final CommandSender sender, final String[] superCommandArgs) {
        super(instance, sender, superCommandArgs);
    }

    @Override
    public boolean exec() {
        if (getArgs().length != 1) {
            return false;
        } else if (getSender().isOp() || getSender().hasPermission(Permissions.CMD_RELOAD)) {
            switch (getArgs()[0]) {
                case "cuboids":
                case "cuboid":
                case "cubo":
                    try {
                        Messages.loadConfig(getPlugin().getPathMessages());
                        getPlugin().sendMessage(getSender(), MessageId.cmdReloadCuboids);
                        return true;
                    } catch (final IOException e) {
                        e.printStackTrace();
                        getPlugin().sendMessage(getSender(), MessageId.errorWhileLoadingConfiguration, NCuboid.F_CUBOIDS);
                        return true;
                    }
                case "config":
                case "conf":
                    try {
                        Messages.loadConfig(getPlugin().getPathMessages());
                        getPlugin().sendMessage(getSender(), MessageId.cmdReloadConfig);
                        return true;
                    } catch (final IOException e) {
                        e.printStackTrace();
                        getPlugin().sendMessage(getSender(), MessageId.errorWhileLoadingConfiguration, NCuboid.F_CONFIG);
                        return true;
                    }
                case "messages":
                case "mess":
                case "mes":
                    try {
                        Messages.loadConfig(getPlugin().getPathMessages());
                        getPlugin().sendMessage(getSender(), MessageId.cmdReloadMessages);
                        return true;
                    } catch (final IOException e) {
                        e.printStackTrace();
                        getPlugin().sendMessage(getSender(), MessageId.errorWhileLoadingConfiguration, NCuboid.F_MESSAGES);
                        return true;
                    }
                default:
                    return false;
            }
        } else {
            getPlugin().sendMessage(getSender(), MessageId.noPermissionForCommand);
            return true;
        }
    }

}
