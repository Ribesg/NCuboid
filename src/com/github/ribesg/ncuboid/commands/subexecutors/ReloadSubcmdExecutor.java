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
                case "config":
                case "conf":
                    try {
                        Messages.loadConfig(getPlugin().getPathMessages());
                        getPlugin().sendMessage(getSender(), MessageId.cmdReloadMessages);
                        return true;
                    } catch (final IOException e) {
                        e.printStackTrace();
                        return true;
                    }
                case "messages":
                case "mes":
                    try {
                        Messages.loadConfig(getPlugin().getPathMessages());
                        getPlugin().sendMessage(getSender(), MessageId.cmdReloadMessages);
                        return true;
                    } catch (final IOException e) {
                        e.printStackTrace();
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
