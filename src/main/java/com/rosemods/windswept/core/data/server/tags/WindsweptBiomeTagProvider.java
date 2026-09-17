package com.rosemods.windswept.core.data.server.tags;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.data.server.WindsweptDatapackProvider;
import com.teamabnormals.blueprint.core.util.TagUtil;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import static com.rosemods.windswept.core.other.tags.WindsweptBiomeTags.*;
import static com.rosemods.windswept.core.registry.datapack.WindsweptBiomes.*;
import static net.minecraft.world.level.biome.Biomes.*;

public class WindsweptBiomeTagProvider extends BiomeTagsProvider {

    public WindsweptBiomeTagProvider(GatherDataEvent event, WindsweptDatapackProvider dataPack) {
        super(event.getGenerator().getPackOutput(), dataPack.getRegistryProvider(), Windswept.MOD_ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(HAS_BLUEBELLS).add(DARK_FOREST, BIRCH_FOREST, OLD_GROWTH_BIRCH_FOREST, OLD_GROWTH_SPRUCE_TAIGA, CHESTNUT_FOREST, SNOWY_CHESTNUT_FOREST);
        this.tag(HAS_LIONS_TAIL).addTag(BiomeTags.IS_SAVANNA).add(WOODED_BADLANDS).addOptional(ResourceLocation.fromNamespaceAndPath("atmospheric", "spiny_thicket"));
        this.tag(HAS_RARE_CHESTNUT_TREES).add(DARK_FOREST, TAIGA, FOREST, WINDSWEPT_FOREST, OLD_GROWTH_BIRCH_FOREST, OLD_GROWTH_SPRUCE_TAIGA, OLD_GROWTH_PINE_TAIGA, SNOWY_TAIGA, BIRCH_FOREST, FLOWER_FOREST, WINDSWEPT_HILLS);
        this.tag(HAS_RARE_HOLLY_TREES).add(TAIGA, SNOWY_TAIGA, OLD_GROWTH_SPRUCE_TAIGA);
        this.tag(HAS_RARE_SNOWY_HOLLY_TREES).add(FROZEN_PEAKS, JAGGED_PEAKS).addOptional(ResourceLocation.fromNamespaceAndPath("atmospheric", "kousa_jungle"));
        this.tag(HAS_RED_ROSE).add(TAIGA, SNOWY_TAIGA, PINE_BARRENS, SNOWY_PINE_BARRENS, OLD_GROWTH_SPRUCE_TAIGA, OLD_GROWTH_PINE_TAIGA, CHERRY_GROVE);
        this.tag(HAS_BLUE_ROSE).add(CHESTNUT_FOREST, SNOWY_CHESTNUT_FOREST).addOptional(ResourceLocation.fromNamespaceAndPath("atmospheric", "kousa_jungle"));
        this.tag(HAS_WHITE_ROSE).add(SNOWY_CHESTNUT_FOREST, SNOWY_TAIGA, CHERRY_GROVE).addOptional(ResourceLocation.fromNamespaceAndPath("atmospheric", "kousa_jungle")).addOptional(ResourceLocation.fromNamespaceAndPath("environmental", "blossom_woods"));
        this.tag(HAS_YELLOW_ROSE).add(DARK_FOREST, OLD_GROWTH_SPRUCE_TAIGA, OLD_GROWTH_PINE_TAIGA, PINE_BARRENS, SNOWY_PINE_BARRENS);
        this.tag(HAS_WILD_BERRIES).add(SNOWY_PLAINS, SNOWY_SLOPES, JAGGED_PEAKS, FROZEN_PEAKS, GROVE);
        this.tag(HAS_BROWN_GOAT).add(TUNDRA);

        this.tag(TagUtil.biomeTag(Windswept.MOD_ID, "has_structure/grove_weathered_house")).add(GROVE, FROZEN_PEAKS, JAGGED_PEAKS);
        this.tag(TagUtil.biomeTag(Windswept.MOD_ID, "has_structure/chestnut_weathered_house")).add(CHESTNUT_FOREST, SNOWY_CHESTNUT_FOREST);
        this.tag(TagUtil.biomeTag(Windswept.MOD_ID, "has_structure/frozen_village")).add(TUNDRA);
        this.tag(TagUtil.biomeTag(Windswept.MOD_ID, "has_structure/pine_totem")).add(PINE_BARRENS);
        this.tag(TagUtil.biomeTag(Windswept.MOD_ID, "has_structure/snowy_pine_totem")).add(SNOWY_PINE_BARRENS);

        this.tag(IS_CHESTNUT_FOREST).add(CHESTNUT_FOREST, SNOWY_CHESTNUT_FOREST);
        this.tag(IS_PINE_BARRENS).add(PINE_BARRENS, SNOWY_PINE_BARRENS, OLD_GROWTH_PINE_TAIGA);
        this.tag(IS_LAVENDER).add(LAVENDER_HILLS, LAVENDER_FIELDS);

        this.tag(TagUtil.biomeTag("autumnity", "has_feature/spotted_maple_tree/orange")).add(CHESTNUT_FOREST, SNOWY_CHESTNUT_FOREST);
        this.tag(TagUtil.biomeTag("autumnity", "has_feature/spotted_maple_tree/red")).add(PINE_BARRENS, SNOWY_PINE_BARRENS);

        this.tag(TagUtil.biomeTag("environmental", "has_spawn/yak")).add(TUNDRA, PINE_BARRENS, SNOWY_PINE_BARRENS);
        this.tag(TagUtil.biomeTag("environmental", "has_spawn/reindeer")).add(TUNDRA);
        this.tag(TagUtil.biomeTag("environmental", "has_spawn/sheep")).addTag(IS_LAVENDER).addTag(IS_PINE_BARRENS).addTag(IS_CHESTNUT_FOREST).add(FLOWERING_SAVANNA);
        this.tag(TagUtil.biomeTag("environmental", "has_feature/dwarf_spruce_sparse")).add(TUNDRA);

        //this.tag(BlueprintBiomeTags.IS_GRASSLAND).add(LAVENDER_HILLS, LAVENDER_FIELDS);
        this.tag(BiomeTags.IS_SAVANNA).add(FLOWERING_SAVANNA);
        this.tag(BiomeTags.IS_MOUNTAIN).add(SNOWY_PINE_BARRENS, LAVENDER_HILLS);
        this.tag(BiomeTags.IS_OVERWORLD).add(CHESTNUT_FOREST, SNOWY_CHESTNUT_FOREST, PINE_BARRENS, SNOWY_PINE_BARRENS, LAVENDER_HILLS, LAVENDER_FIELDS, TUNDRA, FLOWERING_SAVANNA);
        this.tag(BiomeTags.IS_FOREST).add(CHESTNUT_FOREST, SNOWY_CHESTNUT_FOREST, PINE_BARRENS, SNOWY_PINE_BARRENS);
        this.tag(BiomeTags.HAS_IGLOO).add(SNOWY_CHESTNUT_FOREST, SNOWY_PINE_BARRENS, TUNDRA);
        this.tag(BiomeTags.HAS_VILLAGE_SNOWY).add(SNOWY_CHESTNUT_FOREST, SNOWY_PINE_BARRENS);
        this.tag(BiomeTags.IS_TAIGA).add(CHESTNUT_FOREST, SNOWY_CHESTNUT_FOREST, PINE_BARRENS, SNOWY_PINE_BARRENS);
        this.tag(BiomeTags.HAS_RUINED_PORTAL_STANDARD).add(CHESTNUT_FOREST, SNOWY_CHESTNUT_FOREST, PINE_BARRENS, SNOWY_PINE_BARRENS);
        this.tag(BiomeTags.HAS_MINESHAFT).add(CHESTNUT_FOREST, SNOWY_CHESTNUT_FOREST, PINE_BARRENS, SNOWY_PINE_BARRENS, TUNDRA);
        this.tag(BiomeTags.STRONGHOLD_BIASED_TO).add(CHESTNUT_FOREST, SNOWY_CHESTNUT_FOREST, PINE_BARRENS, SNOWY_PINE_BARRENS, TUNDRA);
        this.tag(BiomeTags.HAS_PILLAGER_OUTPOST).add(TUNDRA);
        this.tag(BiomeTags.IS_HILL).add(SNOWY_PINE_BARRENS, LAVENDER_HILLS);
        this.tag(Tags.Biomes.IS_SNOWY).add(SNOWY_CHESTNUT_FOREST, SNOWY_PINE_BARRENS, TUNDRA, DEEP_FROZEN_OCEAN);
        this.tag(Tags.Biomes.IS_COLD).add(SNOWY_CHESTNUT_FOREST, SNOWY_PINE_BARRENS, TUNDRA);
        this.tag(Tags.Biomes.IS_HOT).add(FLOWERING_SAVANNA);
        //this.tag(Tags.Biomes.IS_CONIFEROUS).add(CHESTNUT_FOREST, SNOWY_CHESTNUT_FOREST, PINE_BARRENS, SNOWY_PINE_BARRENS);
        //this.tag(Tags.Biomes.IS_DENSE).add(CHESTNUT_FOREST, SNOWY_CHESTNUT_FOREST, PINE_BARRENS, SNOWY_PINE_BARRENS);
        //this.tag(Tags.Biomes.IS_DENSE_OVERWORLD).add(CHESTNUT_FOREST, SNOWY_CHESTNUT_FOREST, PINE_BARRENS, SNOWY_PINE_BARRENS);
    }

}
