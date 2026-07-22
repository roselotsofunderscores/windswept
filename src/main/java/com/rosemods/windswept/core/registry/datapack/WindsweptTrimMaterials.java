package com.rosemods.windswept.core.registry.datapack;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;

import java.util.Map;

public final class WindsweptTrimMaterials {
    public static final ResourceKey<TrimMaterial> ICICLES = createKey("icicles");
    public static final ResourceKey<TrimMaterial> PINECONE = createKey("pinecone");

    public static void bootstrap(BootstrapContext<TrimMaterial> context) {
        register(context, ICICLES, WindsweptBlocks.ICICLES.asItem(), Style.EMPTY.withColor(0x6d91d7), Map.of());
        register(context, PINECONE, WindsweptBlocks.PINECONE.asItem(), Style.EMPTY.withColor(0x7c5741), Map.of());
    }

    private static ResourceKey<TrimMaterial> createKey(String name) {
        return ResourceKey.create(Registries.TRIM_MATERIAL, Windswept.location(name));
    }

    private static void register(BootstrapContext<TrimMaterial> context, ResourceKey<TrimMaterial> key, Item item, Style style, Map<Holder<ArmorMaterial>, String> overrides) {
        String namespace = key.location().getNamespace();
        String path = key.location().getPath();
        context.register(key, new TrimMaterial(namespace + "_" + path, BuiltInRegistries.ITEM.wrapAsHolder(item), -1.0F, overrides, Component.translatable(Util.makeDescriptionId("trim_material", ResourceLocation.fromNamespaceAndPath(namespace, path))).withStyle(style)));
    }

}
