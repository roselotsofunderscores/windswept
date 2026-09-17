package com.rosemods.windswept.common.levelgen.feature;

import com.rosemods.windswept.common.block.TallDesertFlowerBlock;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class DesertLilyFeature extends Feature<NoneFeatureConfiguration> {
    public DesertLilyFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    private static boolean nearWater(WorldGenLevel level, BlockPos pos) {
        for (int x = -2; x <= 2; x++)
            for (int z = -2; z <= 2; z++)
                if (level.isWaterAt(pos.offset(x, -1, z)) || level.isWaterAt(pos.offset(x, -2, z)))
                    return true;

        return false;
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        BlockPos origin = context.origin();
        WorldGenLevel level = context.level();
        RandomSource rand = context.random();
        BlockState state = WindsweptBlocks.DESERT_LILY.get().defaultBlockState();
        boolean generated = false;

        for (int x = -5; x <= 5; x++)
            for (int z = -5; z <= 5; z++)
                for (int y = -2; y <= 2; y++) {
                    BlockPos pos = origin.offset(x, y, z);
                    BlockState below = level.getBlockState(pos.below());

                    if (rand.nextInt(3) == 0 && level.isEmptyBlock(pos) && level.isEmptyBlock(pos.above()) && pos.getY() < level.getMaxBuildHeight()
                            && BluebellsFeature.shouldPlace(x, z, rand) && state.canSurvive(level, pos) && below.is(Blocks.SAND) && nearWater(level, pos)) {
                        TallDesertFlowerBlock.placeAt(level, state, pos, 2);
                        generated = true;
                    }
                }

        return generated;
    }

}

