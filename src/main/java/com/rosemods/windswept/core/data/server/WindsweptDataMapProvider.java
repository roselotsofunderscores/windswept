package com.rosemods.windswept.core.data.server;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

import static com.rosemods.windswept.core.registry.WindsweptBlocks.*;
import static com.rosemods.windswept.core.registry.WindsweptItems.*;

public class WindsweptDataMapProvider extends DataMapProvider {

    public WindsweptDataMapProvider(PackOutput output, CompletableFuture<Provider> provider) {
        super(output, provider);
    }

    @Override
    protected void gather(Provider provider) {
        this.builder(NeoForgeDataMaps.COMPOSTABLES)
                .add(HOLLY_LEAVES.getId(), new Compostable(0.30F), false)
                .add(HOLLY_LEAF_PILE.getId(), new Compostable(0.30F), false)
                .add(HOLLY_SAPLING.getId(), new Compostable(0.30F), false)
                .add(CHESTNUT_LEAVES.getId(), new Compostable(0.30F), false)
                .add(CHESTNUT_LEAF_PILE.getId(), new Compostable(0.30F), false)
                .add(CHESTNUT_SAPLING.getId(), new Compostable(0.30F), false)
                .add(PINE_LEAVES.getId(), new Compostable(0.30F), false)
                .add(PINE_LEAF_PILE.getId(), new Compostable(0.30F), false)
                .add(PINE_SAPLING.getId(), new Compostable(0.30F), false)
                .add(FLOWERING_ACACIA_LEAVES.getId(), new Compostable(0.30F), false)
                .add(FLOWERING_ACACIA_LEAF_PILE.getId(), new Compostable(0.30F), false)
                .add(FLOWERING_ACACIA_SAPLING.getId(), new Compostable(0.30F), false)
                .add(MOSSY_SPROUTS.getId(), new Compostable(0.50F), false)
                .add(DRY_MOSSY_SPROUTS.getId(), new Compostable(0.50F), false)
                .add(GELISOL_GRASS.getId(), new Compostable(0.50F), false)
                .add(SNOWY_SPROUTS.getId(), new Compostable(0.50F), false)
                .add(SANDY_SPROUTS.getId(), new Compostable(0.50F), false)
                .add(DRY_MOSS_CARPET.getId(), new Compostable(0.30F), false)
                .add(DRY_MOSS_BLOCK.getId(), new Compostable(0.65F), false)
                .add(RED_ROSE.getId(), new Compostable(0.65F), false)
                .add(BLUE_ROSE.getId(), new Compostable(0.65F), false)
                .add(WHITE_ROSE.getId(), new Compostable(0.65F), false)
                .add(YELLOW_ROSE.getId(), new Compostable(0.65F), false)
                .add(FOXGLOVE.getId(), new Compostable(0.65F), false)
                .add(BLUEBELLS.getId(), new Compostable(0.65F), false)
                .add(SNOWDROP.getId(), new Compostable(0.65F), false)
                .add(MIMOSA.getId(), new Compostable(0.65F), false)
                .add(MOSS_CAMPION.getId(), new Compostable(0.65F), false)
                .add(WILD_GINGER.getId(), new Compostable(0.65F), false)
                .add(NIGHTSHADE.getId(), new Compostable(0.65F), false)
                .add(BRITTLEBUSH.getId(), new Compostable(0.65F), false)
                .add(LARKSPUR.getId(), new Compostable(0.65F), false)
                .add(VERBENA.getId(), new Compostable(0.65F), false)
                .add(LUPINE.getId(), new Compostable(0.65F), false)
                .add(LIONS_TAIL.getId(), new Compostable(0.65F), false)
                .add(DESERT_LILY.getId(), new Compostable(0.65F), false)
                .add(YELLOW_PETALS.getId(), new Compostable(0.30F), false)
                .add(RED_ROSE_BUSH.getId(), new Compostable(0.65F), false)
                .add(BLUE_ROSE_BUSH.getId(), new Compostable(0.65F), false)
                .add(WHITE_ROSE_BUSH.getId(), new Compostable(0.65F), false)
                .add(YELLOW_ROSE_BUSH.getId(), new Compostable(0.65F), false)
                .add(LAVENDER.getId(), new Compostable(0.65F), false)
                .add(LAVENDER_BALE.getId(), new Compostable(0.85F), false)
                .add(LAVENDER_THATCH.getId(), new Compostable(0.65F), false)
                .add(LAVENDER_THATCH_STAIRS.getId(), new Compostable(0.65F), false)
                .add(LAVENDER_THATCH_SLAB.getId(), new Compostable(0.65F), false)
                .add(PINECONE.getId(), new Compostable(0.65F), false)
                .add(PINECONE_BLOCK.getId(), new Compostable(1.0F), false)
                .add(PINECONE_JAM_BLOCK.getId(), new Compostable(1.0F), false)
                .add(HOLLY_WREATH.getId(), new Compostable(0.65F), false)
                .add(PINECONE_WREATH.getId(), new Compostable(0.65F), false)
                .add(VINE_WREATH.getId(), new Compostable(0.65F), false)
                .add(CHERRY_WREATH.getId(), new Compostable(0.65F), false)
                .add(NIGHTSHADE_BOUQUET.getId(), new Compostable(0.65F), false)
                .add(CHESTNUT_CRATE.getId(), new Compostable(1.0F), false)
                .add(ROASTED_CHESTNUT_CRATE.getId(), new Compostable(1.0F), false)
                .add(GINGER_ROOT_CRATE.getId(), new Compostable(1.0F), false)
                .add(HOLLY_BERRY_BASKET.getId(), new Compostable(1.0F), false)
                .add(WILD_BERRY_BASKET.getId(), new Compostable(1.0F), false)
                .add(HOLLY_BERRIES, new Compostable(0.65F), false)
                .add(WILD_BERRIES, new Compostable(0.65F), false)
                .add(WILD_BERRY_PIPS, new Compostable(0.30F), false)
                .add(CHESTNUTS, new Compostable(0.65F), false)
                .add(ROASTED_CHESTNUTS, new Compostable(0.85F), false)
                .add(GINGER_ROOT, new Compostable(0.65F), false)
                .add(GINGERBREAD_COOKIE, new Compostable(0.85F), false)
                .add(MUTTON_PIE.getId(), new Compostable(1.0F), false)
                .add(CHRISTMAS_PUDDING.getId(), new Compostable(1.0F), false);
    }
}
