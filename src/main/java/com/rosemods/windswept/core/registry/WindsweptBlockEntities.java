package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.common.block_entity.*;
import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.core.util.registry.BlockEntitySubRegistryHelper;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Set;

public final class WindsweptBlockEntities {
    public static final BlockEntitySubRegistryHelper BLOCK_ENTITIES = Windswept.REGISTRY_HELPER.getBlockEntitySubHelper();

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WillOTheWispBlockEntity>> WILL_O_THE_WISP = BLOCK_ENTITIES.createBlockEntity("will_o_the_wisp", WillOTheWispBlockEntity::new, () -> Set.of(WindsweptBlocks.WILL_O_THE_WISP.get()));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<NightFairyLightBlockEntity>> NIGHT_FAIRY_LIGHT = BLOCK_ENTITIES.createBlockEntity("night_fairy_light", NightFairyLightBlockEntity::new, () -> Set.of(WindsweptBlocks.NIGHT_FAIRY_LIGHT.get()));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SuspiciousSnowBlockEntity>> SUSPICIOUS_SNOW = BLOCK_ENTITIES.createBlockEntity("suspicious_snow", SuspiciousSnowBlockEntity::new, () -> Set.of(WindsweptBlocks.SUSPICIOUS_SNOW.get()));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CarvedPineconeBlockEntity>> CARVED_PINECONE = BLOCK_ENTITIES.createBlockEntity("fearful_block", CarvedPineconeBlockEntity::new, () -> Set.of(WindsweptBlocks.CARVED_PINECONE_BLOCK.get()));

}

