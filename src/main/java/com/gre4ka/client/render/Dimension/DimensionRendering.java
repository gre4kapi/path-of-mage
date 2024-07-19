package com.gre4ka.client.render.Dimension;

import com.gre4ka.PathOfMage;
import com.gre4ka.client.render.Dimension.LangDimension.LangDimensionEffects;
import com.gre4ka.client.render.Dimension.LangDimension.LangSkyRenderer;
import com.gre4ka.world.dimension.ModDimensions;
import net.fabricmc.fabric.api.client.rendering.v1.DimensionRenderingRegistry;
import net.minecraft.client.render.DimensionEffects;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

public class DimensionRendering {
    public static void register() {
        //removeClouds();
        replaceSkyRenderer();
        //removeShit();
    }

    private static void replaceSkyRenderer() {
        DimensionRenderingRegistry.SkyRenderer noSkyRenderer = context -> {
        };
        DimensionRenderingRegistry.registerSkyRenderer(ModDimensions.LANGDIM_LEVEL_KEY, LangSkyRenderer::render);
    }

    private static void removeClouds() {
        DimensionRenderingRegistry.CloudRenderer noCloudRenderer = context -> {
        };
        DimensionRenderingRegistry.registerCloudRenderer(ModDimensions.LANGDIM_LEVEL_KEY, noCloudRenderer);
    }
    private static void removeShit(){
        DimensionRenderingRegistry.registerDimensionEffects(Identifier.of(PathOfMage.MOD_ID, "langdim"), new LangDimensionEffects());
    }


}
