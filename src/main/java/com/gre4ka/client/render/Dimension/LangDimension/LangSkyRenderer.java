package com.gre4ka.client.render.Dimension.LangDimension;

import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;

public class LangSkyRenderer  {
    private static final ExtendedCubeMapRenderer cubeMapRenderer = new ExtendedCubeMapRenderer();
    public static void render(WorldRenderContext ctx) {
        cubeMapRenderer.render();
    }
}
