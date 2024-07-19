package com.gre4ka;

import com.gre4ka.client.event.ClientEventHandler;
import com.gre4ka.client.render.Dimension.DimensionRendering;
import com.gre4ka.fluids.FluidRender;
import com.gre4ka.network.ServerPackets;
import com.gre4ka.util.ModRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class PathOfMageClient implements ClientModInitializer {

	public static final String MOD_ID = "magpath";

	@Override
	public void onInitializeClient() {

		ServerPackets.registerS2CPackets();

		ClientEventHandler.register();
		DimensionRendering.register();
		FluidRender.fluidRender();

		BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.REFINED_DIAMOND_BLOCK, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModRegistry.REFINED_DIAMOND_BLOCK, RenderLayer.getTranslucent());
	}
}