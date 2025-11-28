package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.common.entity.PathfinderMobData;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.PathfinderMob;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(PathfinderMob.class)
public class PathfinderMobMixin implements PathfinderMobData {
  @Unique
  private @Nullable BlockPos windswept$potentialScarePosition = null;

  @Override
  public @Nullable BlockPos windswept$getPanicPosition() {
    return windswept$potentialScarePosition;
  }

  @Override
  public void windswept$setPanicPosition(@Nullable BlockPos pos) {
    this.windswept$potentialScarePosition = pos;
  }
}
