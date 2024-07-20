package com.gre4ka.client.render.Dimension.LangDimension;

import com.gre4ka.PathOfMage;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.CubeMapRenderer;
import net.minecraft.util.Identifier;

public class ExtendedCubeMapRenderer extends CubeMapRenderer {
    public ExtendedCubeMapRenderer() {
        super(Identifier.of(PathOfMage.MOD_ID, "textures/skybox/test"));
    }

    public void render() {
        var client = MinecraftClient.getInstance();
        var camera = client.gameRenderer.getCamera();
        super.draw(client, camera.getPitch(), -camera.getYaw(), 1f);
    }
}