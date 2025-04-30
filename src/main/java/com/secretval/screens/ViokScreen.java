package com.secretval.screens;

import com.secretval.Viok;
import com.secretval.systems.cosmetics.Cosmetic;
import com.secretval.systems.cosmetics.CosmeticManager;
import com.secretval.systems.cosmetics.Cosmetics;
import com.secretval.systems.modules.Module;
import com.secretval.systems.modules.Modules;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class ViokScreen extends Screen {
    public Screen parent;
    public ViokScreen(Text title, Screen parent) {
        super(title);
        this.parent = parent;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void init() {
        ButtonWidget backBtn = ButtonWidget.builder(Text.of("back"), b -> {
            Viok.mc.setScreen(this.parent);
        }).dimensions(0,0, 120, 20).build();
        this.addDrawableChild(backBtn);

        int x[] = {0};
        int y[] = {25};
        Viok.systems.get(Modules.class).getManager().getAll().forEach((name, m) -> {
            ButtonWidget btn = ButtonWidget.builder(getModuleString(name, m), b -> {
                if (m.enabled) m.disable();
                else m.enable();
                b.setMessage(getModuleString(name, m));
            }).dimensions(x[0], y[0], 120, 20).build();
            this.addDrawableChild(btn);
            y[0] += 25;
        });
        x[0] = 140;
        y[0] = 25;
        ((CosmeticManager)Viok.systems.get(Cosmetics.class).getManager()).getAll().forEach((name, c) -> {
            ButtonWidget btn = ButtonWidget.builder(getCosmeticString(name, c), b -> {
                if (c.isEnabled()) c.disable();
                else c.enable();
                b.setMessage(getCosmeticString(name, c));
            }).dimensions(x[0], y[0], 120, 20).build();
            this.addDrawableChild(btn);
            y[0] += 25;
        });
    }


    private Text getModuleString(String name, Module m) {
        return Text.of(name + " " + (m.enabled ? "on" : "off"));
    }

    private Text getCosmeticString(String name, Cosmetic c) {
        return Text.of(name + " " + (c.isEnabled() ? "on" : "off"));
    }

	@Override
	public void render(DrawContext context, int mouseX, int mouseY, float delta) {
		super.render(context, mouseX, mouseY, delta);
	}
}
