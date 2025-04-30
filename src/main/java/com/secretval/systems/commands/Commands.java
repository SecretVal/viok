package com.secretval.systems.commands;

import com.secretval.systems.System;
import com.secretval.systems.commands.commands.SayCommand;
import com.secretval.systems.commands.commands.CosmeticCommand;
import com.secretval.systems.commands.commands.ToggleCommand;

public class Commands extends System<Commands, CommandManager> {
    private CommandManager manager = new CommandManager();

    public Commands() {
        super(null);
    }

    @Override
    public void init() {
        manager.register("say", new SayCommand());
        manager.register("toggle", new ToggleCommand());
        manager.register("cosmetic", new CosmeticCommand());
    }

    @Override
    public CommandManager getManager() {
        return manager;
    }
}

