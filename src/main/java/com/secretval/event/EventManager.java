package com.secretval.event;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventManager {
    private Map<Class<?>, List<EventListener>> listeners = new ConcurrentHashMap<>();

    public void register(Object o) {
        for (Method m : o.getClass().getMethods()) {
            if (m.isAnnotationPresent(EventListen.class) && m.getParameters().length != 0) {
                listeners.computeIfAbsent(m.getParameters()[0].getType(), k -> new CopyOnWriteArrayList<>()).add(new EventListener(o, m));
            }
        }
    }

    public void remove(Object o) {
        boolean[] removed = new boolean[1];
        listeners.values().removeIf(v -> {
            removed[0] |= v.removeIf(l -> o.getClass().equals(l.getTargetClass()));
            return v.isEmpty();
        });
    }

    public void post(Event e) {
        for (Entry<Class<?>, List<EventListener>> entry: listeners.entrySet()) {
            if (entry.getKey().isAssignableFrom(e.getClass())) {
                for (EventListener l: entry.getValue()) {
                    try {
                        l.call(e);
                    } catch (Throwable t) {
                        throw new RuntimeException(t);
                    }
                }
            }
        }
    }
}
