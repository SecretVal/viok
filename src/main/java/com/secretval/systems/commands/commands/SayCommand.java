package com.secretval.systems.commands.commands;

import com.secretval.systems.commands.Command;

import net.minecraft.client.MinecraftClient;

public class SayCommand implements Command {
    @Override
    public void execute(MinecraftClient client, String[] args) {
        String str = "";
        for (String arg: args) 
            str += arg;
        client.player.networkHandler.sendChatMessage(str);
    }
}
