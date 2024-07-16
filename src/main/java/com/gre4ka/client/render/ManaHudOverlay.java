package com.gre4ka.client.render;

import com.gre4ka.PathOfMageClient;
import com.gre4ka.util.IDataSaver;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Identifier;

import java.util.Random;

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
        //drawContext.drawHorizontalLine(-3, -28, 98, 0xFF00FF00);
        int mana = ((IDataSaver) MinecraftClient.getInstance().player).getPersistentData().getInt("mana");
        Random r = new Random();
        for (int i = 0; i < mana; i++){
            drawContext.drawHorizontalLine(x+2, x+27, y+101-i, 0xBB5522FF);
        }
        //PathOfMage.LOGGER.info("line drawing: ");

    }
}
