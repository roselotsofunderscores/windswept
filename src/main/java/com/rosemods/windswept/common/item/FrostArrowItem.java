package com.rosemods.windswept.common.item;

import com.rosemods.windswept.common.entity.FrostArrow;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FrostArrowItem extends ArrowItem {

    public FrostArrowItem(Properties properties) {
        super(properties);
    }

    @Override
    public AbstractArrow createArrow(Level level, ItemStack pickupItemStack, LivingEntity entity, ItemStack firedFromWeapon) {
        return new FrostArrow(level, entity, pickupItemStack.copyWithCount(1), firedFromWeapon);
    }

    @Override
    public Projectile asProjectile(Level level, net.minecraft.core.Position pos, ItemStack stack, Direction direction) {
        FrostArrow arrow = new FrostArrow(level, pos.x(), pos.y(), pos.z(), stack.copyWithCount(1), null);
        arrow.pickup = AbstractArrow.Pickup.ALLOWED;
        return arrow;
    }

}
