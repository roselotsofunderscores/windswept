package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.common.entity.Chilled;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.sensing.VillagerHostilesSensor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VillagerHostilesSensor.class)
public abstract class VillagerHostilesSensorMixin {

    @Inject(method = "isMatchingEntity", at = @At("HEAD"), cancellable = true)
    private void windswept$isMatchingEntity(LivingEntity attacker, LivingEntity target, CallbackInfoReturnable<Boolean> info) {
        if (target instanceof Chilled)
            info.setReturnValue(target.distanceToSqr(attacker) <= 64);
    }
}