package com.rosemods.windswept.common.entity;

import net.minecraft.core.BlockPos;
import org.jetbrains.annotations.Nullable;

public interface PathfinderMobData {
    int BLOCK_RADIUS = 2;
    double BLOCK_RADIUS_SQUARE = BLOCK_RADIUS * BLOCK_RADIUS;

    @Nullable BlockPos windswept$getPanicPosition();

    void windswept$setPanicPosition(@Nullable BlockPos pos);
}
