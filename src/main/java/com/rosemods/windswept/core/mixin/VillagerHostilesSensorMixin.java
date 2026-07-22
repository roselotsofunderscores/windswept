package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.core.registry.WindsweptEntityTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.sensing.VillagerHostilesSensor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VillagerHostilesSensor.class)
public abstract class VillagerHostilesSensorMixin {

    @Inject(method = "isMatchingEntity", at = @At("HEAD"), cancellable = true)
    private void windswept$isMatchingEntity(LivingEntity attacker, LivingEntity target, CallbackInfoReturnable<Boolean> cir) {
        if (target.getType() == WindsweptEntityTypes.CHILLED.get()) {
            double acceptableDistance = 10.0D;
            cir.setReturnValue(target.distanceToSqr(attacker) <= acceptableDistance * acceptableDistance);
        }
    }
}