package com.rosemods.windswept.core.registry.datapack;

import com.rosemods.windswept.core.Windswept;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.decoration.PaintingVariant;

public final class WindsweptPaintingVariants {
    public static final ResourceKey<PaintingVariant> CLIFFSIDE = createKey("cliffside");
    public static final ResourceKey<PaintingVariant> DRESS_CODES = createKey("dress_codes");
    public static final ResourceKey<PaintingVariant> ECOTONAL_PAREIDOLIA = createKey("ecotonal_pareidolia");
    public static final ResourceKey<PaintingVariant> THE_FOILS = createKey("the_foils");
    public static final ResourceKey<PaintingVariant> AURORAE = createKey("aurorae");
    public static final ResourceKey<PaintingVariant> HEARTH_RUG = createKey("hearth_rug");
    public static final ResourceKey<PaintingVariant> ALLU_PINE = createKey("al-lu_pine");

    public static void bootstrap(BootstrapContext<PaintingVariant> context) {
        register(context, CLIFFSIDE, 3, 2);
        register(context, DRESS_CODES, 2, 2);
        register(context, ECOTONAL_PAREIDOLIA, 4, 2);
        register(context, THE_FOILS, 3, 4);
        register(context, AURORAE, 2, 1);
        register(context, HEARTH_RUG, 1, 4);
        register(context, ALLU_PINE, 2, 3);
    }

    private static ResourceKey<PaintingVariant> createKey(String name) {
        return ResourceKey.create(Registries.PAINTING_VARIANT, Windswept.location(name));
    }

    private static void register(BootstrapContext<PaintingVariant> context, ResourceKey<PaintingVariant> key, int width, int height) {
        context.register(key, new PaintingVariant(width, height, key.location()));
    }

}