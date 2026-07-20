package com.rosemods.windswept.common.capability.wrappers;

import com.rosemods.windswept.common.item.WoodenBucketItem;
import com.rosemods.windswept.common.item.WoodenMilkBucketItem;
import com.rosemods.windswept.core.other.WindsweptConstants;
import com.rosemods.windswept.core.registry.WindsweptItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.capability.IFluidHandlerItem;
import org.jetbrains.annotations.NotNull;

public class WoodenBucketWrapper implements IFluidHandlerItem {
    private ItemStack container;

    public WoodenBucketWrapper(ItemStack container) {
        this.container = container;
    }

    @Override
    public @NotNull ItemStack getContainer() {
        return this.container;
    }

    private FluidStack getFluid() {
        Item item = this.container.getItem();

        if (item instanceof WoodenBucketItem bucket && !bucket.isEmpty())
            return new FluidStack(bucket.getFluid(), FluidType.BUCKET_VOLUME);
        else if (item instanceof WoodenMilkBucketItem)
            return new FluidStack(NeoForgeMod.MILK.get(), FluidType.BUCKET_VOLUME);

        return FluidStack.EMPTY;
    }

    private void setFluid(FluidStack stack) {
        if (stack.isEmpty()) {
            this.container = WoodenBucketItem.getEmpty(this.container, null, null);
        } else {
            Item item = getBucketFromFluid(stack.getFluid());
            if (item != null)
                this.container = WoodenBucketItem.getFilled(this.container, item, null);
        }
    }

    @Override
    public int getTanks() {
        return 1;
    }

    @Override
    public @NotNull FluidStack getFluidInTank(int tank) {
        return this.getFluid();
    }

    @Override
    public int getTankCapacity(int tank) {
        return FluidType.BUCKET_VOLUME;
    }

    @Override
    public boolean isFluidValid(int tank, @NotNull FluidStack stack) {
        return true;
    }

    @Override
    public int fill(FluidStack stack, FluidAction action) {
        if (this.container.getCount() != 1 || !this.getFluid().isEmpty() || stack.getAmount() < FluidType.BUCKET_VOLUME || !canFillFromFluid(stack.getFluid()))
            return 0;

        if (action.execute())
            this.setFluid(stack);

        return FluidType.BUCKET_VOLUME;
    }

    @Override
    public @NotNull FluidStack drain(int maxDrain, FluidAction action) {
        if (this.container.getCount() != 1 || maxDrain < FluidType.BUCKET_VOLUME)
            return FluidStack.EMPTY;

        FluidStack fluidStack = this.getFluid();
        if (!fluidStack.isEmpty()) {
            if (action.execute())
                this.setFluid(FluidStack.EMPTY);

            return fluidStack;
        }

        return FluidStack.EMPTY;
    }

    @Override
    public @NotNull FluidStack drain(FluidStack stack, FluidAction action) {
        if (this.container.getCount() != 1 || stack.getAmount() < FluidType.BUCKET_VOLUME)
            return FluidStack.EMPTY;

        FluidStack fluidStack = this.getFluid();
        if (!fluidStack.isEmpty() && FluidStack.isSameFluid(fluidStack, stack)) {
            if (action.execute())
                this.setFluid(FluidStack.EMPTY);

            return fluidStack;
        }

        return FluidStack.EMPTY;
    }

    private static Item getBucketFromFluid(Fluid fluid) {
        if (fluid == Fluids.WATER) return WindsweptItems.WOODEN_WATER_BUCKET.get();
        if (fluid == NeoForgeMod.MILK.get()) return WindsweptItems.WOODEN_MILK_BUCKET.get();
        if (ModList.get().isLoaded("create")) {
            ResourceLocation location = BuiltInRegistries.FLUID.getKey(fluid);
            if (location.equals(WindsweptConstants.HONEY)) return WindsweptItems.WOODEN_HONEY_BUCKET.get();
            if (location.equals(WindsweptConstants.CHOCOLATE)) return WindsweptItems.WOODEN_CHOCOLATE_BUCKET.get();
        }

        return null;
    }

    private static boolean canFillFromFluid(Fluid fluid) {
        if (ModList.get().isLoaded("create")) {
            ResourceLocation location = BuiltInRegistries.FLUID.getKey(fluid);
            if (location.equals(WindsweptConstants.HONEY) || location.equals(WindsweptConstants.CHOCOLATE)) return true;
        }
        return fluid == Fluids.WATER || fluid == NeoForgeMod.MILK.get();
    }

}