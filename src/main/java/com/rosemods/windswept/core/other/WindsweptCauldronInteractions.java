package com.rosemods.windswept.core.other;

import com.rosemods.windswept.common.item.WoodenBucketItem;
import com.rosemods.windswept.core.registry.WindsweptItems;
import com.rosemods.windswept.integration.neapolitan.WindsweptMilkCauldronInteractions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.fml.ModList;

public final class WindsweptCauldronInteractions {

    public static void registerCauldronInteractions() {
        CauldronInteraction.WATER.map().put(WindsweptItems.WOODEN_BUCKET.get(), WindsweptCauldronInteractions::emptyWaterCauldron);
        CauldronInteraction.EMPTY.map().put(WindsweptItems.WOODEN_WATER_BUCKET.get(), WindsweptCauldronInteractions::fillWaterCauldron);
        CauldronInteraction.WATER.map().put(WindsweptItems.WOODEN_WATER_BUCKET.get(), WindsweptCauldronInteractions::fillWaterCauldron);
        CauldronInteraction.POWDER_SNOW.map().put(WindsweptItems.WOODEN_BUCKET.get(), WindsweptCauldronInteractions::emptySnowCauldron);
        CauldronInteraction.EMPTY.map().put(WindsweptItems.WOODEN_POWDER_SNOW_BUCKET.get(), WindsweptCauldronInteractions::fillSnowCauldron);
        CauldronInteraction.POWDER_SNOW.map().put(WindsweptItems.WOODEN_POWDER_SNOW_BUCKET.get(), WindsweptCauldronInteractions::fillSnowCauldron);

        // Регистрация очистки ботинок от краски
        CauldronInteraction.WATER.map().put(WindsweptItems.SNOW_BOOTS.get(), CauldronInteraction.DYED_ITEM);

        if (ModList.get().isLoaded("neapolitan"))
            WindsweptMilkCauldronInteractions.registerCauldronInteractions();
    }

    public static ItemInteractionResult fillCauldron(Block filledBlock, SoundEvent sound, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack stack) {
        if (!level.isClientSide) {
            player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, WoodenBucketItem.getEmpty(stack, player, hand)));
            player.awardStat(Stats.FILL_CAULDRON);
            player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
            level.setBlockAndUpdate(pos, filledBlock.defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, 3));
            level.playSound(null, pos, sound, SoundSource.BLOCKS, 1f, 1f);
            level.gameEvent(null, GameEvent.FLUID_PLACE, pos);
        }

        return ItemInteractionResult.sidedSuccess(level.isClientSide);
    }

    private static ItemInteractionResult fillWaterCauldron(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack stack) {
        return fillCauldron(Blocks.WATER_CAULDRON, SoundEvents.BUCKET_EMPTY, state, level, pos, player, hand, stack);
    }

    private static ItemInteractionResult fillSnowCauldron(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack stack) {
        return fillCauldron(Blocks.POWDER_SNOW_CAULDRON, SoundEvents.BUCKET_EMPTY_POWDER_SNOW, state, level, pos, player, hand, stack);
    }

    public static ItemInteractionResult emptyCauldron(ItemLike filled, SoundEvent sound, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack stack) {
        if (state.getValue(LayeredCauldronBlock.LEVEL) == 3) {
            if (!level.isClientSide) {
                player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, WoodenBucketItem.getFilled(stack, filled, player)));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
                level.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                level.playSound(null, pos, sound, SoundSource.BLOCKS, 1f, 1f);
                level.gameEvent(null, GameEvent.FLUID_PICKUP, pos);
            }

            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    private static ItemInteractionResult emptyWaterCauldron(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack stack) {
        return emptyCauldron(WindsweptItems.WOODEN_WATER_BUCKET.get(), SoundEvents.BUCKET_FILL, state, level, pos, player, hand, stack);
    }

    private static ItemInteractionResult emptySnowCauldron(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack stack) {
        return emptyCauldron(WindsweptItems.WOODEN_POWDER_SNOW_BUCKET.get(), SoundEvents.BUCKET_FILL_POWDER_SNOW, state, level, pos, player, hand, stack);
    }
}