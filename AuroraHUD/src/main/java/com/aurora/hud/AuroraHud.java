package com.aurora.hud;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

public class AuroraHud implements ClientModInitializer {
    private static final Identifier LOGO = Identifier.of("aurorahud", "textures/gui/logo.png");

    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register((DrawContext context, float tickDelta) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player == null || client.options.hudHidden) return;

            TextRenderer font = client.textRenderer;
            int x = 10;
            int y = 10;

            context.drawTexture(LOGO, x, y, 0, 0, 32, 32, 32, 32);
            context.drawTextWithShadow(font, "Aurora HUD", x + 38, y + 4, 0xFF8B5CF6);
            
            int fps = client.getCurrentFps();
            context.drawTextWithShadow(font, "FPS: " + fps, x + 38, y + 16, 0xFFFFFFFF);
            
            int px = (int) client.player.getX();
            int py = (int) client.player.getY();
            int pz = (int) client.player.getZ();
            context.drawTextWithShadow(font, "XYZ: " + px + " " + py + " " + pz, x, y + 40, 0xFFFFFFFF);
        });
    }
}