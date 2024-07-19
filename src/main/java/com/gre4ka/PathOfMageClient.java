package com.gre4ka;

import com.gre4ka.client.event.ClientEventHandler;
import com.gre4ka.fluids.ModFluids;
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

	public static final String MOD_ID = "magpath";

	@Override
	public void onInitializeClient() {

		ServerPackets.registerS2CPackets();

		ClientEventHandler.register();

		ModFluids.fluidRender();

		BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.REFINED_DIAMOND_BLOCK, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.REFINED_DIAMOND_BLOCK, RenderLayer.getTranslucent());
	}
}