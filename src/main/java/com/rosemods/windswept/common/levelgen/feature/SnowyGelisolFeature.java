package com.rosemods.windswept.common.levelgen.feature;

import com.rosemods.windswept.common.block.GelisolBlock;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.rosemods.windswept.core.registry.datapack.WindsweptBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class SnowyGelisolFeature extends Feature<NoneFeatureConfiguration> {
    public SnowyGelisolFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        BlockPos origin = context.origin();
        WorldGenLevel level = context.level();
        RandomSource rand = context.random();
        BlockState snowyGelisol = WindsweptBlocks.GELISOL.get().defaultBlockState().setValue(GelisolBlock.SNOWY, true);
        BlockState gelisol = WindsweptBlocks.GELISOL.get().defaultBlockState();
        BlockState snow = Blocks.SNOW.defaultBlockState();
        BlockState dirt = Blocks.DIRT.defaultBlockState();
        BlockState sprouts = WindsweptBlocks.GELISOL_GRASS.get().defaultBlockState();
        BlockState snowdrop = WindsweptBlocks.SNOWDROP.get().defaultBlockState();
        boolean generated = false;

        int originChunkX = origin.getX() >> 4;
        int originChunkZ = origin.getZ() >> 4;

        ChunkAccess originChunk = level.getChunk(originChunkX, originChunkZ);

        for (int x = -10; x <= 10; ++x)
            for (int z = -10; z <= 10; ++z) {
                BlockPos columnPos = origin.offset(x, 0, z);
                int chunkX = columnPos.getX() >> 4;
                int chunkZ = columnPos.getZ() >> 4;

                if (chunkX != originChunkX || chunkZ != originChunkZ)
                    continue;

                for (int y = -3; y <= 3; ++y) {
                    BlockPos pos = origin.offset(x, y, z);

                    if (!level.getBlockState(pos).is(Blocks.SNOW_BLOCK))
                        continue;

                    Holder<Biome> biome = originChunk.getNoiseBiome(
                            QuartPos.fromBlock(pos.getX()),
                            QuartPos.fromBlock(pos.getY()),
                            QuartPos.fromBlock(pos.getZ())
                    );

                    if (!biome.is(WindsweptBiomes.TUNDRA))
                        continue;

                    BlockState above = level.getBlockState(pos.above());

                    if (!above.isSolid()) {
                        if (rand.nextInt(45) == 0) {
                            level.setBlock(pos, gelisol, 2);

                            if (above.isAir())
                                level.setBlock(pos.above(), sprouts, 2);
                        } else {
                            level.setBlock(pos, snowyGelisol, 2);

                            if (above.isAir())
                                level.setBlock(pos.above(), rand.nextInt(200) == 0 ? snowdrop : snow, 2);
                        }
                    } else
                        level.setBlock(pos, dirt, 2);

                    generated = true;
                }
            }

        return generated;
    }

}
