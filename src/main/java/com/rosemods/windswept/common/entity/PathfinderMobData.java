package com.rosemods.windswept.common.entity;

import net.minecraft.core.BlockPos;
import org.jetbrains.annotations.Nullable;

public interface PathfinderMobData {
  @Nullable BlockPos windswept$getPanicPosition ();
  void windswept$setPanicPosition (@Nullable BlockPos pos);
}
