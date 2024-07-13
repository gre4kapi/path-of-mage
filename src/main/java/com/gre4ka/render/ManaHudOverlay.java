package com.gre4ka.render;

import com.gre4ka.PathOfMageClient;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Identifier;

public class ManaHudOverlay implements HudRenderCallback {

    public static final Identifier EMPTY_MANA = Identifier.of(PathOfMageClient.MOD_ID, "textures/mana/empty_mana.png");

    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter tickCounter) {
        int x = 3;
        int y = 3;
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null) {
            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();
        }

        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        drawContext.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, EMPTY_MANA);
        drawContext.drawTexture(EMPTY_MANA, x, y, 0, 0, 30, 104, 30, 104);
    }
}
