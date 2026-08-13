package com.rosemods.windswept.core.registry.datapack;

import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.common.world.modification.structure.StructureRepaletterEntry;
import com.teamabnormals.blueprint.core.registry.BlueprintDataPackRegistries;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.neoforged.neoforge.common.conditions.OrCondition;

import java.util.List;
import java.util.function.BiConsumer;

import static com.teamabnormals.blueprint.common.world.modification.structure.StructureRepaletterEntry.repalette;
import static com.teamabnormals.blueprint.common.world.modification.structure.StructureRepaletterEntry.simple;
import static com.rosemods.windswept.core.registry.WindsweptBlocks.*;

public final class WindsweptStructureRepaletters {
    public static final ResourceKey<StructureRepaletterEntry> IGLOO = create("igloo");
    public static final ResourceKey<StructureRepaletterEntry> VILLAGE_TAIGA = create("village_taiga");
    public static final ResourceKey<StructureRepaletterEntry> VILLAGE_SAVANNA = create("village_savanna");
    public static final ResourceKey<StructureRepaletterEntry> VILLAGE_SNOWY = create("village_snowy");
    public static final ResourceKey<StructureRepaletterEntry> VILLAGE_DESERT = create("village_desert");
    public static final ResourceKey<StructureRepaletterEntry> SHIPWRECK = create("shipwreck");

    public static final ResourceKey<StructureRepaletterEntry> WOODWORKS_IGLOO = create("woodworks_igloo");
    public static final ResourceKey<StructureRepaletterEntry> WOODWORKS_VILLAGE_SNOWY = create("woodworks_village_snowy");
    public static final ResourceKey<StructureRepaletterEntry> WOODWORKS_VILLAGE_FROZEN = create("woodworks_village_frozen");
    public static final ResourceKey<StructureRepaletterEntry> WOODWORKS_CHESTNUT_WEATHERED_HOUSE = create("woodworks_chestnut_weathered_house");
    public static final ResourceKey<StructureRepaletterEntry> WOODWORKS_GROVE_WEATHERED_HOUSE = create("woodworks_grove_weathered_house");

