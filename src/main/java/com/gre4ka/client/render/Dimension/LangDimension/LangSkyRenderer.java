package com.gre4ka.client.render.Dimension.LangDimension;

import com.gre4ka.PathOfMage;
import com.gre4ka.client.render.Dimension.LangDimension.skybox.ExtendedCubeMapRenderer;
import com.gre4ka.client.render.Dimension.LangDimension.skybox.SkyboxTextures;
import net.fabricmc.fabric.api.client.rendering.v1.DimensionRenderingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import com.mojang.blaze3d.systems.RenderSystem;
import org.joml.Matrix4f;

public class LangSkyRenderer  {
    private static final Identifier TEST = Identifier.of(PathOfMage.MOD_ID,
            "textures/block/refined_diamond_block.png");

    private static final ExtendedCubeMapRenderer cubeMapRenderer = new ExtendedCubeMapRenderer();
    private static final SkyboxTextures SPACE_SKYBOX = new SkyboxTextures(TEST, TEST, TEST, TEST, TEST, TEST);

    public static void render(WorldRenderContext ctx) {
        var client = MinecraftClient.getInstance();
        var worldKey = client.player.getWorld().getRegistryKey();
        cubeMapRenderer.swapTextures(SPACE_SKYBOX);
        cubeMapRenderer.render();
    }
}
