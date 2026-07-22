package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.common.entity.Frostbiter;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PanicGoal.class)
public class PanicGoalMixin {
    @Shadow
    @Final
    protected PathfinderMob mob;
    @Shadow
    protected double posX;
    @Shadow
    protected double posY;
    @Shadow
    protected double posZ;

    @Inject(method = "canUse", at = @At("HEAD"), cancellable = true)
    private void canUse(CallbackInfoReturnable<Boolean> info) {
        AABB radius = new AABB(this.mob.blockPosition()).inflate(2);

        for (LivingEntity entity : this.mob.level().getEntitiesOfClass(LivingEntity.class, radius))
            if (this.mob != entity && (entity.getItemBySlot(EquipmentSlot.HEAD).is(WindsweptBlocks.CARVED_PINECONE_BLOCK.asItem()) || (entity instanceof Frostbiter frostbiter && frostbiter.hasControllingPassenger()))) {
                Vec3 vec3 = DefaultRandomPos.getPos(this.mob, 5, 4);
                if (vec3 == null)
                    info.setReturnValue(false);
                else {
                    this.posX = vec3.x;
                    this.posY = vec3.y;
                    this.posZ = vec3.z;
                    info.setReturnValue(true);
                }

                return;
            }
    }

}
