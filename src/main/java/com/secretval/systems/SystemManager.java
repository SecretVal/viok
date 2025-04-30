package com.secretval.systems;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.secretval.systems.commands.Commands;
import com.secretval.systems.cosmetics.Cosmetics;
import com.secretval.systems.modules.Modules;

public class SystemManager {
    private Map<Class<? extends System>, System<?, ?>> systems = new ConcurrentHashMap<>();

    public void init() {
        add(new Commands());
        add(new Modules());

        // Cosmetics
        add(new Cosmetics());
    }

    private void add(System<?, ?> s) {
        systems.put(s.getClass(), s);
        s.init();
    }


    @SuppressWarnings("unchecked")
    public <T extends System<T, ?>> T get(Class<T> clazz) {
        return (T) systems.get(clazz);
    }
}
