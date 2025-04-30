package com.secretval.systems.commands.commands;

import java.util.Optional;

import com.secretval.Viok;
import com.secretval.systems.commands.Command;
import com.secretval.systems.cosmetics.Cosmetic;
import com.secretval.systems.cosmetics.CosmeticManager;
import com.secretval.systems.cosmetics.Cosmetics;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class CosmeticCommand implements Command {
    @Override
    public void execute(MinecraftClient client, String[] args) {
        if (args.length != 1) {
            client.player.sendMessage(Text.of("§c [viok] Expected 1 argument"), false);
            return;
        }
        String name = args[0];
        @SuppressWarnings("unchecked")
        CosmeticManager man = (CosmeticManager)Viok.systems.get(Cosmetics.class).getManager();
        Optional<Cosmetic> op = man.get(name);
        if (op == null) {
            client.player.sendMessage(Text.of("§c[viok] " + name + " not found"), false);
            return;
        }
        Cosmetic cosmetic = op.orElseThrow();
        boolean enabled = cosmetic.isEnabled();
        if (enabled) cosmetic.disable();
        else cosmetic.enable();
        client.player.sendMessage(Text.of("[§cviok§r] §a" + (!enabled ? "Enabled" : "Disabled") + " " + name), false);
    }
}
