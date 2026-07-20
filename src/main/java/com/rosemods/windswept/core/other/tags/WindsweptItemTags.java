package com.rosemods.windswept.core.other.tags;

import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.core.util.TagUtil;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public final class WindsweptItemTags {
    public static final TagKey<Item> HOLLY_LOGS = TagUtil.itemTag(Windswept.MOD_ID, "holly_logs");
    public static final TagKey<Item> CHESTNUT_LOGS = TagUtil.itemTag(Windswept.MOD_ID, "chestnut_logs");
    public static final TagKey<Item> PINE_LOGS = TagUtil.itemTag(Windswept.MOD_ID, "pine_logs");
    public static final TagKey<Item> WILD_BERRY_SEEDS = TagUtil.itemTag("c", "seeds/wild_berry");
    public static final TagKey<Item> MILK = TagUtil.itemTag("c", "milk");
    public static final TagKey<Item> RAW_GOAT = TagUtil.itemTag("c", "raw_goat");
    public static final TagKey<Item> COOKED_GOAT = TagUtil.itemTag("c", "cooked_goat");
    public static final TagKey<Item> SILVER_INGOT = TagUtil.itemTag("c", "ingots/silver");
    public static final TagKey<Item> BERRIES = TagUtil.itemTag("c", "berries");
    public static final TagKey<Item> COOKED_MUTTON = TagUtil.itemTag("c", "cooked_mutton");
    public static final TagKey<Item> ROSES = TagUtil.itemTag(Windswept.MOD_ID, "roses");
    public static final TagKey<Item> STRIPPED_LOGS = TagUtil.itemTag("c", "stripped_logs");
    public static final TagKey<Item> STRIPPED_WOOD = TagUtil.itemTag("c", "stripped_wood");
    public static final TagKey<Item> HONEY_BUCKETS = TagUtil.itemTag("c", "buckets/honey");
    public static final TagKey<Item> CHOCOLATE_BUCKETS = TagUtil.itemTag("c", "buckets/chocolate");

    public static final TagKey<Item> KNIVES = TagUtil.itemTag("farmersdelight", "tools/knives");
    public static final TagKey<Item> GINGERBREADS = TagUtil.itemTag("snowyspirit", "gingerbreads");
    public static final TagKey<Item> QUARK_LADDERS = TagUtil.itemTag("quark", "ladders");
    public static final TagKey<Item> HANGABLE_ITEMS = TagUtil.itemTag("connectiblechains", "hangable_items");
}
