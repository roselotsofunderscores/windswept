package com.rosemods.windswept.common.block_entity;

import com.rosemods.windswept.common.entity.PathfinderMobData;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public abstract class FearfulBlockEntity extends BlockEntity {
    private final AABB radius;

    public FearfulBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.radius = new AABB(pos).inflate(PathfinderMobData.BLOCK_RADIUS);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, FearfulBlockEntity blockEntity) {
        for (PathfinderMob entity : level.getEntitiesOfClass(PathfinderMob.class, blockEntity.radius))
            ((PathfinderMobData) entity).windswept$setPanicPosition(pos);
    }
}
