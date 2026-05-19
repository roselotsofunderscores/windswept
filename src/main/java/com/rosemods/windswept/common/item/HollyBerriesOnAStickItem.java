package com.rosemods.windswept.common.item;

import com.rosemods.windswept.common.entity.Frostbiter;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class HollyBerriesOnAStickItem extends Item {
    public HollyBerriesOnAStickItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (level.isClientSide)
            return InteractionResultHolder.pass(itemstack);

        if (player.isPassenger() && player.getControlledVehicle() instanceof Frostbiter frostbiter && frostbiter.boost()) {
            EquipmentSlot equipmentslot = LivingEntity.getSlotForHand(hand);
            ItemStack itemstack1 = itemstack.hurtAndConvertOnBreak(7, Items.FISHING_ROD, player, equipmentslot);
            return InteractionResultHolder.success(itemstack1);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.pass(itemstack);
    }

}
