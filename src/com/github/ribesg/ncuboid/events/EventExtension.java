package com.github.ribesg.ncuboid.events;

import lombok.Getter;

import org.bukkit.event.Event;

public abstract class EventExtension {
    @Getter private final Event relatedEvent;

    public EventExtension(final Event event) {
        relatedEvent = event;
    }
}
