package com.rosemods.windswept.core.registry;

import com.mojang.datafixers.util.Pair;
import com.rosemods.windswept.common.item.*;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.other.WindsweptConstants;
import com.rosemods.windswept.core.other.WindsweptFoods;
import com.rosemods.windswept.core.other.tags.WindsweptBannerPatternTags;
import com.rosemods.windswept.core.registry.datapack.WindsweptJukeboxSongs;
import com.rosemods.windswept.integration.boatload.WindsweptBoatTypes;
import com.teamabnormals.blueprint.common.item.BlueprintBoatItem;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.*;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;

public final class WindsweptItems {
    public static final ItemSubRegistryHelper ITEMS = Windswept.REGISTRY_HELPER.getItemSubHelper();

    // Misc //
    public static final DeferredItem<Item> HOLLY_BERRIES = ITEMS.createItem("holly_berries", () -> new Item(new Item.Properties().food(WindsweptFoods.HOLLY_BERRIES)));
    public static final DeferredItem<Item> HOLLY_BERRIES_ON_A_STICK = ITEMS.createItem("holly_berries_on_a_stick", () -> new HollyBerriesOnAStickItem(new Item.Properties().durability(25)));
    public static final DeferredItem<Item> ELDER_FEATHER = ITEMS.createItem("elder_feather", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FROST_ARROW = ITEMS.createItem("frost_arrow", () -> new FrostArrowItem(new Item.Properties()));
    public static final DeferredItem<Item> FROZEN_BRANCH = ITEMS.createItem("frozen_branch", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FROZEN_FLESH = ITEMS.createItem("frozen_flesh", () -> new Item(new Item.Properties().food(WindsweptFoods.FROZEN_FLESH)));

    // Armour //
    public static final DeferredItem<Item> LAVENDER_CROWN = ITEMS.createItem("lavender_crown", () -> new LavenderCrownItem(WindsweptArmorMaterials.LAVENDER_CROWN, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(5))));
    public static final DeferredItem<Item> ANTLER_HELMET = ITEMS.createItem("antler_helmet", () -> new AntlerHelmetItem(WindsweptArmorMaterials.ANTLER_HELMET, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(5))));
    public static final DeferredItem<Item> FEATHER_CLOAK = ITEMS.createItem("feather_cloak", () -> new FeatherCloakItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> SNOW_BOOTS = ITEMS.createItem("snow_boots", () -> new SnowBootsItem(WindsweptArmorMaterials.SNOW_BOOTS, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(5))));
    
    // Wooden Buckets //
    public static final DeferredItem<Item> WOODEN_BUCKET = ITEMS.createItem("wooden_bucket", () -> new WoodenBucketItem(() -> Fluids.EMPTY, new Item.Properties().durability(24)));
    public static final DeferredItem<Item> WOODEN_WATER_BUCKET = ITEMS.createItem("wooden_water_bucket", () -> new WoodenBucketItem(() -> Fluids.WATER, new Item.Properties().durability(24).craftRemainder(WOODEN_BUCKET.get())));
    public static final DeferredItem<Item> WOODEN_MILK_BUCKET = ITEMS.createItem("wooden_milk_bucket", () -> new WoodenMilkBucketItem(new Item.Properties().durability(24).craftRemainder(WOODEN_BUCKET.get())));
    public static final DeferredItem<Item> WOODEN_POWDER_SNOW_BUCKET = ITEMS.createItem("wooden_powder_snow_bucket", () -> new WoodenPowderSnowBucketItem(new Item.Properties().durability(24).craftRemainder(WOODEN_BUCKET.get())));
    public static final DeferredItem<Item> WOODEN_HONEY_BUCKET = ITEMS.createItem("wooden_honey_bucket", () -> new WoodenBucketItem(() -> BuiltInRegistries.FLUID.get(WindsweptConstants.HONEY), new Item.Properties().durability(24).craftRemainder(WOODEN_BUCKET.get())));
    public static final DeferredItem<Item> WOODEN_CHOCOLATE_BUCKET = ITEMS.createItem("wooden_chocolate_bucket", () -> new WoodenBucketItem(() -> BuiltInRegistries.FLUID.get(WindsweptConstants.CHOCOLATE), new Item.Properties().durability(24).craftRemainder(WOODEN_BUCKET.get())));

