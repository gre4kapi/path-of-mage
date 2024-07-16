package com.gre4ka;

import com.gre4ka.client.event.ClientEventHandler;
import com.gre4ka.network.ServerPackets;
import com.gre4ka.util.ModRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.RenderLayer;
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
		ClientEventHandler.register();

		BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.REFINED_DIAMOND_BLOCK, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.REFINED_DIAMOND_BLOCK, RenderLayer.getTranslucent());
	}
}