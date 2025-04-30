package com.secretval.overlays;

import com.secretval.Viok;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.LayeredDrawer.Layer;
import net.minecraft.client.render.RenderTickCounter;

public class FpsOverlay implements Layer {
    private float totalTickDelta = 0;

    MinecraftClient mc =  Viok.mc;
    @Override
    public void render(DrawContext context, RenderTickCounter tickCounter) {
        String string = mc.getCurrentFps() + "Fps";
        TextRenderer textRenderer = mc.textRenderer;
        int w = textRenderer.getWidth(string);
        int h = 10;
        context.fill(0, 0, w+1, h+1, -1873784752);

        context.drawText(textRenderer, string, 1, 1, 14737632, false);
    }
}
