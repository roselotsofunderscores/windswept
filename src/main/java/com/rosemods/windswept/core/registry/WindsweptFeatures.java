package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.common.levelgen.feature.*;
import com.rosemods.windswept.core.Windswept;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class WindsweptFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, Windswept.MOD_ID);

    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> SNOWY_SPROUTS_PATCH = FEATURES.register("snowy_sprouts_patch", SnowySproutsFeature::new);
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> NIGHTSHADE_PATCH = FEATURES.register("nightshade_patch", NightshadeFeature::new);
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> BLUEBELL_PATCH = FEATURES.register("bluebell_patch", BluebellsFeature::new);
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> LAVENDER_PATCH = FEATURES.register("lavender_patch", LavenderFeature::new);
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> ICICLES_PATCH = FEATURES.register("icicles_patch", IciclesFeature::new);
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> FLOOR_ICICLES_PATCH = FEATURES.register("floor_icicles_patch", FloorIciclesFeature::new);
    public static final DeferredHolder<Feature<?>, Feature<SimpleBlockConfiguration>> FALLEN_LOG = FEATURES.register("fallen_log", FallenLogFeature::new);
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> SNOWY_GELISOL = FEATURES.register("snowy_gelisol", SnowyGelisolFeature::new);
    public static final DeferredHolder<Feature<?>, Feature<TreeConfiguration>> PINE_TREE = FEATURES.register("pine_tree", PineTreeFeature::new);

}
