package com.gre4ka.client.render.Dimension;

import com.gre4ka.PathOfMage;
import com.gre4ka.client.render.Dimension.LangDimension.LangDimensionEffects;
import com.gre4ka.client.render.Dimension.LangDimension.LangSkyRenderer;
import com.gre4ka.world.dimension.ModDimensions;
import net.fabricmc.fabric.api.client.rendering.v1.DimensionRenderingRegistry;
import net.fabricmc.fabric.mixin.client.rendering.DimensionEffectsAccessor;
import net.minecraft.client.render.DimensionEffects;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

public class DimensionRendering {
    public static final Identifier LANGDIM = Identifier.of(PathOfMage.MOD_ID, "langdim");
    public static final Identifier LANGDIM_TYPE = Identifier.of(PathOfMage.MOD_ID, "langdim_type");
    public static void register() {
        removeClouds();
        replaceSkyRenderer();
    }
    private static void replaceSkyRenderer() {
        DimensionRenderingRegistry.registerSkyRenderer(ModDimensions.LANGDIM_LEVEL_KEY, LangSkyRenderer::render);
    }
    private static void removeClouds() {
        DimensionRenderingRegistry.CloudRenderer noCloudRenderer = context -> {
        };
        DimensionRenderingRegistry.registerCloudRenderer(ModDimensions.LANGDIM_LEVEL_KEY, noCloudRenderer);
    }
    public static void registerDimensionEffects(MagpathRegisterDimensionEffectsEvent event){
        event.register(LANGDIM, new LangDimensionEffects());
        MagpathRegisterDimensionEffectsEvent.EVENT.invoke(new MagpathRegisterDimensionEffectsEvent(DimensionRenderingRegistry::registerDimensionEffects));
    }
}
