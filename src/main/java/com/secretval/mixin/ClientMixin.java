package com.secretval.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.secretval.Viok;
import com.secretval.event.events.TickEvent;

import net.minecraft.client.MinecraftClient;

@Mixin(MinecraftClient.class)
public class ClientMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo info) {
        Viok.eventManager.post(new TickEvent());
    }
}
