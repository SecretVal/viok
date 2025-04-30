package com.secretval.systems.commands.commands;

import java.util.LinkedHashMap;

import com.secretval.Viok;
import com.secretval.systems.commands.Command;
import com.secretval.systems.modules.Module;
import com.secretval.systems.modules.Modules;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class ToggleCommand implements Command {
    @Override
    public void execute(MinecraftClient client, String[] args) {
        if (args.length != 1) {
            client.player.sendMessage(Text.of("[§cviok§r] §cExpected the name of a module"), false);
            return;
        }


        LinkedHashMap<String, Module> modules = Viok.systems.get(Modules.class).getManager().getAll();
        String name = args[0];
        if (!modules.containsKey(name)) {
            client.player.sendMessage(Text.of("[§cviok§r] §cModule not found"), false);
            return;
        }

        Module m = modules.get(name);
        if (m.enabled) m.disable();
        else m.enable();
        client.player.sendMessage(Text.of("[§cviok§r] §a" + (m.enabled ? "Enabled" : "Disabled") + " " + name), false);
    }
}