    public static void bootstrap(BootstrapContext<StructureRepaletterEntry> context) {
        HolderGetter<Structure> structures = context.lookup(Registries.STRUCTURE);

        context.register(IGLOO, repalette().repaletters(
                simple(Blocks.SNOW_BLOCK, SNOW_BRICKS.get()),
                simple(Blocks.OAK_WALL_SIGN, HOLLY_SIGNS.getSecond().get()),
                simple(Blocks.POTTED_CACTUS, POTTED_WHITE_ROSE.get()),
                simple(Blocks.SPRUCE_SLAB, HOLLY_SLAB.get()),
                simple(Blocks.SPRUCE_STAIRS, HOLLY_STAIRS.get()),
                simple(Blocks.MOSSY_STONE_BRICKS, CHISELED_ICICLE_BLOCK.get()),
                simple(Blocks.INFESTED_MOSSY_STONE_BRICKS, CHISELED_ICICLE_BLOCK.get()),
                simple(Blocks.OAK_TRAPDOOR, HOLLY_TRAPDOOR.get()),
                simple(Blocks.POLISHED_ANDESITE, Blocks.GOLD_BLOCK),
                simple(Blocks.IRON_BARS, ICICLE_BARS.get()),
                simple(Blocks.STONE_BRICKS, PACKED_ICE_BRICKS.get()),
                simple(Blocks.INFESTED_STONE_BRICKS, PACKED_ICE_BRICKS.get()),
                simple(Blocks.CHISELED_STONE_BRICKS, CHISELED_PACKED_ICE_BRICKS.get()),
                simple(Blocks.INFESTED_CHISELED_STONE_BRICKS, CHISELED_PACKED_ICE_BRICKS.get()),
                simple(Blocks.CRACKED_STONE_BRICKS, Blocks.PACKED_ICE),
                simple(Blocks.INFESTED_CRACKED_STONE_BRICKS, Blocks.PACKED_ICE),
                simple(Blocks.REDSTONE_TORCH, ICE_LANTERN.get())
        ).select(holder(structures, BuiltinStructures.IGLOO)));

        context.register(VILLAGE_TAIGA, repalette().repaletters(
                simple(Blocks.POTTED_SPRUCE_SAPLING, POTTED_BLUEBELLS.get()),
                simple(Blocks.POTTED_POPPY, POTTED_RED_ROSE.get()),
                simple(Blocks.POPPY, RED_ROSE.get()),
                simple(Blocks.LIGHT_GRAY_WOOL, SNOW_BRICKS.get()),
                simple(Blocks.BLUE_ICE, PACKED_ICE_BRICKS.get())
        ).select(holder(structures, BuiltinStructures.VILLAGE_TAIGA)));

        context.register(VILLAGE_SAVANNA, repalette().repaletters(
                simple(Blocks.ACACIA_SAPLING, MIMOSA.get()),
                simple(Blocks.POPPY, YELLOW_PETALS.get()),
                simple(Blocks.ACACIA_PRESSURE_PLATE, POTTED_MIMOSA.get())
        ).select(holder(structures, BuiltinStructures.VILLAGE_SAVANNA)));

        context.register(VILLAGE_SNOWY, repalette().repaletters(
                simple(Blocks.SPRUCE_PLANKS, CHESTNUT_PLANKS.get()),
                simple(Blocks.SPRUCE_STAIRS, CHESTNUT_STAIRS.get()),
                simple(Blocks.SPRUCE_SLAB, CHESTNUT_SLAB.get()),
                simple(Blocks.STRIPPED_SPRUCE_LOG, STRIPPED_CHESTNUT_LOG.get()),
                simple(Blocks.STRIPPED_SPRUCE_WOOD, STRIPPED_CHESTNUT_WOOD.get()),
                simple(Blocks.SPRUCE_FENCE, CHESTNUT_FENCE.get()),
                simple(Blocks.SPRUCE_FENCE_GATE, CHESTNUT_FENCE_GATE.get()),
                simple(Blocks.SPRUCE_DOOR, CHESTNUT_DOOR.get())
        ).select(holder(structures, BuiltinStructures.VILLAGE_SNOWY)));

        context.register(VILLAGE_DESERT, repalette().repaletters(
                simple(Blocks.POTTED_CACTUS, POTTED_BRITTLEBUSH.get()),
                simple(Blocks.SEA_PICKLE, POTTED_VERBENA.get()),
                simple(Blocks.POTTED_DEAD_BUSH, POTTED_SANDY_SPROUTS.get()),
                simple(Blocks.TERRACOTTA, ARKOSE_TILES.get()),
                simple(Blocks.SMOOTH_SANDSTONE_STAIRS, ARKOSE_BRICK_STAIRS.get()),
                simple(Blocks.COBBLESTONE, ARKOSE.get())
        ).select(holder(structures, BuiltinStructures.VILLAGE_DESERT)));

        context.register(SHIPWRECK, repalette().repaletters(
                simple(Blocks.JUNGLE_DOOR, PINE_DOOR.get()),
                simple(Blocks.JUNGLE_FENCE, PINE_FENCE.get()),
                simple(Blocks.JUNGLE_LOG, PINE_LOG.get()),
                simple(Blocks.JUNGLE_PLANKS, PINE_PLANKS.get()),
                simple(Blocks.JUNGLE_SLAB, PINE_SLAB.get()),
                simple(Blocks.JUNGLE_STAIRS, PINE_STAIRS.get()),
                simple(Blocks.JUNGLE_TRAPDOOR, PINE_TRAPDOOR.get())
        ).select(HolderSet.direct(structures.getOrThrow(BuiltinStructures.SHIPWRECK), structures.getOrThrow(BuiltinStructures.SHIPWRECK_BEACHED))));

        context.register(WOODWORKS_IGLOO, repalette().priority(50).repaletters(
                simple(Blocks.CHEST, HOLLY_CHEST.get()),
                simple(Blocks.LADDER, HOLLY_LADDER.get())
        ).select(holder(structures, BuiltinStructures.IGLOO)));

        context.register(WOODWORKS_VILLAGE_SNOWY, repalette().priority(50).repaletters(
                simple(Blocks.CHEST, CHESTNUT_CHEST.get()),
                simple(Blocks.BOOKSHELF, CHESTNUT_BOOKSHELF.get()),
                simple(Blocks.LADDER, CHESTNUT_LADDER.get())
        ).select(holder(structures, BuiltinStructures.VILLAGE_SNOWY)));

        context.register(WOODWORKS_VILLAGE_FROZEN, repalette().priority(50).repaletters(
                simple(Blocks.CHEST, HOLLY_CHEST.get()),
                simple(Blocks.BOOKSHELF, HOLLY_BOOKSHELF.get()),
                simple(Blocks.LADDER, HOLLY_LADDER.get())
        ).select(holder(structures, WindsweptStructures.VILLAGE_FROZEN)));

        context.register(WOODWORKS_CHESTNUT_WEATHERED_HOUSE, repalette().priority(50).repaletters(
                simple(Blocks.LADDER, CHESTNUT_LADDER.get())
        ).select(holder(structures, WindsweptStructures.CHESTNUT_WEATHERED_HOUSE)));

        context.register(WOODWORKS_GROVE_WEATHERED_HOUSE, repalette().priority(50).repaletters(
                simple(Blocks.BOOKSHELF, HOLLY_BOOKSHELF.get()),
                simple(Blocks.LADDER, HOLLY_LADDER.get())
        ).select(holder(structures, WindsweptStructures.GROVE_WEATHERED_HOUSE)));
    }

    public static void applyConditions(BiConsumer<ResourceKey<?>, ICondition> builder) {
        ICondition woodworks = new OrCondition(List.of(new ModLoadedCondition("woodworks"), new ModLoadedCondition("quark")));
        builder.accept(WOODWORKS_IGLOO, woodworks);
        builder.accept(WOODWORKS_VILLAGE_SNOWY, woodworks);
        builder.accept(WOODWORKS_VILLAGE_FROZEN, woodworks);
        builder.accept(WOODWORKS_CHESTNUT_WEATHERED_HOUSE, woodworks);
        builder.accept(WOODWORKS_GROVE_WEATHERED_HOUSE, woodworks);
    }

    private static HolderSet<Structure> holder(HolderGetter<Structure> getter, ResourceKey<Structure> key) {
        return HolderSet.direct(getter.getOrThrow(key));
    }

    private static ResourceKey<StructureRepaletterEntry> create(String name) {
        return ResourceKey.create(BlueprintDataPackRegistries.STRUCTURE_REPALETTERS, Windswept.location(name));
    }
}