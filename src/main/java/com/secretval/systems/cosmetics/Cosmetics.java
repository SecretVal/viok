package com.secretval.systems.cosmetics;

import com.secretval.systems.System;
import com.secretval.systems.cosmetics.shimmer.Shimmer;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.util.Identifier;

public class Cosmetics extends System {
    private CosmeticManager manager = new CosmeticManager();
    private AbstractClientPlayerEntity player;

    public void setPlayer(AbstractClientPlayerEntity player) {
        this.player = player;
    }

    public AbstractClientPlayerEntity getPlayer() {
        return player;
    }

    public Cosmetics() { super(null); }

    @Override
    public void init() {
        manager.register("Creeper", new Shimmer(Identifier.ofVanilla("textures/entity/creeper/creeper_armor.png")));
        manager.register("Wither", new Shimmer(Identifier.ofVanilla("textures/entity/wither/wither_armor.png")));
    }

    @Override
    public Object getManager() {
        return manager;
    }
}
