package com.secretval.systems.modules.utils;

import com.secretval.Viok;
import com.secretval.systems.modules.Module;

public class FullBrightModule extends Module {
    Double prev;
    
    public void onEnable() {
        prev = Viok.mc.options.getGamma().getValue();
        Viok.mc.options.getGamma().setValue(10.0);
    }

    public void onDisable() {
        Viok.mc.options.getGamma().setValue(prev);
    }
}
