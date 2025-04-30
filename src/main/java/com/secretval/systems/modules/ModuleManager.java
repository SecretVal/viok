package com.secretval.systems.modules;

import java.util.LinkedHashMap;
import java.util.Optional;

import com.secretval.systems.Manager;

public class ModuleManager  implements Manager<String, Module>{
    private LinkedHashMap<String, Module> modules = new LinkedHashMap<>();

    @Override
    public void register(String name, Module m) {
        modules.put(name, m);
    }

    public void enable(String name) {
        modules.get(name).enable();
    }


    public void disable(String name) {
        modules.get(name).disable();
    }

    @Override
    public void remove(String id) {
        modules.remove(id);
    }

    @Override
    public Optional<Module> get(String id) {
        return Optional.of(modules.get(id));
    }

    @Override
    public LinkedHashMap<String, Module> getAll() {
        return modules;
    }
}
