package com.gre4ka.client.render.Dimension.LangDimension.skybox;

import com.gre4ka.PathOfMage;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.CubeMapRenderer;
import net.minecraft.util.Identifier;

public class ExtendedCubeMapRenderer extends CubeMapRenderer {
    private Identifier[] faces = new Identifier[6];
    public ExtendedCubeMapRenderer() {
        super(Identifier.of(PathOfMage.MOD_ID, "textures/skybox/test"));
    }

    public void swapTextures(SkyboxTextures textures) {
        this.faces[0] = textures.north();
        this.faces[1] = textures.west();
        this.faces[2] = textures.south();
        this.faces[3] = textures.east();
        this.faces[4] = textures.up();
        this.faces[5] = textures.down();
    }

    public void render() {
        var client = MinecraftClient.getInstance();
        var camera = client.gameRenderer.getCamera();
        super.draw(client, camera.getPitch(), -camera.getYaw(), 1f);
    }
}