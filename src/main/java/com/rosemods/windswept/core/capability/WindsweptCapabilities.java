package com.rosemods.windswept.core.capability;

import com.rosemods.windswept.common.capability.wrappers.WoodenBucketWrapper;
import com.rosemods.windswept.core.registry.WindsweptItems;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public class WindsweptCapabilities {

    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerItem(
                Capabilities.FluidHandler.ITEM,
                (stack, ctx) -> new WoodenBucketWrapper(stack),
                WindsweptItems.WOODEN_BUCKET.get(),
                WindsweptItems.WOODEN_WATER_BUCKET.get(),
                WindsweptItems.WOODEN_MILK_BUCKET.get()
        );

        if (ModList.get().isLoaded("create")) {
            event.registerItem(
                    Capabilities.FluidHandler.ITEM,
                    (stack, ctx) -> new WoodenBucketWrapper(stack),
                    WindsweptItems.WOODEN_HONEY_BUCKET.get(),
                    WindsweptItems.WOODEN_CHOCOLATE_BUCKET.get()
            );
        }
    }
}