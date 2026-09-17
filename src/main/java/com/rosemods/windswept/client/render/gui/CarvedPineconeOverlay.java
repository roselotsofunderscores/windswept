package com.rosemods.windswept.client.render.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CarvedPineconeOverlay implements LayeredDraw.Layer {
    private static final ResourceLocation PINECONE_BLUR_LOCATION = Windswept.location("textures/misc/pineconeblur.png");

    @Override
    public void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.player != null && minecraft.options.getCameraType().isFirstPerson() && minecraft.player.getInventory().getArmor(3).is(WindsweptBlocks.CARVED_PINECONE_BLOCK.asItem()))
            this.renderTextureOverlay(guiGraphics, PINECONE_BLUR_LOCATION, 1f);
    }

    private void renderTextureOverlay(GuiGraphics graphics, ResourceLocation texture, float opacity) {
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        graphics.setColor(1.0F, 1.0F, 1.0F, opacity);
        graphics.blit(texture, 0, 0, -90, 0.0F, 0.0F, graphics.guiWidth(), graphics.guiHeight(), graphics.guiWidth(), graphics.guiHeight());
        RenderSystem.disableBlend();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        graphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

}