    // Food //
    public static final DeferredItem<Item> WILD_BERRIES = ITEMS.createItem("wild_berries", ItemSubRegistryHelper.areModsLoaded("berry_good") ? () -> new Item(new Item.Properties().food(WindsweptFoods.WILD_BERRIES)) : () -> new ItemNameBlockItem(WindsweptBlocks.WILD_BERRY_BUSH.get(), new Item.Properties().food(WindsweptFoods.WILD_BERRIES)));
    public static final DeferredItem<Item> WILD_BERRY_PIPS = ITEMS.createItem("wild_berry_pips", ItemSubRegistryHelper.areModsLoaded("berry_good") ? () -> new ItemNameBlockItem(WindsweptBlocks.WILD_BERRY_BUSH.get(), new Item.Properties()) : () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CANDY_CANE = ITEMS.createItem("candy_cane", () -> new Item(new Item.Properties().food(WindsweptFoods.CANDY_CANE)));

    public static final DeferredItem<Item> CHESTNUTS = ITEMS.createItem("chestnuts", () -> new Item(new Item.Properties().food(WindsweptFoods.CHESTNUTS)));
    public static final DeferredItem<Item> ROASTED_CHESTNUTS = ITEMS.createItem("roasted_chestnuts", () -> new Item(new Item.Properties().food(WindsweptFoods.ROASTED_CHESTNUTS)));
    public static final DeferredItem<Item> CHESTNUT_SOUP = ITEMS.createItem("chestnut_soup", () -> new Item(new Item.Properties().food(WindsweptFoods.CHESTNUT_SOUP).stacksTo(1)));

    public static final DeferredItem<Item> GINGER_ROOT = ITEMS.createItem("ginger_root", () -> new ItemNameBlockItem(WindsweptBlocks.GINGER.get(), new Item.Properties().food(WindsweptFoods.GINGER_ROOT)));
    public static final DeferredItem<Item> GINGERBREAD_COOKIE = ITEMS.createItem("gingerbread_cookie", () -> new Item(new Item.Properties().food(WindsweptFoods.GINGERBREAD_COOKIE)));
    public static final DeferredItem<Item> GINGER_TEA = ITEMS.createItem("ginger_tea", () -> new DrinkableBottleItem(WindsweptFoods.GINGER_TEA));

    public static final DeferredItem<Item> SPICY_SNOW_CONE = ITEMS.createItem("spicy_snow_cone", () -> new FoodRemainderItem(() -> WindsweptBlocks.PINECONE.get(), new Item.Properties().food(WindsweptFoods.SPICY_SNOW_CONE)));
    public static final DeferredItem<Item> SWEET_SNOW_CONE = ITEMS.createItem("sweet_snow_cone", () -> new FoodRemainderItem(() -> WindsweptBlocks.PINECONE.get(), new Item.Properties().food(WindsweptFoods.SWEET_SNOW_CONE)));
    public static final DeferredItem<Item> MINTY_SNOW_CONE = ITEMS.createItem("minty_snow_cone", () -> new FoodRemainderItem(() -> WindsweptBlocks.PINECONE.get(), new Item.Properties().food(WindsweptFoods.MINTY_SNOW_CONE)));

    public static final DeferredItem<Item> PINECONE_JAM_BOTTLE = ITEMS.createItem("pinecone_jam_bottle", () -> new DrinkableBottleItem(() -> SoundEvents.HONEY_DRINK, WindsweptFoods.PINECONE_JAM));

    public static final DeferredItem<Item> LAVENDER_TEA = ITEMS.createItem("lavender_tea", () -> new DrinkableBottleItem(WindsweptFoods.LAVENDER_TEA));

    public static final DeferredItem<Item> GOAT = ITEMS.createItem("goat", () -> new Item(new Item.Properties().food(WindsweptFoods.GOAT)));
    public static final DeferredItem<Item> COOKED_GOAT = ITEMS.createItem("cooked_goat", () -> new Item(new Item.Properties().food(WindsweptFoods.COOKED_GOAT)));
    public static final DeferredItem<Item> GOAT_STEW = ITEMS.createItem("goat_stew", () -> new Item(new Item.Properties().food(WindsweptFoods.GOAT_STEW).stacksTo(1)));
    public static final DeferredItem<Item> MUTTON_PIE = ITEMS.createItem("mutton_pie", () -> new Item(new Item.Properties().food(WindsweptFoods.MUTTON_PIE)));

    // Pottery Sherds //
    public static final DeferredItem<Item> HOOT_POTTERY_SHERD = ITEMS.createItem("hoot_pottery_sherd", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PLUMAGE_POTTERY_SHERD = ITEMS.createItem("plumage_pottery_sherd", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> OFFSHOOT_POTTERY_SHERD = ITEMS.createItem("offshoot_pottery_sherd", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FLAKE_POTTERY_SHERD = ITEMS.createItem("flake_pottery_sherd", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DRUPES_POTTERY_SHERD = ITEMS.createItem("drupes_pottery_sherd", () -> new Item(new Item.Properties()));

    // Banner Patterns //
    public static final DeferredItem<Item> SNOW_GOLEM_BANNER_PATTERN = ITEMS.createItem("snow_golem_banner_pattern", () -> new BannerPatternItem(WindsweptBannerPatternTags.SNOW_GOLEM, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> SNOW_CHARGE_BANNER_PATTERN = ITEMS.createItem("snow_charge_banner_pattern", () -> new BannerPatternItem(WindsweptBannerPatternTags.SNOW_CHARGE, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> ROSE_FLOWER_BANNER_PATTERN = ITEMS.createItem("rose_flower_banner_pattern", () -> new BannerPatternItem(WindsweptBannerPatternTags.ROSE_FLOWER, new Item.Properties().stacksTo(1)));

    // Music Discs //
    public static final DeferredItem<Item> MUSIC_DISC_RAIN = ITEMS.createItem("music_disc_rain", () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE).jukeboxPlayable(WindsweptJukeboxSongs.RAIN)));
    public static final DeferredItem<Item> MUSIC_DISC_SNOW = ITEMS.createItem("music_disc_snow", () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE).jukeboxPlayable(WindsweptJukeboxSongs.SNOW)));
    public static final DeferredItem<Item> MUSIC_DISC_BUMBLEBEE = ITEMS.createItem("music_disc_bumblebee", () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE).jukeboxPlayable(WindsweptJukeboxSongs.BUMBLEBEE)));

    // Spawn Eggs //
    public static final DeferredItem<DeferredSpawnEggItem> CHILLED_SPAWN_EGG = ITEMS.createSpawnEggItem("chilled", WindsweptEntityTypes.CHILLED::get, 0x9e9cbe, 0x98654a);
    public static final DeferredItem<DeferredSpawnEggItem> FROSTBITER_SPAWN_EGG = ITEMS.createSpawnEggItem("frostbiter", WindsweptEntityTypes.FROSTBITER::get, 0xe2e2e2, 0x8fb5ff);

    // Boats //
    public static final Pair<DeferredItem<BlueprintBoatItem>, DeferredItem<BlueprintBoatItem>> HOLLY_BOAT = ITEMS.createBoatAndChestBoatItem("holly", WindsweptBlocks.HOLLY_PLANKS);
    public static final DeferredItem<Item> HOLLY_FURNACE_BOAT = ITEMS.createItem("holly_furnace_boat", ItemSubRegistryHelper.areModsLoaded("boatload") ? WindsweptBoatTypes.HOLLY_FURNACE_BOAT : () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LARGE_HOLLY_BOAT = ITEMS.createItem("large_holly_boat", ItemSubRegistryHelper.areModsLoaded("boatload") ? WindsweptBoatTypes.LARGE_HOLLY_BOAT : () -> new Item(new Item.Properties()));
    public static final Pair<DeferredItem<BlueprintBoatItem>, DeferredItem<BlueprintBoatItem>> CHESTNUT_BOAT = ITEMS.createBoatAndChestBoatItem("chestnut", WindsweptBlocks.CHESTNUT_PLANKS);
    public static final DeferredItem<Item> CHESTNUT_FURNACE_BOAT = ITEMS.createItem("chestnut_furnace_boat", ItemSubRegistryHelper.areModsLoaded("boatload") ? WindsweptBoatTypes.CHESTNUT_FURNACE_BOAT : () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LARGE_CHESTNUT_BOAT = ITEMS.createItem("large_chestnut_boat", ItemSubRegistryHelper.areModsLoaded("boatload") ? WindsweptBoatTypes.LARGE_CHESTNUT_BOAT : () -> new Item(new Item.Properties()));
    public static final Pair<DeferredItem<BlueprintBoatItem>, DeferredItem<BlueprintBoatItem>> PINE_BOAT = ITEMS.createBoatAndChestBoatItem("pine", WindsweptBlocks.PINE_PLANKS);
    public static final DeferredItem<Item> PINE_FURNACE_BOAT = ITEMS.createItem("pine_furnace_boat", ItemSubRegistryHelper.areModsLoaded("boatload") ? WindsweptBoatTypes.PINE_FURNACE_BOAT : () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LARGE_PINE_BOAT = ITEMS.createItem("large_pine_boat", ItemSubRegistryHelper.areModsLoaded("boatload") ? WindsweptBoatTypes.LARGE_PINE_BOAT : () -> new Item(new Item.Properties()));
}
