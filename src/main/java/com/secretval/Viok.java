package com.secretval;

import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.secretval.event.EventManager;
import com.secretval.event.events.ChatEvents;
import com.secretval.overlays.FpsOverlay;
import com.secretval.screens.ViokScreen;
import com.secretval.systems.SystemManager;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.message.v1.ClientSendMessageEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudLayerRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.IdentifiedLayer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class Viok implements ClientModInitializer {
	public static final String MOD_ID = "viok";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static MinecraftClient mc;

    public static EventManager eventManager = new EventManager();

    public static SystemManager systems = new SystemManager();

    private static KeyBinding enableKeyBinding;

	private static final Identifier HUD_LAYER = Identifier.of(MOD_ID, "hud-layer");

    @Override
    public void onInitializeClient() {
        LOGGER.info("viok started");
        mc = MinecraftClient.getInstance();

        HudLayerRegistrationCallback.EVENT.register(context -> {
            context.addLayer(IdentifiedLayer.of(HUD_LAYER, new FpsOverlay()));
        });

        systems.init();

        registerEvents();
        registerKeybinds();
	}

    private void registerEvents() {
        ClientSendMessageEvents.CHAT.register(msg -> {
            eventManager.post(new ChatEvents.ChatSendEvent(msg));
        });
        ClientSendMessageEvents.ALLOW_CHAT.register(new ClientSendMessageEvents.AllowChat() {
            @Override
            public boolean allowSendChatMessage(String msg) {
                ChatEvents.AllowChatEvent event = new ChatEvents.AllowChatEvent(msg);
                eventManager.post(event);
                return !event.isCancelled();
            }
        });
    }
    

    private void registerKeybinds() {
        enableKeyBinding = KeyBindingHelper.registerKeyBinding(
            new KeyBinding(
                "key.viok.enable", 
                InputUtil.Type.KEYSYM, 
                GLFW.GLFW_KEY_COMMA, 
                "category.viok"
            )
        );

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (enableKeyBinding.wasPressed()) {
                mc.setScreen(new ViokScreen(Text.of("Viok"), mc.currentScreen));
            }
        });
    }
}
