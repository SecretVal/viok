package com.secretval.systems.commands;

import net.minecraft.client.MinecraftClient;

public interface Command {
    public void execute(MinecraftClient client, String[] args);
}
