package com.rosemods.windswept.core.other.tags;

import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.core.util.TagUtil;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public final class WindsweptItemTags {
    public static final TagKey<Item> HOLLY_LOGS = TagUtil.itemTag(Windswept.MOD_ID, "holly_logs");
    public static final TagKey<Item> CHESTNUT_LOGS = TagUtil.itemTag(Windswept.MOD_ID, "chestnut_logs");
    public static final TagKey<Item> PINE_LOGS = TagUtil.itemTag(Windswept.MOD_ID, "pine_logs");
    public static final TagKey<Item> SILVER_INGOT = TagUtil.itemTag("c", "ingots/silver");
    public static final TagKey<Item> COOKED_MUTTON = TagUtil.itemTag("c", "cooked_mutton");
    public static final TagKey<Item> ROSES = TagUtil.itemTag(Windswept.MOD_ID, "roses");
    public static final TagKey<Item> KNIVES = TagUtil.itemTag("farmersdelight", "tools/knives");
}
