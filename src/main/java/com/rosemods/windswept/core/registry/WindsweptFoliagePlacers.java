package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.common.levelgen.tree.foliage_placer.ChestnutFoliagePlacer;
import com.rosemods.windswept.core.Windswept;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class WindsweptFoliagePlacers {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS = DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, Windswept.MOD_ID);

    public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<ChestnutFoliagePlacer>> CHESTNUT_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("chestnut_foliage_placer", () -> new FoliagePlacerType<>(ChestnutFoliagePlacer.CODEC));

}
