package com.rosemods.windswept.common.entity;

import com.rosemods.windswept.core.registry.WindsweptEntityTypes;
import com.rosemods.windswept.core.registry.WindsweptItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class FrostArrow extends AbstractArrow {

    private static final int FREEZE_AMOUNT = 100;

    public FrostArrow(EntityType<? extends FrostArrow> type, Level level) {
        super(type, level);
    }

    public FrostArrow(Level level, LivingEntity owner, ItemStack pickupItemStack, ItemStack firedFromWeapon) {
        super(WindsweptEntityTypes.FROST_ARROW.get(), owner, level, pickupItemStack, firedFromWeapon);
    }

    public FrostArrow(Level level, double x, double y, double z, ItemStack pickupItemStack, ItemStack firedFromWeapon) {
        super(WindsweptEntityTypes.FROST_ARROW.get(), x, y, z, level, pickupItemStack, firedFromWeapon);
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.inGround && this.level().isClientSide && this.tickCount > 2) {
            Vec3 motion = this.getDeltaMovement();

            for (int i = 1; i < 3; i++) {
                double px = getX() - motion.x * ((float) i / 3) + (Math.random() - .5d) * .1d;
                double py = getY() - motion.y * ((float) i / 3) + (Math.random() - .5d) * .1d;
                double pz = getZ() - motion.z * ((float) i / 3) + (Math.random() - .5d) * .1d;
                double mx = (Math.random() - .5d) * .03d - motion.x * .08d;
                double my = (Math.random() - .5d) * .03d - motion.y * .08d;
                double mz = (Math.random() - .5d) * .03d - motion.z * .08d;

                this.level().addParticle(ParticleTypes.SNOWFLAKE, px, py, pz, mx, my, mz);
            }
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        if (result.getEntity() instanceof LivingEntity livingEntity && !livingEntity.level().isClientSide && !this.isBlockedByShield(livingEntity)) {
            double speedFactor = Mth.clamp(this.getDeltaMovement().length(), 0.5D, 1.5D);
            int freezeAmount = (int) (FREEZE_AMOUNT * speedFactor);
            int maxFreezeTicks = livingEntity.getTicksRequiredToFreeze() * 2;

            livingEntity.setTicksFrozen(Math.min(livingEntity.getTicksFrozen() + freezeAmount, maxFreezeTicks));
        }

        super.onHitEntity(result);
    }

    private boolean isBlockedByShield(LivingEntity livingEntity) {
        if (!livingEntity.isBlocking())
            return false;

        Vec3 sourcePos = this.position();
        Vec3 viewVector = livingEntity.calculateViewVector(0.0F, livingEntity.getYHeadRot());
        Vec3 toDefender = sourcePos.vectorTo(livingEntity.position());
        toDefender = new Vec3(toDefender.x, 0.0D, toDefender.z).normalize();

        return toDefender.dot(viewVector) < 0.0D;
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return WindsweptItems.FROST_ARROW.get().getDefaultInstance();
    }

}
