package com.rosemods.windswept.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.Optional;

public interface IWoodenBucketPickupBlock {
    default void pickupBlockFromWoodenBucket(@Nullable Player player, LevelAccessor level, BlockPos pos, BlockState state) {
        if (state.getBlock() instanceof BucketPickup pickup)
            pickup.pickupBlock(player, level, pos, state);
    }

    default Optional<SoundEvent> getWoodenBucketPickupSound(BlockState state) {
        return state.getBlock() instanceof BucketPickup pickup ? pickup.getPickupSound(state) : Optional.empty();
    }

    default boolean canPickupFromWoodenBucket(LevelAccessor level, BlockPos pos, BlockState state) {
        return true;
    }

    Item getWoodenBucketItem(BlockState state);

}
