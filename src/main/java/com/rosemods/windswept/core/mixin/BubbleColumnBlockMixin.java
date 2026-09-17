package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.common.block.IWoodenBucketPickupBlock;
import com.rosemods.windswept.core.registry.WindsweptItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.BubbleColumnBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BubbleColumnBlock.class)
public abstract class BubbleColumnBlockMixin implements IWoodenBucketPickupBlock {

    @Override
    public Item getWoodenBucketItem(BlockState state) {
        return WindsweptItems.WOODEN_WATER_BUCKET.get();
    }

}