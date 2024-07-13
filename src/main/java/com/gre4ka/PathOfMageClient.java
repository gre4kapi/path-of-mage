package com.gre4ka;

import com.gre4ka.event.EventHandler;
import com.gre4ka.network.ClientPackets;
import com.gre4ka.network.ServerPackets;
import com.gre4ka.network.payload.ManaSyncPayload;
import com.gre4ka.network.payload.SpeechPayload;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class PathOfMageClient implements ClientModInitializer {
	public static KeyBinding vKeyBinding;

	public static final String MOD_ID = "magpath";

	@Override
	public void onInitializeClient() {

		ServerPackets.registerS2CPackets();

		vKeyBinding = new KeyBinding("key.magpath.mic", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_V, KeyBinding.MISC_CATEGORY);
		KeyBindingHelper.registerKeyBinding(vKeyBinding);
		EventHandler.register();
	}
}