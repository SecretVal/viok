package com.secretval.mixin;

import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.client.option.SimpleOption;

// CREDIT: https://raw.githubusercontent.com/Greeenman999/fullbright/refs/heads/1.20/src/main/java/de/greenman999/fullbright/mixin/DoubleSliderCallbacksMixin.java
@Mixin(SimpleOption.DoubleSliderCallbacks.class)
public class DoubleSliderCallbackMixin {
    @Inject(method = "validate(Ljava/lang/Double;)Ljava/util/Optional;", at = @At("RETURN"), cancellable = true)
    public void removeValidation(Double d, CallbackInfoReturnable<Optional<Double>> cir) {
        cir.setReturnValue(d == null ? Optional.empty() : Optional.of(d));
    }
}
