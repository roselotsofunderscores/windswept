package com.rosemods.windswept.core.data.server;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.datapack.*;
import com.teamabnormals.blueprint.core.registry.BlueprintDataPackRegistries;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;

public class WindsweptDatapackProvider extends DatapackBuiltinEntriesProvider {
    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, WindsweptConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, WindsweptPlacedFeatures::bootstrap)
            .add(Registries.BIOME, WindsweptBiomes::bootstrap)
            .add(BlueprintDataPackRegistries.MODDED_BIOME_SLICES, WindsweptBiomeSlices::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, WindsweptBiomeModifiers::bootstrap)
            .add(BlueprintDataPackRegistries.STRUCTURE_REPALETTERS, WindsweptStructureRepaletters::bootstrap)
            .add(Registries.TRIM_MATERIAL, WindsweptTrimMaterials::bootstrap)
            .add(Registries.DAMAGE_TYPE, WindsweptDamageTypes::bootstrap)
            .add(Registries.BANNER_PATTERN, WindsweptBannerPatterns::bootstrap)
            .add(Registries.PAINTING_VARIANT, WindsweptPaintingVariants::bootstrap)
            .add(Registries.JUKEBOX_SONG, WindsweptJukeboxSongs::bootstrap);

    public WindsweptDatapackProvider(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), event.getLookupProvider(), BUILDER, Set.of(Windswept.MOD_ID));
    }

}
