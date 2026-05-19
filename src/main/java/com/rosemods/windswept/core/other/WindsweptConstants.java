package com.rosemods.windswept.core.other;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public final class WindsweptConstants {
    public static final ResourceLocation MOSSY_COBBLESTONE_BRICK_WALL = ResourceLocation.tryBuild("caverns_and_chasms", "mossy_cobblestone_brick_wall");
    public static final ResourceLocation MOSSY_COBBLESTONE_TILE_WALL = ResourceLocation.tryBuild("caverns_and_chasms", "mossy_cobblestone_tile_wall");
    public static final ResourceLocation SWEET_BERRY_PIPS = ResourceLocation.tryBuild("berry_good", "sweet_berry_pips");
    public static final ResourceLocation HONEY = ResourceLocation.tryBuild("create", "honey");
    public static final ResourceLocation CHOCOLATE = ResourceLocation.tryBuild("create", "chocolate");

    public static Item getItem(String modid, String path) {
        return BuiltInRegistries.ITEM.get(ResourceLocation.tryBuild(modid, path));
    }

    public static Block getBlock(String modid, String path) {
        return BuiltInRegistries.BLOCK.get(ResourceLocation.tryBuild(modid, path));
    }

}
