package com.secretval.systems.cosmetics.shimmer;

import com.secretval.Viok;
import com.secretval.systems.cosmetics.Cosmetic;

import net.fabricmc.fabric.mixin.client.rendering.LivingEntityRendererAccessor;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.LoadedEntityModels;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.util.Identifier;

public class Shimmer extends Cosmetic {
    PlayerEntityRenderer renderer;
    PlayerShimmerRenderer shimmerRenderer;
    LivingEntityRendererAccessor<PlayerEntityRenderState, PlayerEntityModel> accessor;
    Identifier id;
    boolean init = false;

    public Shimmer(Identifier id) {
        this.id = id;
    }

    @SuppressWarnings("unchecked")
    private void init() {
        if(init) return;

        renderer = (PlayerEntityRenderer) Viok.mc.getEntityRenderDispatcher().getRenderer(Viok.mc.player);
        shimmerRenderer = new PlayerShimmerRenderer(renderer, LoadedEntityModels.copy(), id);

        accessor = (LivingEntityRendererAccessor<PlayerEntityRenderState, PlayerEntityModel>) (Object) renderer;
        accessor.callAddFeature(shimmerRenderer);
        init = true;
    }


    public void enable() {
        if (this.shimmerRenderer == null) init();

        shimmerRenderer.active = true;
    }

    public void disable() {
        if (this.shimmerRenderer == null) init();

        shimmerRenderer.active = false;
    }

    public boolean isEnabled() {
        if (this.shimmerRenderer == null) init();

        return shimmerRenderer.active;
    }
}
