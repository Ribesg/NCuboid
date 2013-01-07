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
        } else {
            switch (getArgs()[0]) {
                case "messages":
                case "mes":
                    if (getSender().isOp() || getSender().hasPermission(Permissions.CMD_RELOAD_MESSAGES)) {
                        try {
                            Messages.getInstance().loadConfig(getPlugin().getPathMessages());
                            getPlugin().sendMessage(getSender(), MessageId.cmdReloadMessages);
                            return true;
                        } catch (final IOException e) {
                            e.printStackTrace();
                            return true;
                        }
                    } else {
                        getPlugin().sendMessage(getSender(), MessageId.noPermissionForCommand);
                        return true;
                    }
                default:
                    return false;
            }
        }
    }

}
