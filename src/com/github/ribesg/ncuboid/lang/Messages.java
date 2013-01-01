package com.github.ribesg.ncuboid.lang;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import lombok.Getter;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

public class Messages {

    public enum MessageId {

        // PlayerStickListener
        blockInSelection,
        blockNotInSelection,
        blockNotProtected,
        blockProtectedMultipleCuboids,
        blockProtectedOneCuboid,
        firstPointSelected,
        noSelection,
        secondPointSelected,
        selectionReset,

    }

    public static final String LINE_SEPARATOR = "%%";
    public static final String MESSAGE_HEADER = ChatColor.BLACK + "[" + ChatColor.UNDERLINE + ChatColor.GOLD + "NCuboid" + ChatColor.RESET + ChatColor.BLACK + "]" + ChatColor.WHITE;
    private static Messages    instance;

    public static Messages getInstance() {
        if (instance == null) {
            instance = new Messages();
        }
        return instance;
    }

    @Getter private Map<MessageId, Message> messagesMap; // Id ; Message

    public Messages() {
        messagesMap = new EnumMap<>(MessageId.class);
    }

    public void loadConfig(final Path pathMessages) throws IOException {
        if (!Files.exists(pathMessages)) {
            messagesMap = getDefaultConfig();
            newConfig(pathMessages);
        } else {
            final YamlConfiguration cMessages = YamlConfiguration.loadConfiguration(pathMessages.toFile());
            final Map<MessageId, Message> defaultMessages = getDefaultConfig();
            for (final String idString : cMessages.getKeys(false)) {
                try {
                    final MessageId id = MessageId.valueOf(idString);
                    final Message def = defaultMessages.get(id);
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
    public Map<MessageId, Message> getDefaultConfig() {
        final Set<Message> newMessages = new HashSet<Message>();

        // PlayerStickListener
        newMessages.add(new Message(MessageId.firstPointSelected, "First point selected : %coords%", new String[] { "%coords%" }, null));
        newMessages.add(new Message(MessageId.secondPointSelected, "Second point selected : %coords%, Size : %size%", new String[] { "%coords%", "%size%" }, null));
        newMessages.add(new Message(MessageId.blockInSelection, "This block is in your selection", null, null));
        newMessages.add(new Message(MessageId.blockNotInSelection, "This block is not in your selection", null, null));
        newMessages.add(new Message(MessageId.blockNotProtected, "This block is not protected", null, null));
        newMessages.add(new Message(MessageId.blockProtectedOneCuboid, "This block is protected by one cuboid:" + LINE_SEPARATOR + "%cuboidInfo%", new String[] { "%cuboidInfo%" }, null));
        newMessages.add(new Message(MessageId.blockProtectedMultipleCuboids, "This block is protected by %nb% cuboids:" + LINE_SEPARATOR + "%cuboidsInfos%", new String[] { "%cuboidsInfos%" }, null));
        newMessages.add(new Message(MessageId.selectionReset, "Your selection has been reset", null, null));
        newMessages.add(new Message(MessageId.noSelection, "You have no selection to reset", null, null));

        final Map<MessageId, Message> map = new EnumMap<MessageId, Message>(MessageId.class);
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
        try (BufferedWriter w = Files.newBufferedWriter(pathMessages, StandardCharsets.UTF_8, overwrite ? StandardOpenOption.TRUNCATE_EXISTING : StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE)) {
            for (final Message m : getMessagesMap().values()) {
                getMessagesMap().put(m.getId(), m);
                w.append("# Default value    : " + m.getDefaultMessage() + '\n');
                w.append("# Awaited arguments: " + m.getAwaitedArgsString() + '\n');
                w.append(m.getId() + ": \"" + m.getConfigMessage() == null ? m.getConfigMessage() : m.getDefaultMessage() + "\"\n\n");
            }
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
            for (int i = 0; i < m.getAwaitedArgsNb(); i++) {
                res = res.replace(m.getAwaitedArgs()[i], args[i]);
            }
            return res.split(LINE_SEPARATOR);
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
