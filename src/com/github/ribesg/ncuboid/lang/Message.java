package com.github.ribesg.ncuboid.lang;

import lombok.Getter;

import com.github.ribesg.ncuboid.lang.Messages.MessageId;

public class Message {
    @Getter private final MessageId id;
    @Getter private final String    defaultMessage;
    @Getter private final String    configMessage;
    @Getter private final String[]  awaitedArgs;

    public Message(final MessageId id, final String defaultMessage, final String[] awaitedArgs) {
        this.id = id;
        this.defaultMessage = defaultMessage;
        this.awaitedArgs = awaitedArgs;
        configMessage = null;
    }

    public Message(final MessageId id, final String defaultMessage, final String[] awaitedArgs, final String configMessage) {
        this.id = id;
        this.defaultMessage = defaultMessage;
        this.awaitedArgs = awaitedArgs;
        this.configMessage = configMessage;
    }

    public String getAwaitedArgsString() {
        if (awaitedArgs == null) {
            return "none";
        } else {
            final StringBuilder s = new StringBuilder();
            for (final String arg : awaitedArgs) {
                s.append(arg);
                s.append(" ; ");
            }
            return s.toString().substring(0, s.length() - 3);
        }
    }

    public int getAwaitedArgsNb() {
        if (awaitedArgs == null) {
            return 0;
        } else {
            return awaitedArgs.length;
        }
    }
}
