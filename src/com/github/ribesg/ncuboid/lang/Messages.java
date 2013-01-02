package com.github.ribesg.ncuboid.lang;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

public class Messages {

    public enum MessageId {

        // General deny response
        actionCancelledByCuboid,

        // PlayerStickListener
        blockInSelection,
        blockNotInSelection,
        blockNotProtected,
        blockProtectedMultipleCuboids,
        blockProtectedOneCuboid,
        firstPointSelected,
        noSelection,
        secondPointSelected,
        selectionReset

    }

    public static final String  LINE_SEPARATOR = "%%";
    public static final String  MESSAGE_HEADER = "§0§l[§c§lN§6§lCuboid§0§l] §f";
    public static final Charset CHARSET        = Charset.defaultCharset();
    private static Messages     instance;

    public static Messages getInstance() {
        if (instance == null) {
            instance = new Messages();
        }
        return instance;
    }

    @Getter private EnumMap<MessageId, Message> messagesMap; // Id ; Message

    public Messages() {
        messagesMap = new EnumMap<>(MessageId.class);
    }

    public void loadConfig(final Path pathMessages) throws IOException {
        messagesMap = getDefaultConfig();
        if (!Files.exists(pathMessages)) {
            newConfig(pathMessages);
        } else {
            final YamlConfiguration cMessages = new YamlConfiguration();
            try (BufferedReader reader = Files.newBufferedReader(pathMessages, CHARSET)) {
                final StringBuilder s = new StringBuilder();
                while (reader.ready()) {
                    s.append(reader.readLine() + '\n');
                }
                cMessages.loadFromString(s.toString()); // Fails
            } catch (final Exception e) {
                e.printStackTrace();
            }
            for (final String idString : cMessages.getKeys(false)) {
                try {
                    final MessageId id = MessageId.valueOf(idString);
                    final Message def = messagesMap.get(id);
                    messagesMap.put(id, new Message(id, def.getDefaultMessage(), def.getAwaitedArgs(), cMessages.getString(idString, def.getDefaultMessage())));
                } catch (final IllegalArgumentException e) {
                    e.printStackTrace();
                    continue;
                }
            }
            overwriteConfig(pathMessages);
        }
    }

    // Precond : fMessages does not exist
    public EnumMap<MessageId, Message> getDefaultConfig() {
        final Set<Message> newMessages = new HashSet<Message>();

        // General deny response
        newMessages.add(new Message(MessageId.actionCancelledByCuboid, "&cAction cancelled by the cuboid %cuboid%", new String[] { "%cuboid%" }, null));

        // PlayerStickListener
        newMessages.add(new Message(MessageId.firstPointSelected, "&aFirst point selected : %coords%", new String[] { "%coords%" }, null));
        newMessages.add(new Message(MessageId.secondPointSelected, "&aSecond point selected : %coords%%%&aSelection Size : %size%", new String[] { "%coords%", "%size%" }, null));
        newMessages.add(new Message(MessageId.blockInSelection, "&aThis block is in your selection", null, null));
        newMessages.add(new Message(MessageId.blockNotInSelection, "&cThis block is not in your selection", null, null));
        newMessages.add(new Message(MessageId.blockNotProtected, "&aThis block is not protected", null, null));
        newMessages.add(new Message(MessageId.blockProtectedOneCuboid, "&cThis block is protected by one cuboid:" + LINE_SEPARATOR + "%cuboidInfo%", new String[] { "%cuboidInfo%" }, null));
        newMessages.add(new Message(MessageId.blockProtectedMultipleCuboids, "&cThis block is protected by %nb% cuboids:" + LINE_SEPARATOR + "%cuboidsInfos%", new String[] { "%cuboidsInfos%" }, null));
        newMessages.add(new Message(MessageId.selectionReset, "&aYour selection has been reset", null, null));
        newMessages.add(new Message(MessageId.noSelection, "&cYou have no selection to reset", null, null));

        final EnumMap<MessageId, Message> map = new EnumMap<MessageId, Message>(MessageId.class);
        for (final Message m : newMessages) {
            map.put(m.getId(), m);
        }
        return map;
    }

    private void newConfig(final Path pathMessages) throws IOException {
        writeConfig(pathMessages, false);
    }

    private void overwriteConfig(final Path pathMessages) throws IOException {
        writeConfig(pathMessages, true);
    }

    private void writeConfig(final Path pathMessages, final boolean overwrite) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(pathMessages, CHARSET, overwrite ? StandardOpenOption.TRUNCATE_EXISTING : StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE)) {
            final StringBuilder content = new StringBuilder();
            content.append("################################################################################\n");
            content.append("# List of NCuboid messages. You're free to change text/colors/language here.   #\n");
            content.append("# Supports both '§' and '&' characters for colors.                      Ribesg #\n");
            content.append("################################################################################\n\n");
            for (final Message m : getMessagesMap().values()) {
                content.append("# Default value    : " + m.getDefaultMessage() + '\n');
                content.append("# Awaited arguments: " + m.getAwaitedArgsString() + '\n');
                content.append(m.getId().name() + ": \"" + (m.getConfigMessage() != null ? m.getConfigMessage() : m.getDefaultMessage()) + "\"\n\n");
            }
            writer.write(content.toString());
        }
    }

    // Args have to be in the same order than in Message.getAwaitedArgs()
    public String[] get(final MessageId id, final String... args) {
        try {
            final Message m = getMessagesMap().get(id);
            if (args != null && args.length != m.getAwaitedArgsNb() || args == null && m.getAwaitedArgsNb() > 0) {
                throw new IllegalArgumentException("Call to Messages.get(id,args...) with wrong number of args : " + (args == null ? 0 : args.length) + " (awaited : " + m.getAwaitedArgsNb() + ")");
            }
            String res = m.getConfigMessage() == null ? m.getDefaultMessage() : m.getConfigMessage();
            // Replacing args by there values
            for (int i = 0; i < m.getAwaitedArgsNb(); i++) {
                res = res.replace(m.getAwaitedArgs()[i], args[i]);
            }
            // Adding Header, colors
            final String[] resSplit = res.concat(LINE_SEPARATOR).split(LINE_SEPARATOR);
            for (int i = 0; i < resSplit.length; i++) {
                resSplit[i] = MESSAGE_HEADER + ChatColor.translateAlternateColorCodes('&', resSplit[i]);
            }
            return resSplit;
        } catch (final IllegalArgumentException e) {
            e.printStackTrace();
            return new String[] { MESSAGE_HEADER + ChatColor.RED + "Something gone wrong, see console" };
        }
    }

    public static String merge(final String[] strings) {
        if (strings == null || strings.length < 1) {
            return null;
        } else {
            final StringBuilder res = new StringBuilder(strings[0]);
            for (int i = 1; i < strings.length; i++) {
                res.append(LINE_SEPARATOR);
                res.append(strings[i]);
            }
            return res.toString();
        }
    }
}
