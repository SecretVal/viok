package com.secretval.mixin;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.fabricmc.fabric.mixin.client.rendering.LivingEntityRendererAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.LivingEntity;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin<S extends LivingEntityRenderState, M extends EntityModel<? super S>>
        implements LivingEntityRendererAccessor<S, M> {
    @Inject(method = "hasLabel", at = @At("HEAD"), cancellable = true)
    public void showInThirdPerson(LivingEntity entity,double d, CallbackInfoReturnable<Boolean> cir) {
        if (entity == MinecraftClient.getInstance().player) {
            cir.setReturnValue(true);
        }
    }

    @Accessor("features")
    protected abstract List<FeatureRenderer<S, M>> getFeatures();

    @Shadow
    protected abstract boolean addFeature(FeatureRenderer<S, M> feature);

    @Override
    public boolean callAddFeature(FeatureRenderer<S, M> feature) {
        return addFeature(feature);
    }
}
