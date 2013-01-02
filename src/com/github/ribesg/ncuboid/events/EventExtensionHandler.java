package com.github.ribesg.ncuboid.events;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.event.Event;

public final class EventExtensionHandler {
    private static Map<Event, EventExtension> map = new HashMap<Event, EventExtension>();

    public static boolean containsEvent(final Event event) {
        return map.containsKey(event);
    }

    public static EventExtension get(final Event event) {
        return map.get(event);
    }

    public static void add(final EventExtension e) {
        map.put(e.getRelatedEvent(), e);
    }

    public static void remove(final Event event) {
        map.remove(event);
    }
}
