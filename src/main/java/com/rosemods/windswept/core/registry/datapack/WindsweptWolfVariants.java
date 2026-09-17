package com.rosemods.windswept.core.registry.datapack;

import com.rosemods.windswept.core.Windswept;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.animal.WolfVariant;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.biome.Biome;

import java.util.Arrays;

public class WindsweptWolfVariants {
    public static final ResourceKey<WolfVariant> CHESTNUT = create("chestnut");

    public static void bootstrap(BootstrapContext<WolfVariant> context) {
        register(context, CHESTNUT, "wolf_chestnut", WindsweptBiomes.CHESTNUT_FOREST, WindsweptBiomes.SNOWY_CHESTNUT_FOREST);
    }

    private static ResourceKey<WolfVariant> create(String name) {
        return ResourceKey.create(Registries.WOLF_VARIANT, Windswept.location(name));
    }

    @SafeVarargs
    static void register(BootstrapContext<WolfVariant> context, ResourceKey<WolfVariant> key, String name, ResourceKey<Biome>... spawnBiomes) {
        register(context, key, name, HolderSet.direct(Arrays.stream(spawnBiomes)
                .map(biome -> context.lookup(Registries.BIOME).getOrThrow(biome))
                .toList()));
    }

    private static void register(BootstrapContext<WolfVariant> context, ResourceKey<WolfVariant> key, String name, TagKey<Biome> spawnBiomes) {
        register(context, key, name, context.lookup(Registries.BIOME).getOrThrow(spawnBiomes));
    }

    private static void register(BootstrapContext<WolfVariant> context, ResourceKey<WolfVariant> key, String name, HolderSet<Biome> spawnBiomes) {
        ResourceLocation texture = Windswept.location("entity/wolf/" + name);
        ResourceLocation tame = Windswept.location("entity/wolf/" + name + "_tame");
        ResourceLocation angry = Windswept.location("entity/wolf/" + name + "_angry");
        context.register(key, new WolfVariant(texture, tame, angry, spawnBiomes));
    }
}