package com.rosemods.windswept.common.block;

import com.rosemods.windswept.core.registry.WindsweptBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LightEngine;

public interface Gelisol {

    Block getUnspreadBlock();

    static boolean canBeGelisol(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos abovePos = pos.above();
        BlockState aboveState = level.getBlockState(abovePos);

        if (aboveState.getFluidState().getAmount() == 8) {
            return false;
        } else {
            int light = LightEngine.getLightBlockInto(level, state, pos, aboveState, abovePos, Direction.UP, aboveState.getLightBlock(level, abovePos));
            return light < level.getMaxLightLevel();
        }
    }

    static boolean canPropagate(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos abovePos = pos.above();
        return canBeGelisol(state, level, pos) && !level.getFluidState(abovePos).is(FluidTags.WATER);
    }

    default void randomGelisolTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!canBeGelisol(state, level, pos)) {
            if (!level.isAreaLoaded(pos, 1)) return;
            level.setBlockAndUpdate(pos, this.getUnspreadBlock().defaultBlockState());
        } else {
            if (!level.isAreaLoaded(pos, 3)) return;
            if (level.getMaxLocalRawBrightness(pos.above()) >= 9) {
                BlockState gelisolState = WindsweptBlocks.GELISOL.get().defaultBlockState();

                for (int i = 0; i < 4; ++i) {
                    BlockPos offsetPos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                    BlockState offsetState = level.getBlockState(offsetPos);

                    if (offsetState.is(Blocks.DIRT) && canPropagate(gelisolState, level, offsetPos)) {
                        level.setBlockAndUpdate(offsetPos, gelisolState);
                    }
                }
            }
        }
    }

    default boolean isGelisolValidBonemealTarget(BlockGetter level, BlockPos pos, BlockState state) {
        BlockState above = level.getBlockState(pos.above());
        return above.canBeReplaced() && !above.is(WindsweptBlocks.GELISOL_GRASS.get());
    }

    default void performGelisolBonemeal(Block block, ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        BlockState gelisolGrass = WindsweptBlocks.GELISOL_GRASS.get().defaultBlockState();

        label48:
        for (int i = 0; i < 128; ++i) {
            BlockPos offsetPos = pos.above();

            for (int j = 0; j < i / 16; ++j) {
                offsetPos = offsetPos.offset(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);

                if (!level.getBlockState(offsetPos.below()).is(block) || level.getBlockState(offsetPos).isCollisionShapeFullBlock(level, offsetPos)) {
                    continue label48;
                }
            }

            if (level.getBlockState(offsetPos).isAir())
                level.setBlock(offsetPos, gelisolGrass, 3);
        }

    }

}