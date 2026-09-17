package com.rosemods.windswept.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;

public class HollySaplingBlock extends SaplingBlock {

    public HollySaplingBlock(TreeGrower tree, Properties properties) {
        super(tree, properties);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        HollyLeavesBlock.entityInside(1f, entity, level);
    }

}
