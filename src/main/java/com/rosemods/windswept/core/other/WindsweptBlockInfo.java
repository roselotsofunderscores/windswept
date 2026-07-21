package com.rosemods.windswept.core.other;

import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.core.util.DataUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;

import static com.rosemods.windswept.core.registry.WindsweptBlocks.*;
import static com.rosemods.windswept.core.registry.WindsweptItems.*;

public final class WindsweptBlockInfo {
    public static void changeLocalisation() {
        DataUtil.changeBlockLocalization(Blocks.SNOW, Windswept.MOD_ID, "snow_carpet");
        DataUtil.changeBlockLocalization(Blocks.SNOW_BLOCK, "minecraft", "snow");
    }

    public static void registerCompostables() {
        registerCompostable(WILD_BERRIES.get(), .3f);
        registerCompostable(WILD_BERRY_PIPS.get(), .3f);
        registerCompostable(HOLLY_BERRIES.get(), .3f);
        registerCompostable(CHESTNUTS.get(), .3f);
        registerCompostable(GINGER_ROOT.get(), .3f);
        registerCompostable(ROASTED_CHESTNUTS.get(), .3f);
        registerCompostable(PINECONE.get(), .3f);

        registerCompostable(CHESTNUT_CRATE.get(), 1f);
        registerCompostable(ROASTED_CHESTNUT_CRATE.get(), 1f);
        registerCompostable(GINGER_ROOT_CRATE.get(), 1f);
        registerCompostable(HOLLY_BERRY_BASKET.get(), 1f);
        registerCompostable(WILD_BERRY_BASKET.get(), 1f);

        registerCompostable(RED_ROSE.get(), .65f);
        registerCompostable(BLUE_ROSE.get(), .65f);
        registerCompostable(WHITE_ROSE.get(), .65f);
        registerCompostable(YELLOW_ROSE.get(), .65f);
        registerCompostable(FOXGLOVE.get(), .65f);
        registerCompostable(SNOWDROP.get(), .65f);
        registerCompostable(MOSS_CAMPION.get(), .65f);
        registerCompostable(NIGHTSHADE.get(), .65f);
        registerCompostable(BLUEBELLS.get(), .65f);
        registerCompostable(MIMOSA.get(), .65f);
        registerCompostable(YELLOW_PETALS.get(), .3f);
        registerCompostable(LAVENDER.get(), .3f);

        registerCompostable(RED_ROSE_BUSH.get(), .65f);
        registerCompostable(BLUE_ROSE_BUSH.get(), .65f);
        registerCompostable(WHITE_ROSE_BUSH.get(), .65f);
        registerCompostable(YELLOW_ROSE_BUSH.get(), .65f);
        registerCompostable(LUPINE.get(), .65f);
        registerCompostable(LIONS_TAIL.get(), .65f);

        registerCompostable(HOLLY_LEAVES.get(), .3f);
        registerCompostable(CHESTNUT_LEAVES.get(), .3f);
        registerCompostable(PINE_LEAVES.get(), .3f);
        registerCompostable(HOLLY_SAPLING.get(), .3f);
        registerCompostable(CHESTNUT_SAPLING.get(), .3f);
        registerCompostable(PINE_SAPLING.get(), .3f);

        registerCompostable(SNOWY_SPROUTS.get(), .5f);
        registerCompostable(GELISOL_GRASS.get(), .5f);
        registerCompostable(DRY_MOSSY_SPROUTS.get(), .5f);
        registerCompostable(MOSSY_SPROUTS.get(), .5f);

        registerCompostable(GINGERBREAD_COOKIE.get(), .85f);
        registerCompostable(MUTTON_PIE.get(), 1f);
        registerCompostable(CHRISTMAS_PUDDING.get(), 1f);

        registerCompostable(PINECONE_BLOCK.get(), 1f);
        registerCompostable(PINECONE_JAM_BLOCK.get(), 1f);
    }

    private static void registerCompostable(net.minecraft.world.level.ItemLike item, float chance) {
        ComposterBlock.COMPOSTABLES.put(item.asItem(), chance);
    }

