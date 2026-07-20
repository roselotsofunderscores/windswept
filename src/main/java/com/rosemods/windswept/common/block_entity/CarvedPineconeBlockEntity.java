package com.rosemods.windswept.common.block_entity;

import com.rosemods.windswept.core.registry.WindsweptBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class CarvedPineconeBlockEntity extends FearfulBlockEntity {
  public CarvedPineconeBlockEntity(BlockPos pos, BlockState state) {
    super(WindsweptBlockEntities.CARVED_PINECONE.get(), pos, state);
  }
}