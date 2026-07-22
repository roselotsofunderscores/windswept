package com.rosemods.windswept.common.item;

import com.rosemods.windswept.common.block.IWoodenBucketPickupBlock;
import com.rosemods.windswept.core.WindsweptConfig;
import com.rosemods.windswept.core.registry.WindsweptItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class WoodenBucketItem extends BucketItem {
    private final Supplier<? extends Fluid> contentSupplier;

    public WoodenBucketItem(Supplier<? extends Fluid> supplier, Properties builder) {
        super(supplier.get(), builder);
        this.contentSupplier = supplier;
    }

    public static ItemStack getEmpty(ItemStack handStack, @Nullable Player player, @Nullable InteractionHand hand) {
        ItemStack bucket = new ItemStack(WindsweptItems.WOODEN_BUCKET.get());
        bucket.setDamageValue(handStack.getDamageValue());

        if (handStack.has(DataComponents.ENCHANTMENTS)) {
            bucket.set(DataComponents.ENCHANTMENTS, handStack.get(DataComponents.ENCHANTMENTS));
        }

        if (player != null) {
            EquipmentSlot slot = hand == InteractionHand.OFF_HAND ? EquipmentSlot.OFFHAND : EquipmentSlot.MAINHAND;
            bucket.hurtAndBreak(1, player, slot);

            if (player.getAbilities().instabuild) {
                return handStack;
            }
        } else {
            int newDamage = bucket.getDamageValue() + 1;
            bucket.setDamageValue(newDamage);
            if (newDamage >= bucket.getMaxDamage()) {
                bucket.setCount(0);
            }
        }

        return bucket;
    }

    public static ItemStack getFilled(ItemStack handStack, ItemLike filled, @Nullable Player player) {
        ItemStack bucket = new ItemStack(filled);

        if (handStack.has(DataComponents.ENCHANTMENTS)) {
            bucket.set(DataComponents.ENCHANTMENTS, handStack.get(DataComponents.ENCHANTMENTS));
        }

        if (player == null || !player.getAbilities().instabuild)
            bucket.setDamageValue(handStack.getDamageValue());

        return player != null ? ItemUtils.createFilledResult(handStack, player, bucket) : bucket;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        Fluid content = this.contentSupplier.get();
        BlockHitResult blockhitresult = getPlayerPOVHitResult(level, player, content == Fluids.EMPTY ? ClipContext.Fluid.SOURCE_ONLY : ClipContext.Fluid.NONE);

        if (blockhitresult.getType() == HitResult.Type.BLOCK) {
            BlockPos blockpos = blockhitresult.getBlockPos();
            Direction direction = blockhitresult.getDirection();
            BlockPos blockpos1 = blockpos.relative(direction);

            if (level.mayInteract(player, blockpos) && player.mayUseItemAt(blockpos1, direction, itemstack)) {
                if (content == Fluids.EMPTY) {
                    BlockState state = level.getBlockState(blockpos);

                    if (state.getBlock() instanceof IWoodenBucketPickupBlock pickup && pickup.canPickupFromWoodenBucket(level, blockpos, state)) {
                        ItemStack filledBucket = getFilled(itemstack, pickup.getWoodenBucketItem(state), player);

                        pickup.getWoodenBucketPickupSound(state).ifPresent(soundevent -> player.playSound(soundevent, 1f, 1f));
                        pickup.pickupBlockFromWoodenBucket(player, level, blockpos, state);

                        player.awardStat(Stats.ITEM_USED.get(this));
                        level.gameEvent(player, GameEvent.FLUID_PICKUP, blockpos);

                        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer)
                            CriteriaTriggers.FILLED_BUCKET.trigger(serverPlayer, filledBucket);

                        return InteractionResultHolder.sidedSuccess(filledBucket, level.isClientSide);
                    }
                } else {
                    BlockState blockstate = level.getBlockState(blockpos);
                    BlockPos blockpos2 = this.canBlockContainFluid(player, level, blockpos, blockstate) ? blockpos : blockpos1;
                    if (this.emptyContents(player, level, blockpos2, blockhitresult)) {
                        if (player instanceof ServerPlayer serverPlayer)
                            CriteriaTriggers.PLACED_BLOCK.trigger(serverPlayer, blockpos2, itemstack);

                        player.awardStat(Stats.ITEM_USED.get(this));
                        return InteractionResultHolder.sidedSuccess(getEmpty(itemstack, player, hand), level.isClientSide);
                    }
                }
            }
            return InteractionResultHolder.fail(itemstack);
        }
        return InteractionResultHolder.pass(itemstack);
    }

    public Fluid getFluid() {
        return this.contentSupplier.get();
    }

    public boolean isEmpty() {
        return this.getFluid() == Fluids.EMPTY;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return WindsweptConfig.COMMON.woodenBucketDurabilty.get();
    }

    @Override
    public EquipmentSlot getEquipmentSlot(ItemStack stack) {
        return this.isEmpty() ? EquipmentSlot.HEAD : null;
    }

    @Override
    public boolean isRepairable(ItemStack stack) {
        return this.isEmpty();
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public int getBurnTime(ItemStack stack, @Nullable RecipeType<?> recipeType) {
        return this.isEmpty() ? 600 : -1;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        return this.isEmpty() ? ItemStack.EMPTY : getEmpty(itemStack, null, null);
    }

}
