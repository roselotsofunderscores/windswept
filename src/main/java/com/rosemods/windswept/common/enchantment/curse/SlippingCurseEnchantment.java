package com.rosemods.windswept.common.enchantment.curse;

import com.rosemods.windswept.core.registry.WindsweptEnchantments;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.Blocks;

public final class SlippingCurseEnchantment {

    public static float getFriction(Entity entity, float friction) {
        if (entity instanceof LivingEntity livingEntity && Blocks.ICE.getFriction() > friction && hasSlipping(livingEntity)) {
            return Blocks.ICE.getFriction();
        }
        return friction;
    }

    public static void attemptDamageBoots(LivingEntity entity) {
        if (hasSlipping(entity) && entity.level().random.nextFloat() < .02f) {
            var stack = entity.getItemBySlot(EquipmentSlot.FEET);
            if (!stack.isEmpty() && entity.level() instanceof ServerLevel serverLevel) {
                stack.hurtAndBreak(1, serverLevel, null, (item) -> entity.onEquippedItemBroken(item, EquipmentSlot.FEET));
            }
        }
    }

    public static boolean hasSlipping(LivingEntity entity) {
        var registry = entity.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        var holder = registry.get(WindsweptEnchantments.SLIPPING_CURSE);
        return holder.isPresent() && EnchantmentHelper.getEnchantmentLevel(holder.get(), entity) > 0;
    }
}