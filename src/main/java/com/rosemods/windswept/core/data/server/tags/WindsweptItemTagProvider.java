package com.rosemods.windswept.core.data.server.tags;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.data.server.WindsweptDatapackProvider;
import com.rosemods.windswept.core.other.tags.WindsweptBlockTags;
import com.rosemods.windswept.core.other.tags.WindsweptItemTags;
import com.teamabnormals.blueprint.core.other.tags.BlueprintBlockTags;
import com.teamabnormals.blueprint.core.other.tags.BlueprintItemTags;
import com.teamabnormals.blueprint.core.util.TagUtil;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import static com.rosemods.windswept.core.registry.WindsweptBlocks.*;
import static com.rosemods.windswept.core.registry.WindsweptItems.*;

public class WindsweptItemTagProvider extends ItemTagsProvider {

    public WindsweptItemTagProvider(GatherDataEvent event, WindsweptBlockTagProvider blockTags, WindsweptDatapackProvider dataPack) {
        super(event.getGenerator().getPackOutput(), dataPack.getRegistryProvider(), blockTags.contentsGetter(), Windswept.MOD_ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        //windswept
        this.tag(WindsweptItemTags.ROSES).add(RED_ROSE.asItem(), BLUE_ROSE.asItem(), WHITE_ROSE.asItem(), YELLOW_ROSE.asItem(), Items.WITHER_ROSE, Items.ROSE_BUSH, RED_ROSE_BUSH.asItem(), BLUE_ROSE_BUSH.asItem(), WHITE_ROSE_BUSH.asItem(), YELLOW_ROSE_BUSH.asItem());
        this.copy(WindsweptBlockTags.HOLLY_LOGS, WindsweptItemTags.HOLLY_LOGS);
        this.copy(WindsweptBlockTags.CHESTNUT_LOGS, WindsweptItemTags.CHESTNUT_LOGS);
        this.copy(WindsweptBlockTags.PINE_LOGS, WindsweptItemTags.PINE_LOGS);

        //c
        this.tag(Tags.Items.BUCKETS_EMPTY).add(WOODEN_BUCKET.get());
        this.tag(Tags.Items.BUCKETS_MILK).add(WOODEN_MILK_BUCKET.get());
        this.tag(Tags.Items.BUCKETS_POWDER_SNOW).add(WOODEN_POWDER_SNOW_BUCKET.get());
        this.tag(Tags.Items.BUCKETS_WATER).add(WOODEN_WATER_BUCKET.get());

        this.tag(WindsweptItemTags.COOKED_MUTTON).add(Items.COOKED_MUTTON);
        this.tag(TagUtil.itemTag("c", "stripped_logs")).add(STRIPPED_HOLLY_LOG.asItem(), STRIPPED_CHESTNUT_LOG.asItem(), STRIPPED_PINE_LOG.asItem());
        this.tag(TagUtil.itemTag("c", "stripped_wood")).add(STRIPPED_HOLLY_WOOD.asItem(), STRIPPED_CHESTNUT_WOOD.asItem(), STRIPPED_PINE_WOOD.asItem());
        this.tag(TagUtil.itemTag("c", "buckets/honey")).add(WOODEN_HONEY_BUCKET.get());
        this.tag(TagUtil.itemTag("c", "buckets/chocolate")).add(WOODEN_CHOCOLATE_BUCKET.get());
        this.tag(TagUtil.itemTag("c", "storage_blocks")).add(LAVENDER_BALE.asItem(), CHESTNUT_CRATE.asItem(), ROASTED_CHESTNUT_CRATE.asItem(), GINGER_ROOT_CRATE.asItem(), HOLLY_BERRY_BASKET.asItem(), WILD_BERRY_BASKET.asItem(), PINECONE_BLOCK.asItem());
        this.tag(TagUtil.itemTag("c", "animal_foods")).add(WILD_BERRY_PIPS.get(), HOLLY_BERRIES.get(), FROZEN_FLESH.asItem());
        this.tag(Tags.Items.SEEDS).add(WILD_BERRY_PIPS.get());
        this.tag(ItemTags.DYEABLE).add(SNOW_BOOTS.get());
        this.tag(Tags.Items.FEATHERS).add(ELDER_FEATHER.get());
        this.copy(Tags.Blocks.CHAINS, Tags.Items.CHAINS);
        this.copy(Tags.Blocks.BOOKSHELVES, Tags.Items.BOOKSHELVES);
        this.copy(Tags.Blocks.CHESTS_WOODEN, Tags.Items.CHESTS_WOODEN);
        this.copy(Tags.Blocks.CHESTS_TRAPPED, Tags.Items.CHESTS_TRAPPED);
        this.copy(BlueprintBlockTags.LADDERS, BlueprintItemTags.LADDERS);
        this.copy(WindsweptBlockTags.QUARK_LADDERS, TagUtil.itemTag("quark", "ladders"));
        this.copy(BlueprintBlockTags.WOODEN_BOOKSHELVES, BlueprintItemTags.WOODEN_BOOKSHELVES);
        this.copy(BlueprintBlockTags.WOODEN_LADDERS, BlueprintItemTags.WOODEN_LADDERS);
        this.copy(BlueprintBlockTags.WOODEN_BEEHIVES, BlueprintItemTags.WOODEN_BEEHIVES);
        this.copy(BlueprintBlockTags.WOODEN_TRAPPED_CHESTS, BlueprintItemTags.WOODEN_TRAPPED_CHESTS);
        this.copy(BlueprintBlockTags.WOODEN_CHISELED_BOOKSHELVES, BlueprintItemTags.WOODEN_CHISELED_BOOKSHELVES);

        //foods
        this.tag(TagUtil.itemTag("c", "foods")).add(GINGERBREAD_COOKIE.get(), CANDY_CANE.get(), CHESTNUTS.get(), ROASTED_CHESTNUTS.get(), HOLLY_BERRIES.get(), FROZEN_FLESH.asItem());
        this.tag(TagUtil.itemTag("c", "foods/cookie")).add(GINGERBREAD_COOKIE.get());
        this.tag(TagUtil.itemTag("c", "foods/candy")).add(CANDY_CANE.get());
        this.tag(TagUtil.itemTag("c", "foods/ginger")).add(GINGER_ROOT.get());
        this.tag(TagUtil.itemTag("c", "foods/food_poisoning")).add(HOLLY_BERRIES.get(), FROZEN_FLESH.asItem());
        this.tag(TagUtil.itemTag("c", "seeds/wild_berry")).add(WILD_BERRY_PIPS.get());
        this.tag(TagUtil.itemTag("c", "raw_meat")).add(GOAT.get());
        this.tag(TagUtil.itemTag("c", "raw_goat")).add(GOAT.get());
        this.tag(TagUtil.itemTag("c", "cooked_meat")).add(COOKED_GOAT.get());
        this.tag(TagUtil.itemTag("c", "cooked_goat")).add(COOKED_GOAT.get());
        this.tag(TagUtil.itemTag("c", "foods/berry")).add(WILD_BERRIES.get());
        
        //crops
        this.tag(TagUtil.itemTag("c", "crops")).add(GINGER_ROOT.get());
        this.tag(TagUtil.itemTag("c", "crops/ginger")).add(GINGER_ROOT.get());

        //caverns and chasms
        this.tag(TagUtil.itemTag("caverns_and_chasms", "rat_food")).add(FROZEN_FLESH.asItem());

        //connectable chains
        this.tag(TagUtil.itemTag("connectiblechains", "hangable_items")).add(PINECONE.asItem(), FAIRY_LIGHT.asItem(), SOUL_FAIRY_LIGHT.asItem(), REDSTONE_FAIRY_LIGHT.asItem(), NIGHT_FAIRY_LIGHT.asItem(), CUPRIC_FAIRY_LIGHT.asItem(), ENDER_FAIRY_LIGHT.asItem(), ICE_LANTERN.asItem());

        //snowy spirit
        this.copy(WindsweptBlockTags.GINGERBREADS, TagUtil.itemTag("snowyspirit", "gingerbreads"));

        //boatload
        this.tag(BlueprintItemTags.FURNACE_BOATS).add(HOLLY_FURNACE_BOAT.get(), CHESTNUT_FURNACE_BOAT.get(), PINE_FURNACE_BOAT.get());
        this.tag(BlueprintItemTags.LARGE_BOATS).add(LARGE_HOLLY_BOAT.get(), LARGE_CHESTNUT_BOAT.get(), LARGE_PINE_BOAT.get());

        //vanilla
        this.tag(ItemTags.DECORATED_POT_SHERDS).add(HOOT_POTTERY_SHERD.get(), PLUMAGE_POTTERY_SHERD.get(), OFFSHOOT_POTTERY_SHERD.get(), FLAKE_POTTERY_SHERD.get(), DRUPES_POTTERY_SHERD.get());
        this.tag(ItemTags.TRIM_MATERIALS).add(ICICLES.asItem(), PINECONE.asItem());
        this.tag(ItemTags.ARROWS).add(FROST_ARROW.get());
        this.tag(ItemTags.FOX_FOOD).add(WILD_BERRIES.get());
        this.tag(ItemTags.BOATS).add(HOLLY_BOAT.getFirst().get(), CHESTNUT_BOAT.getFirst().get(), PINE_BOAT.getFirst().get());
        this.tag(ItemTags.CHEST_BOATS).add(HOLLY_BOAT.getSecond().get(), CHESTNUT_BOAT.getSecond().get(), PINE_BOAT.getSecond().get());
        this.tag(ItemTags.SIGNS).add(HOLLY_SIGNS.getFirst().asItem(), CHESTNUT_SIGNS.getFirst().asItem(), PINE_SIGNS.getFirst().asItem());
        this.tag(ItemTags.HANGING_SIGNS).add(HOLLY_HANGING_SIGNS.getFirst().asItem(), CHESTNUT_HANGING_SIGNS.getFirst().asItem(), PINE_HANGING_SIGNS.getFirst().asItem());
        this.tag(ItemTags.MEAT).add(FROZEN_FLESH.asItem(), GOAT.asItem(), COOKED_GOAT.asItem());
        this.tag(ItemTags.WOLF_FOOD).add(FROZEN_FLESH.asItem());
        this.copy(BlockTags.LEAVES, ItemTags.LEAVES);
        this.copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);
        this.copy(BlockTags.PLANKS, ItemTags.PLANKS);
        this.copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);
        this.copy(BlockTags.SLABS, ItemTags.SLABS);
        this.copy(BlockTags.FLOWERS, ItemTags.FLOWERS);
        this.copy(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS);
        this.copy(BlockTags.TALL_FLOWERS, ItemTags.TALL_FLOWERS);
        this.copy(BlockTags.STAIRS, ItemTags.STAIRS);
        this.copy(BlockTags.WALLS, ItemTags.WALLS);
        this.copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);
        this.copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
        this.copy(BlockTags.DOORS, ItemTags.DOORS);
        this.copy(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);
        this.copy(BlockTags.TRAPDOORS, ItemTags.TRAPDOORS);
        this.copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
        this.copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);
        this.copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
        this.copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);
    }

}
