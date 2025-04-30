package com.secretval.systems.modules;

import com.secretval.Viok;

public abstract class Module {
    public boolean enabled;

    public void enable() {
        enabled = true;
        Viok.eventManager.register(this);
        this.onEnable();
    }

    public void disable() {
        enabled = false;
        Viok.eventManager.remove(this);
        this.onDisable();
    }
    
    public abstract void onEnable();
    public abstract void onDisable();
}
