package com.rosemods.windswept.core.registry.datapack;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.WindsweptItems;
import net.minecraft.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimPattern;

public class WindsweptTrimPatterns {
    public static final ResourceKey<TrimPattern> STARE = createKey("stare");

    public static void bootstrap(BootstrapContext<TrimPattern> context) {
        register(context, STARE, WindsweptItems.STARE_ARMOR_TRIM_SMITHING_TEMPLATE.get());
    }

    public static ResourceKey<TrimPattern> createKey(String name) {
        return ResourceKey.create(Registries.TRIM_PATTERN, Windswept.location(name));
    }

    private static void register(BootstrapContext<TrimPattern> context, ResourceKey<TrimPattern> key, Item item) {
        context.register(key, new TrimPattern(key.location(), BuiltInRegistries.ITEM.wrapAsHolder(item), Component.translatable(Util.makeDescriptionId("trim_pattern", key.location())), false));
    }
}