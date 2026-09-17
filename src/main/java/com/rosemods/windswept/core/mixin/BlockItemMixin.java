package com.rosemods.windswept.core.mixin;

import com.rosemods.windswept.core.WindsweptConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockItem.class)
public class BlockItemMixin {

    @Inject(method = "placeBlock", at = @At("HEAD"), cancellable = true)
    private void placeBlock(BlockPlaceContext context, BlockState state, CallbackInfoReturnable<Boolean> info) {
        Level level = context.getLevel();

        if (level.dimensionType().ultraWarm())
            for (String s : WindsweptConfig.COMMON.dryNetherBlocks.get()) {
                String[] split = s.split("=");

                if (split.length == 2) {
                    Block block = BuiltInRegistries.BLOCK.get(ResourceLocation.tryParse(split[0]));
                    Block newBlock = BuiltInRegistries.BLOCK.get(ResourceLocation.tryParse(split[1]));

                    if (state.is(block) && newBlock != Blocks.AIR) {
                        BlockPos pos = context.getClickedPos();
                        level.playSound(context.getPlayer(), pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, .2f, (3f + level.getRandom().nextFloat() * .2f) * .7f);

                        for (int i = 0; i < 8; i++)
                            level.addParticle(ParticleTypes.SMOKE, pos.getX() + Math.random(), pos.getY() + Math.random(), pos.getZ() + Math.random(), 0f, 0f, 0f);

                        BlockState newState = newBlock.defaultBlockState();

                        for (Property<?> property : state.getProperties())
                            newState = windswept$copyProperty(state, newState, property);

                        info.setReturnValue(level.setBlock(pos, newState, 11));
                        return;
                    }
                }
            }
    }

    @Unique
    private static <T extends Comparable<T>> BlockState windswept$copyProperty(BlockState from, BlockState to, Property<T> property) {
        return to.setValue(property, from.getValue(property));
    }

}