    public static void registerFlammables() {
        DataUtil.registerFlammable(STRIPPED_HOLLY_LOG.get(), 5, 5);
        DataUtil.registerFlammable(STRIPPED_HOLLY_WOOD.get(), 5, 5);
        DataUtil.registerFlammable(HOLLY_LOG.get(), 5, 5);
        DataUtil.registerFlammable(HOLLY_WOOD.get(), 5, 5);
        DataUtil.registerFlammable(HOLLY_PLANKS.get(), 5, 20);
        DataUtil.registerFlammable(HOLLY_SLAB.get(), 5, 20);
        DataUtil.registerFlammable(HOLLY_STAIRS.get(), 5, 20);
        DataUtil.registerFlammable(HOLLY_FENCE.get(), 5, 20);
        DataUtil.registerFlammable(HOLLY_FENCE_GATE.get(), 5, 20);
        DataUtil.registerFlammable(HOLLY_BOARDS.get(), 5, 20);
        DataUtil.registerFlammable(HOLLY_LEAVES.get(), 30, 60);
        DataUtil.registerFlammable(HOLLY_BOOKSHELF.get(), 30, 20);
        DataUtil.registerFlammable(HOLLY_BEEHIVE.get(), 5, 20);
        DataUtil.registerFlammable(HOLLY_LEAF_PILE.get(), 30, 60);

        DataUtil.registerFlammable(STRIPPED_CHESTNUT_LOG.get(), 5, 5);
        DataUtil.registerFlammable(STRIPPED_CHESTNUT_WOOD.get(), 5, 5);
        DataUtil.registerFlammable(CHESTNUT_LOG.get(), 5, 5);
        DataUtil.registerFlammable(CHESTNUT_WOOD.get(), 5, 5);
        DataUtil.registerFlammable(CHESTNUT_PLANKS.get(), 5, 20);
        DataUtil.registerFlammable(CHESTNUT_SLAB.get(), 5, 20);
        DataUtil.registerFlammable(CHESTNUT_STAIRS.get(), 5, 20);
        DataUtil.registerFlammable(CHESTNUT_FENCE.get(), 5, 20);
        DataUtil.registerFlammable(CHESTNUT_FENCE_GATE.get(), 5, 20);
        DataUtil.registerFlammable(CHESTNUT_BOARDS.get(), 5, 20);
        DataUtil.registerFlammable(CHESTNUT_LEAVES.get(), 30, 60);
        DataUtil.registerFlammable(CHESTNUT_BOOKSHELF.get(), 30, 20);
        DataUtil.registerFlammable(CHESTNUT_BEEHIVE.get(), 5, 20);
        DataUtil.registerFlammable(CHESTNUT_LEAF_PILE.get(), 30, 60);

        DataUtil.registerFlammable(STRIPPED_PINE_LOG.get(), 5, 5);
        DataUtil.registerFlammable(STRIPPED_PINE_WOOD.get(), 5, 5);
        DataUtil.registerFlammable(PINE_LOG.get(), 5, 5);
        DataUtil.registerFlammable(PINE_WOOD.get(), 5, 5);
        DataUtil.registerFlammable(PINE_PLANKS.get(), 5, 20);
        DataUtil.registerFlammable(PINE_SLAB.get(), 5, 20);
        DataUtil.registerFlammable(PINE_STAIRS.get(), 5, 20);
        DataUtil.registerFlammable(PINE_FENCE.get(), 5, 20);
        DataUtil.registerFlammable(PINE_FENCE_GATE.get(), 5, 20);
        DataUtil.registerFlammable(PINE_BOARDS.get(), 5, 20);
        DataUtil.registerFlammable(PINE_LEAVES.get(), 30, 60);
        DataUtil.registerFlammable(PINE_BOOKSHELF.get(), 30, 20);
        DataUtil.registerFlammable(PINE_BEEHIVE.get(), 5, 20);
        DataUtil.registerFlammable(PINE_LEAF_PILE.get(), 30, 60);

        DataUtil.registerFlammable(PINECONE_BLOCK.get(), 5, 20);
        DataUtil.registerFlammable(CARVED_PINECONE_BLOCK.get(), 5, 20);
        DataUtil.registerFlammable(PINECONE_SHINGLES.get(), 5, 20);
        DataUtil.registerFlammable(PINECONE_SHINGLE_STAIRS.get(), 5, 20);
        DataUtil.registerFlammable(PINECONE_SHINGLE_SLAB.get(), 5, 20);

        DataUtil.registerFlammable(HOLLY_BERRY_BASKET.get(), 5, 20);
        DataUtil.registerFlammable(CHESTNUT_CRATE.get(), 5, 20);
        DataUtil.registerFlammable(GINGER_ROOT_CRATE.get(), 5, 20);
        DataUtil.registerFlammable(ROASTED_CHESTNUT_CRATE.get(), 5, 20);

        DataUtil.registerFlammable(WILD_BERRY_BUSH.get(), 60, 100);
        DataUtil.registerFlammable(SNOWY_SPROUTS.get(), 60, 100);
        DataUtil.registerFlammable(GELISOL_GRASS.get(), 60, 100);
        DataUtil.registerFlammable(DRY_MOSSY_SPROUTS.get(), 60, 100);
        DataUtil.registerFlammable(LAVENDER.get(), 60, 100);
        DataUtil.registerFlammable(RED_ROSE.get(), 60, 100);
        DataUtil.registerFlammable(BLUE_ROSE.get(), 60, 100);
        DataUtil.registerFlammable(WHITE_ROSE.get(), 60, 100);
        DataUtil.registerFlammable(YELLOW_ROSE.get(), 60, 100);
        DataUtil.registerFlammable(FOXGLOVE.get(), 60, 100);
        DataUtil.registerFlammable(SNOWDROP.get(), 60, 100);
        DataUtil.registerFlammable(MOSS_CAMPION.get(), 60, 100);
        DataUtil.registerFlammable(NIGHTSHADE.get(), 60, 100);
        DataUtil.registerFlammable(BLUEBELLS.get(), 60, 100);
        DataUtil.registerFlammable(MIMOSA.get(), 60, 100);
        DataUtil.registerFlammable(LUPINE.get(), 60, 100);
        registerTallFlower(RED_ROSE_BUSH.get());
        registerTallFlower(BLUE_ROSE_BUSH.get());
        registerTallFlower(WHITE_ROSE_BUSH.get());
        registerTallFlower(YELLOW_ROSE_BUSH.get());
        DataUtil.registerFlammable(LIONS_TAIL.get(), 60, 100);
    }

    private static void registerTallFlower(Block block) {
        DataUtil.registerFlammable(block, 60, 100);
    }

}