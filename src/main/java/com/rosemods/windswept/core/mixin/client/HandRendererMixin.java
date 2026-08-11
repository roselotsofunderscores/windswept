package com.rosemods.windswept.core.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.rosemods.windswept.core.other.WindsweptDataProcessors;
import com.teamabnormals.blueprint.common.world.storage.tracking.IDataManager;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemInHandRenderer.class)
public abstract class HandRendererMixin {

    @Inject(method = "renderHandsWithItems", at = @At("HEAD"), cancellable = true)
    private void windswept$cancelCloakedHandRender(float partialTick, PoseStack poseStack, MultiBufferSource.BufferSource bufferSource, LocalPlayer player, int combinedLight, CallbackInfo ci) {
        if (player instanceof IDataManager data && data.getValue(WindsweptDataProcessors.CLOAKED))
            ci.cancel();
    }
}