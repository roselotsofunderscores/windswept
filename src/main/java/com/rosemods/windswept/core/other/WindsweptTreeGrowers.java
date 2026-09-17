package com.rosemods.windswept.core.other;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.datapack.WindsweptConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.Optional;

public final class WindsweptTreeGrowers {
    public static final TreeGrower HOLLY = create("holly", WindsweptConfiguredFeatures.HOLLY);
    public static final TreeGrower CHESTNUT = create("chestnut", WindsweptConfiguredFeatures.CHESTNUT);
    public static final TreeGrower PINE = create("pine", WindsweptConfiguredFeatures.PINE);
    public static final TreeGrower FLOWERING_ACACIA = create("flowering_acacia", WindsweptConfiguredFeatures.FLOWERING_ACACIA);

    private static TreeGrower create(String name, ResourceKey<ConfiguredFeature<?, ?>> tree) {
        return new TreeGrower(Windswept.MOD_ID + ':' + name, Optional.empty(), Optional.of(tree), Optional.empty());
    }

}
