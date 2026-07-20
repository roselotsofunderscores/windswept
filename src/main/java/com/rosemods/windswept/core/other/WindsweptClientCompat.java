package com.rosemods.windswept.core.other;

import com.rosemods.windswept.core.registry.WindsweptItems;
import net.minecraft.world.item.component.DyedItemColor;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

public class WindsweptClientCompat {

    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        event.register((stack, color) -> color > 0 ? -1 : DyedItemColor.getOrDefault(stack, DyedItemColor.LEATHER_COLOR), WindsweptItems.SNOW_BOOTS.get());
    }
}