package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.datapack.WindsweptBiomes;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class WindsweptVillagerTypes {
    public static final DeferredRegister<VillagerType> VILLAGER_TYPES = DeferredRegister.create(Registries.VILLAGER_TYPE, Windswept.MOD_ID);

    public static final DeferredHolder<VillagerType, ?> ICE = register("ice");

    private static DeferredHolder<VillagerType, ?> register(String name) {
        return VILLAGER_TYPES.register(name, () -> new VillagerType(Windswept.MOD_ID + ':' + name));
    }


    public static void registerVillagerBiomes() {
        VillagerTrades.TRADES.isEmpty(); // referenced to call trade modification event to prevent crash

        VillagerType.BY_BIOME.replace(Biomes.ICE_SPIKES, ICE.get());
        VillagerType.BY_BIOME.replace(Biomes.FROZEN_PEAKS, ICE.get());
        VillagerType.BY_BIOME.replace(Biomes.GROVE, ICE.get());
        VillagerType.BY_BIOME.put(WindsweptBiomes.TUNDRA, ICE.get());
        VillagerType.BY_BIOME.put(WindsweptBiomes.PINE_BARRENS, VillagerType.TAIGA);
        VillagerType.BY_BIOME.put(WindsweptBiomes.CHESTNUT_FOREST, VillagerType.TAIGA);
        VillagerType.BY_BIOME.put(WindsweptBiomes.SNOWY_PINE_BARRENS, VillagerType.SNOW);
        VillagerType.BY_BIOME.put(WindsweptBiomes.SNOWY_CHESTNUT_FOREST, VillagerType.SNOW);
    }
}
