package com.gre4ka.fluids;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

public class FluidRender {
    public static void fluidRender(){
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_SKHARNA, ModFluids.FLOWING_SKHARNA,
                new SimpleFluidRenderHandler(
                        Identifier.of("minecraft:block/water_still"),
                        Identifier.of("minecraft:block/water_flow"), 0xC1CCCCCC
                ));
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.STILL_SKHARNA, ModFluids.FLOWING_SKHARNA);
    }
}
