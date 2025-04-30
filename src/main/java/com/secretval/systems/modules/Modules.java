package com.secretval.systems.modules;

import com.secretval.systems.System;
import com.secretval.systems.modules.utils.FullBrightModule;

public class Modules extends System<Modules, ModuleManager> {
    private ModuleManager manager = new ModuleManager();

    public Modules() {
        super(null);
    }

    @Override
    public void init() {
        // Utils
        manager.register("FullBright", new FullBrightModule());
    }

    @Override
    public ModuleManager getManager() {
        return manager;
    }
}
