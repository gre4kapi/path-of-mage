package com.gre4ka.client.render.player;

import com.gre4ka.PathOfMageClient;
import com.gre4ka.client.event.ClientEventHandler;
import com.gre4ka.util.IDataSaver;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ManaHudOverlay implements HudRenderCallback {

    public static final Identifier EMPTY_MANA = Identifier.of(PathOfMageClient.MOD_ID, "textures/mana/empty_mana.png");
    public static boolean switchTrueVision = false;
    int timer = 0;

    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter tickCounter) {
        boolean trueVision = ((IDataSaver) MinecraftClient.getInstance().player).getPersistentData().getBoolean("trueVision");
        if (trueVision){
            if (ClientEventHandler.mKeyBinding.isPressed() && timer == 0){
                timer++;
            }
            if (!ClientEventHandler.mKeyBinding.isPressed() && timer != 0){
                timer = 0;
                switchTrueVision = !switchTrueVision;
            }
            if(switchTrueVision) {
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
                int maxMana = ((IDataSaver) MinecraftClient.getInstance().player).getPersistentData().getInt("maxMana");
                float manaGenSpd = ((IDataSaver) MinecraftClient.getInstance().player).getPersistentData().getFloat("manaGenSpd");
                int step = maxMana / 100;
                if(step != 0) {
                    for (int i = 0; i < mana / step; i++) {
                        drawContext.drawHorizontalLine(x + 2, x + 27, y + 101 - i, 0xBB5522FF);
                    }
                }
                drawContext.drawText(client.textRenderer, Text.of("Mana: " + String.valueOf(mana) + " MaxMana: " + String.valueOf(maxMana) + " ManaGenSpd: " + String.valueOf(manaGenSpd)), x, y, 0xFFFFFFFF, false);
                //PathOfMage.LOGGER.info("line drawing: ");
            }
        }

    }
}
