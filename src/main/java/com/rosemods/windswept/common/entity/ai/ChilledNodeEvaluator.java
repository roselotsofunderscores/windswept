package com.rosemods.windswept.common.entity.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.pathfinder.PathfindingContext;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;

import java.util.EnumSet;
import java.util.Set;

public class ChilledNodeEvaluator extends WalkNodeEvaluator {

    @Override
    public Set<PathType> getPathTypeWithinMobBB(PathfindingContext context, int x, int y, int z) {
        EnumSet<PathType> set = EnumSet.noneOf(PathType.class);

        for (int i = 0; i < this.entityWidth; i++) {
            for (int j = 0; j < this.entityHeight; j++) {
                for (int k = 0; k < this.entityDepth; k++) {
                    int px = i + x;
                    int py = j + y;
                    int pz = k + z;

                    BlockState state = context.level().getBlockState(new BlockPos(px, py, pz));

                    if (j > 0 && state.getBlock() instanceof SlabBlock
                            && state.hasProperty(SlabBlock.TYPE)
                            && state.getValue(SlabBlock.TYPE) == SlabType.TOP) {
                        set.add(PathType.OPEN);
                        continue;
                    }

                    set.add(this.getPathType(context, px, py, pz));
                }
            }
        }

        return set;
    }
}