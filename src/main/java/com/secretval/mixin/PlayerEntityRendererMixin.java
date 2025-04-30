package com.secretval.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.secretval.Viok;
import com.secretval.systems.cosmetics.Cosmetics;
import com.secretval.systems.cosmetics.shimmer.PlayerShimmerRenderer;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;

@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin {
    @SuppressWarnings("unchecked")
    @Inject(
        method = "updateRenderState",
        at     = @At("RETURN")
    )
    private void onUpdateRenderState(
        AbstractClientPlayerEntity player,
        PlayerEntityRenderState state,
        float tickDelta,
        CallbackInfo ci
    ) {
        var accessor = (LivingEntityRendererMixin<PlayerEntityRenderState, PlayerEntityModel>)
                       (Object) this;
        // For each feature on this renderer, if it's our shimmer renderer, inject the entity
        for (FeatureRenderer feature : accessor.getFeatures()) {
            if (feature instanceof PlayerShimmerRenderer) {
                Viok.systems.get(Cosmetics.class).setPlayer(player);
            }
        }
    }
}
