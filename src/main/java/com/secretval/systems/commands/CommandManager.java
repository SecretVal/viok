package com.secretval.systems.commands;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.secretval.Viok;
import com.secretval.event.EventListen;
import com.secretval.event.events.ChatEvents;
import com.secretval.systems.Manager;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;

public class CommandManager implements Manager<String, Command>{
    private LinkedHashMap<String, Command> commands = new LinkedHashMap<>();
    public static final String PREFIX = "!";

    public CommandManager() {
        Viok.eventManager.register(this);
    }

    @Override
    public void register(String name, Command cmd) {
        if (!commands.containsKey(name)) {
            commands.put(name, cmd);
        } else {
            Viok.LOGGER.error("Tried to register the same command twice");;
        }
    }

    @EventListen
    public void onAllowChat(ChatEvents.AllowChatEvent e) {
        if (e.getMsg().startsWith(PREFIX)) {
            e.setCancelled(true);
            ClientPlayerEntity player = Viok.mc.player;
            String input = e.getMsg();
            input = input.replaceFirst("^" + PREFIX, "");
            List<String> rawArgs = new LinkedList<String>(Arrays.asList(input.split(" ")));
            String cmd = rawArgs.removeFirst();
            if (commands.containsKey(cmd)) {
                String[] args = rawArgs.toArray(size -> new String[size]);
                commands.get(cmd).execute(Viok.mc, args);
            } else {
                player.sendMessage(Text.of("§c[viok] command " + cmd + " not found"), false);
            }
        }
    }

    @Override
    public void remove(String id) {
        throw new RuntimeException("This will not be implementd");
    }

    @Override
    public Optional<Command> get(String id) {
        throw new RuntimeException("This will not be implementd");
    }

    @Override
    public LinkedHashMap<String, Command> getAll() {
        return commands;
    }
}
