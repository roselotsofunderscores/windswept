package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.core.other.WindsweptDataProcessors;
import com.teamabnormals.blueprint.common.world.storage.tracking.IDataManager;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public abstract class LivingEntityVisibilityMixin {

    @Redirect(method = "getVisibilityPercent(Lnet/minecraft/world/entity/Entity;)D", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isInvisible()Z"))
    private boolean windswept$isInvisibleOrCloaked(LivingEntity instance) {
        return instance.isInvisible() || (instance instanceof IDataManager data && data.getValue(WindsweptDataProcessors.CLOAKED));
    }
}