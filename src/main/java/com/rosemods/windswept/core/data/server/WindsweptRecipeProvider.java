package com.rosemods.windswept.core.data.server;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.other.tags.WindsweptItemTags;
import com.teamabnormals.blueprint.core.data.server.BlueprintRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.*;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.List;

import static com.rosemods.windswept.core.registry.WindsweptBlocks.*;
import static com.rosemods.windswept.core.registry.WindsweptItems.*;

public class WindsweptRecipeProvider extends BlueprintRecipeProvider {

    public WindsweptRecipeProvider(GatherDataEvent event) {
        super(Windswept.MOD_ID, event.getGenerator().getPackOutput(), event.getLookupProvider());
    }

    private static void conditionalRecipe(RecipeBuilder recipe, ICondition condition, RecipeOutput output, ResourceLocation id) {
        recipe.save(output.withConditions(condition), id);
    }

    private static void blockset(ItemLike ingredient, Block block, Block chiseled, Block slab, Block stairs, Block wall, boolean stonecutter, RecipeOutput output) {
        if (ingredient != null)
            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, block, 4).define('#', ingredient).pattern("##").pattern("##").unlockedBy(getHasName(ingredient), has(ingredient)).save(output, getSaveLocation(block));

        if (chiseled != null) {
            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, chiseled).define('#', slab).pattern("#").pattern("#").unlockedBy(getHasName(block), has(block)).save(output, getSaveLocation(chiseled));

            if (stonecutter)
                stonecutting(block, chiseled, 1, output);
        }

        if (slab != null) {
            slab(block, slab, output);

            if (stonecutter)
                stonecutting(block, slab, 2, output);
        }

        if (stairs != null) {
            stairs(block, stairs, output);

            if (stonecutter)
                stonecutting(block, stairs, 1, output);
        }

        if (wall != null) {
            wall(block, wall, output);

            if (stonecutter)
                stonecutting(block, wall, 1, output);
        }

    }

    private static void leafPile(ItemLike leaves, ItemLike leafPile, RecipeOutput output) {
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, leafPile, 4).requires(leaves).unlockedBy(getHasName(leaves), has(leaves)), new ModLoadedCondition("woodworks"), output, getSaveLocation(leafPile));
        conditionalRecipe(ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, leaves).define('#', leafPile).pattern("##").pattern("##").unlockedBy(getHasName(leafPile), has(leafPile)), new ModLoadedCondition("woodworks"), output, getSaveLocation(getItemName(leaves) + "_from_leaf_pile"));
    }

    private static void compressedBlock(Block block, ItemLike item, ICondition condition, RecipeOutput output) {
        conditionalRecipe(ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, block).define('#', item).pattern("###").pattern("###").pattern("###").unlockedBy(getHasName(item), has(item)), condition, output, getSaveLocation(block));
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, item, 9).requires(block).unlockedBy(getHasName(block), has(block)), condition, output, getSaveLocation(getName(block) + "_revert"));
    }

    private static void compressedBlock(Block block, ItemLike item, RecipeOutput output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, block).define('#', item).pattern("###").pattern("###").pattern("###").unlockedBy(getHasName(item), has(item)).save(output, getSaveLocation(block));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, item, 9).requires(block).unlockedBy(getHasName(block), has(block)).save(output, getSaveLocation(getName(block) + "_revert"));
    }

    private static void woodSet(TagKey<Item> logs, Block planks, Block slab, Block stairs, Block log, Block wood, Block strippedLog, Block strippedWood, ItemLike boat, ItemLike chestBoat, Block button, Block door, Block trapdoor, Block fence, Block fenceGate, Block pressurePlate, Block sign, Block boards, Block beehive, Block ladder, Block bookshelf, Block chest, Block trappedChest, Item largeBoat, Item furnaceBoat, Block hangingSign, Block chiseledBookshelf, RecipeOutput output) {
        woodenBoat(output, boat, planks);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.TRANSPORTATION, chestBoat).group("chest_boat").requires(Tags.Items.CHESTS_WOODEN).requires(boat).unlockedBy(getHasName(boat), has(boat)).save(output, getSaveLocation(chestBoat));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, button).group("wooden_button").requires(planks).unlockedBy(getHasName(planks), has(planks)).save(output, getSaveLocation(button));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, door, 3).group("wooden_door").define('#', planks).pattern("##").pattern("##").pattern("##").unlockedBy(getHasName(planks), has(planks)).save(output, getSaveLocation(door));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, fence, 3).group("wooden_fence").define('#', planks).define('S', Items.STICK).pattern("#S#").pattern("#S#").unlockedBy(getHasName(planks), has(planks)).save(output, getSaveLocation(fence));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, fenceGate).group("wooden_fence_gate").define('#', planks).define('S', Items.STICK).pattern("S#S").pattern("S#S").unlockedBy(getHasName(planks), has(planks)).save(output, getSaveLocation(fenceGate));
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, pressurePlate).group("wooden_pressure_plate").define('#', planks).pattern("##").unlockedBy(getHasName(planks), has(planks)).save(output, getSaveLocation(pressurePlate));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, sign, 3).group("wooden_sign").define('#', planks).define('S', Items.STICK).pattern("###").pattern("###").pattern(" S ").unlockedBy(getHasName(planks), has(planks)).save(output, getSaveLocation(sign));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, trapdoor, 2).group("wooden_trapdoor").define('#', planks).pattern("###").pattern("###").unlockedBy(getHasName(planks), has(planks)).save(output, getSaveLocation(trapdoor));
        hangingSign(output, hangingSign, strippedLog);
        planksFromLogs(output, planks, logs, 4);
        woodFromLogs(output, wood, log);
        woodFromLogs(output, strippedWood, strippedLog);
        slab(planks, slab, "wooden_slab", output);
        stairs(planks, stairs, "wooden_stairs", output);

        ICondition woodworks = new ModLoadedCondition("woodworks");
        ICondition quarkOrWoodworks = new OrCondition(List.of(new ModLoadedCondition("quark"), woodworks));

        conditionalRecipe(ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, boards).group("wooden_boards").define('#', slab).pattern("#").pattern("#").unlockedBy(getHasName(slab), has(slab)), new ModLoadedCondition("woodworks"), output, getSaveLocation(boards));
        conditionalRecipe(ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, beehive).group("wooden_beehive").define('#', planks).define('H', Items.HONEYCOMB).pattern("###").pattern("HHH").pattern("###").unlockedBy(getHasName(planks), has(planks)), new ModLoadedCondition("woodworks"), output, getSaveLocation(beehive));
        conditionalRecipe(ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ladder, 4).group("wooden_ladders").define('#', planks).define('S', Items.STICK).pattern("S S").pattern("S#S").pattern("S S").unlockedBy(getHasName(planks), has(planks)), quarkOrWoodworks, output, getSaveLocation(ladder));
        conditionalRecipe(ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, bookshelf).group("wooden_bookshelves").define('#', planks).define('B', Items.BOOK).pattern("###").pattern("BBB").pattern("###").unlockedBy(getHasName(planks), has(planks)), quarkOrWoodworks, output, getSaveLocation(bookshelf));
        conditionalRecipe(ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, chest).group("wooden_chests").define('#', planks).pattern("###").pattern("# #").pattern("###").unlockedBy(getHasName(planks), has(planks)), quarkOrWoodworks, output, getSaveLocation(chest));
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, trappedChest).requires(chest).requires(Items.TRIPWIRE_HOOK).unlockedBy(getHasName(planks), has(planks)), quarkOrWoodworks, output, getSaveLocation(trappedChest));
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(RecipeCategory.TRANSPORTATION, furnaceBoat).requires(Items.FURNACE).requires(boat).unlockedBy(getHasName(boat), has(boat)), new ModLoadedCondition("boatload"), output, getSaveLocation(furnaceBoat));
        conditionalRecipe(ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, largeBoat).group("wooden_chests").define('#', planks).define('B', boat).pattern("#B#").pattern("###").unlockedBy(getHasName(boat), has(boat)), new ModLoadedCondition("boatload"), output, getSaveLocation(largeBoat));
        conditionalRecipe(ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, chiseledBookshelf).group("wooden_chiseled_bookshelves").define('#', planks).define('S', slab).pattern("###").pattern("SSS").pattern("###").unlockedBy(getHasName(planks), has(planks)), woodworks, output, getSaveLocation(chiseledBookshelf));
    }

    private static void flowerToDye(Block flower, Item dye, RecipeOutput output) {
        String dyeName = getItemName(dye);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, dye).group(dyeName).requires(flower).unlockedBy(getHasName(flower), has(flower)).save(output, getSaveLocation(dyeName + "_from_" + getItemName(flower)));
    }

    private static void flowerToDyeNoDyeDepot(Block flower, Item dye, RecipeOutput output) {
        String dyeName = getItemName(dye);
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, dye).group(dyeName).requires(flower).unlockedBy(getHasName(flower), has(flower)), new NotCondition(new ModLoadedCondition("dye_depot")), output, getSaveLocation(dyeName + "_from_" + getItemName(flower)));
    }

    private static void tallFlowerToDye(Block flower, Item dye, RecipeOutput output) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, dye, 2).group(getName(dye)).requires(flower).unlockedBy(getHasName(flower), has(flower)).save(output, getSaveLocation(getName(dye) + "_from_" + getName(flower)));
    }

    private static void tallFlowerToDyeNoDyeDepot(Block flower, Item dye, RecipeOutput output) {
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, dye, 2).group(getName(dye)).requires(flower).unlockedBy(getHasName(flower), has(flower)), new NotCondition(new ModLoadedCondition("dye_depot")), output, getSaveLocation(getName(dye) + "_from_" + getName(flower)));
    }

    private static void cooking(ItemLike ingredient, ItemLike result, RecipeOutput output) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), RecipeCategory.FOOD, result, .35f, 200).unlockedBy(getHasName(ingredient), has(ingredient)).save(output, getSaveLocation(result));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ingredient), RecipeCategory.FOOD, result, .35f, 600).unlockedBy(getHasName(ingredient), has(ingredient)).save(output, getSaveLocation(getName(result) + "_from_campfire_cooking"));
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient), RecipeCategory.FOOD, result, .35f, 100).unlockedBy(getHasName(ingredient), has(ingredient)).save(output, getSaveLocation(getName(result) + "_from_smoking"));
    }

    private static void stonecutting(ItemLike ingredient, ItemLike result, int amount, RecipeOutput output) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ingredient), RecipeCategory.BUILDING_BLOCKS, result, amount).unlockedBy(getHasName(ingredient), has(ingredient)).save(output, getSaveLocation(getName(ingredient) + "_from_" + getName(result) + "_stonecutting"));
    }

    private static void stonecutting(ItemLike ingredient, ItemLike result, int amount, ICondition condition, RecipeOutput output) {
        conditionalRecipe(SingleItemRecipeBuilder.stonecutting(Ingredient.of(ingredient), RecipeCategory.BUILDING_BLOCKS, result, amount).unlockedBy(getHasName(ingredient), has(ingredient)), condition, output, getSaveLocation(getName(ingredient) + "_from_" + getName(result) + "_stonecutting"));
    }

    private static void stairs(ItemLike ingredient, ItemLike stairs, RecipeOutput output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, stairs, 4).define('#', ingredient).pattern("#  ").pattern("## ").pattern("###").unlockedBy(getHasName(ingredient), has(ingredient)).save(output, getSaveLocation(getName(stairs)));
    }

    private static void stairs(ItemLike ingredient, ItemLike stairs, String group, RecipeOutput output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, stairs, 4).group(group).define('#', ingredient).pattern("#  ").pattern("## ").pattern("###").unlockedBy(getHasName(ingredient), has(ingredient)).save(output, getSaveLocation(getName(stairs)));
    }

    private static void wall(ItemLike ingredient, ItemLike wall, RecipeOutput output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, wall, 6).define('#', ingredient).pattern("###").pattern("###").unlockedBy(getHasName(ingredient), has(ingredient)).save(output, getSaveLocation(getName(wall)));
    }

    private static void slab(ItemLike ingredient, ItemLike slab, RecipeOutput output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, slab, 6).define('#', ingredient).pattern("###").unlockedBy(getHasName(ingredient), has(ingredient)).save(output, getSaveLocation(getName(slab)));
    }

    private static void slab(ItemLike ingredient, ItemLike slab, String group, RecipeOutput output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, slab, 6).group(group).define('#', ingredient).pattern("###").unlockedBy("has_" + getName(ingredient), has(ingredient)).save(output, getSaveLocation(getName(slab)));
    }

    private static String getName(ItemLike object) {
        return BuiltInRegistries.ITEM.getKey(object.asItem()).getPath();
    }

    // Util //

    private static ResourceLocation getSaveLocation(ItemLike item) {
        return BuiltInRegistries.ITEM.getKey(item.asItem());
    }

    private static ResourceLocation getSaveLocation(String name) {
        return Windswept.location(name);
    }

    @Override
    protected void buildRecipes(RecipeOutput output, HolderLookup.Provider holderLookup) {
        // foods
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, CHESTNUT_SOUP.get()).requires(Items.BOWL).requires(ROASTED_CHESTNUTS.get(), 2).requires(Items.CARROT).requires(Items.POTATO).unlockedBy(getHasName(ROASTED_CHESTNUTS.get()), has(ROASTED_CHESTNUTS.get())), new NotCondition(new ModLoadedCondition("windswept_delights")), output, getSaveLocation(CHESTNUT_SOUP.get()));
        conditionalRecipe(ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, Blocks.CAKE).define('A', WindsweptItemTags.MILK).define('B', Items.SUGAR).define('C', Tags.Items.CROPS_WHEAT).define('E', Tags.Items.EGGS).pattern("AAA").pattern("BEB").pattern("CCC").unlockedBy(getHasName(Items.EGG), has(Tags.Items.EGGS)), new NotCondition(new ModLoadedCondition("neapolitan")), output, getSaveLocation(Items.CAKE));
        cooking(CHESTNUTS.get(), ROASTED_CHESTNUTS.get(), output);
        cooking(GOAT.get(), COOKED_GOAT.get(), output);
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, GOAT_STEW.get()).requires(Items.BOWL).requires(COOKED_GOAT.get()).requires(Items.BAKED_POTATO).requires(Items.CARROT).requires(Items.BROWN_MUSHROOM).unlockedBy(getHasName(COOKED_GOAT.get()), has(COOKED_GOAT.get())), new NotCondition(new ModLoadedCondition("windswept_delights")), output, getSaveLocation(GOAT_STEW.get()));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, MUTTON_PIE.get()).requires(WindsweptItemTags.COOKED_MUTTON).requires(Items.WHEAT).requires(Items.SUGAR).requires(Tags.Items.EGGS).unlockedBy(getHasName(Items.COOKED_MUTTON), has(WindsweptItemTags.COOKED_MUTTON)).save(output, getSaveLocation(MUTTON_PIE.get()));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, CHRISTMAS_PUDDING.get()).requires(HOLLY_BERRIES.get()).requires(GINGER_ROOT.get()).requires(ROASTED_CHESTNUTS.get()).requires(Items.SUGAR).requires(Tags.Items.EGGS).requires(Tags.Items.CROPS_WHEAT).unlockedBy(getHasName(HOLLY_BERRIES.get()), has(HOLLY_BERRIES.get())).save(output, getSaveLocation(CHRISTMAS_PUDDING.get()));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, GINGERBREAD_COOKIE.get(), 8).requires(GINGER_ROOT.get()).requires(Items.WHEAT, 2).unlockedBy(getHasName(GINGER_ROOT.get()), has(GINGER_ROOT.get())).save(output, getSaveLocation(GINGERBREAD_COOKIE.get()));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, CANDY_CANE.get(), 3).requires(WILD_BERRIES.get()).requires(Items.SUGAR, 2).unlockedBy(getHasName(WILD_BERRIES.get()), has(WILD_BERRIES.get())).save(output, getSaveLocation(CANDY_CANE.get()));
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, GINGER_TEA.get()).requires(Items.GLASS_BOTTLE).requires(GINGER_ROOT.get(), 2).requires(Items.SUGAR).unlockedBy(getHasName(GINGER_ROOT.get()), has(GINGER_ROOT.get())), new NotCondition(new ModLoadedCondition("windswept_delights")), output, getSaveLocation(GINGER_TEA.get()));
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, LAVENDER_TEA.get()).requires(Items.GLASS_BOTTLE).requires(LAVENDER.get(), 4).unlockedBy(getHasName(LAVENDER.get()), has(LAVENDER.get())), new NotCondition(new ModLoadedCondition("windswept_delights")), output, getSaveLocation(LAVENDER_TEA.get()));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, PINECONE_JAM_BOTTLE.get()).requires(PINECONE.get(), 6).requires(Items.SUGAR, 2).requires(Items.GLASS_BOTTLE).unlockedBy(getHasName(PINECONE.get()), has(PINECONE.get())).save(output, getSaveLocation(PINECONE_JAM_BOTTLE.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PINECONE_JAM_BLOCK.get()).define('#', PINECONE_JAM_BOTTLE.get()).pattern("##").pattern("##").unlockedBy(getHasName(PINECONE_JAM_BOTTLE.get()), has(PINECONE_JAM_BOTTLE.get())).save(output, getSaveLocation(PINECONE_JAM_BLOCK.get()));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, PINECONE_JAM_BOTTLE.get(), 4).requires(PINECONE_JAM_BLOCK.get()).requires(Items.GLASS_BOTTLE, 4).unlockedBy(getHasName(PINECONE_JAM_BLOCK.get()), has(PINECONE_JAM_BLOCK.get())).save(output, getSaveLocation("pinecone_jam_bottle_revert"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, SPICY_SNOW_CONE.get()).requires(PINECONE.get()).requires(GINGER_ROOT.get()).requires(ROASTED_CHESTNUTS.get()).requires(Items.SNOWBALL).requires(Items.SUGAR).unlockedBy(getHasName(GINGER_ROOT.get()), has(GINGER_ROOT.get())).save(output, getSaveLocation(SPICY_SNOW_CONE.get()));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, SWEET_SNOW_CONE.get()).requires(PINECONE.get()).requires(WILD_BERRIES.get()).requires(Items.SWEET_BERRIES).requires(Items.SNOWBALL).requires(Items.SUGAR).unlockedBy(getHasName(GINGER_ROOT.get()), has(GINGER_ROOT.get())).save(output, getSaveLocation(SWEET_SNOW_CONE.get()));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, MINTY_SNOW_CONE.get()).requires(PINECONE.get()).requires(LAVENDER.get()).requires(Items.COCOA_BEANS).requires(Items.SNOWBALL).requires(Items.SUGAR).unlockedBy(getHasName(GINGER_ROOT.get()), has(GINGER_ROOT.get())).save(output, getSaveLocation(MINTY_SNOW_CONE.get()));

        // dyes
        flowerToDyeNoDyeDepot(RED_ROSE.get(), Items.RED_DYE, output);
        flowerToDye(BLUE_ROSE.get(), Items.BLUE_DYE, output);
        flowerToDye(WHITE_ROSE.get(), Items.WHITE_DYE, output);
        flowerToDye(YELLOW_ROSE.get(), Items.YELLOW_DYE, output);
        flowerToDye(FOXGLOVE.get(), Items.PINK_DYE, output);
        flowerToDye(BLUEBELLS.get(), Items.BLUE_DYE, output);
        flowerToDye(SNOWDROP.get(), Items.LIGHT_GRAY_DYE, output);
        flowerToDye(MOSS_CAMPION.get(), Items.MAGENTA_DYE, output);
        flowerToDye(WILD_GINGER.get(), Items.RED_DYE, output);
        flowerToDye(NIGHTSHADE.get(), Items.LIGHT_BLUE_DYE, output);
        flowerToDye(YELLOW_PETALS.get(), Items.YELLOW_DYE, output);
        flowerToDye(MIMOSA.get(), Items.YELLOW_DYE, output);
        tallFlowerToDyeNoDyeDepot(RED_ROSE_BUSH.get(), Items.RED_DYE, output);
        tallFlowerToDye(BLUE_ROSE_BUSH.get(), Items.BLUE_DYE, output);
        tallFlowerToDye(WHITE_ROSE_BUSH.get(), Items.WHITE_DYE, output);
        tallFlowerToDye(YELLOW_ROSE_BUSH.get(), Items.YELLOW_DYE, output);
        tallFlowerToDye(LUPINE.get(), Items.PURPLE_DYE, output);
        tallFlowerToDyeNoDyeDepot(LIONS_TAIL.get(), Items.ORANGE_DYE, output);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(PINECONE.get()), RecipeCategory.MISC, Items.BROWN_DYE, .35f, 200).unlockedBy(getHasName(PINECONE.get()), has(PINECONE.get())).save(output, getSaveLocation("brown_dye_from_pinecone_smelting"));

        // other items
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SNOW_CHARGE_BANNER_PATTERN.get()).requires(Items.PAPER).requires(Tags.Items.BUCKETS_POWDER_SNOW).unlockedBy(getHasName(Items.POWDER_SNOW_BUCKET), has(Tags.Items.BUCKETS_POWDER_SNOW)).save(output, getSaveLocation(SNOW_CHARGE_BANNER_PATTERN.get()));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SNOW_GOLEM_BANNER_PATTERN.get()).requires(Items.PAPER).requires(Items.GOLDEN_CARROT).unlockedBy(getHasName(Items.GOLDEN_CARROT), has(Items.GOLDEN_CARROT)).save(output, getSaveLocation(SNOW_GOLEM_BANNER_PATTERN.get()));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ROSE_FLOWER_BANNER_PATTERN.get()).requires(Items.PAPER).requires(WindsweptItemTags.ROSES).unlockedBy("has_roses", has(WindsweptItemTags.ROSES)).save(output, getSaveLocation(ROSE_FLOWER_BANNER_PATTERN.get()));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(FROZEN_FLESH.get()), RecipeCategory.MISC, Items.LEATHER, .35f, 400).unlockedBy(getHasName(FROZEN_FLESH.get()), has(FROZEN_FLESH.get())).save(output, getSaveLocation("leather_from_frozen_flesh"));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, WOODEN_BUCKET.get()).define('#', ItemTags.LOGS).pattern("# #").pattern(" # ").unlockedBy("has_log", has(ItemTags.LOGS)).save(output, getSaveLocation(WOODEN_BUCKET.get()));
        conditionalRecipe(ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SNOW_BOOTS.get()).define('#', Items.IRON_INGOT).define('L', Items.LEATHER).pattern("L L").pattern("L L").pattern("# #").unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER)), new TagEmptyCondition(WindsweptItemTags.SILVER_INGOT.location()), output, getSaveLocation(SNOW_BOOTS.get()));
        conditionalRecipe(ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SNOW_BOOTS.get()).define('#', WindsweptItemTags.SILVER_INGOT).define('L', Items.LEATHER).pattern("L L").pattern("L L").pattern("# #").unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER)), new NotCondition(new TagEmptyCondition(WindsweptItemTags.SILVER_INGOT.location())), output, getSaveLocation("snow_boots_from_silver"));
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, WILD_BERRY_PIPS.get()).requires(WILD_BERRIES.get()).unlockedBy(getHasName(WILD_BERRIES.get()), has(WILD_BERRIES.get())), new ModLoadedCondition("berry_good"), output, getSaveLocation(WILD_BERRY_PIPS.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, FROST_ARROW.get(), 6).define('#', FROZEN_BRANCH.get()).define('I', ICICLES.get()).define('S', Items.STICK).pattern("#").pattern("S").pattern("I").unlockedBy(getHasName(FROZEN_BRANCH.get()), has(FROZEN_BRANCH.get())).save(output, getSaveLocation(FROST_ARROW.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, HOLLY_BERRIES_ON_A_STICK.get()).define('#', Items.FISHING_ROD).define('H', HOLLY_BERRIES.get()).pattern("# ").pattern(" H").unlockedBy(getHasName(HOLLY_BERRIES.get()), has(HOLLY_BERRIES.get())).save(output, getSaveLocation(HOLLY_BERRIES_ON_A_STICK.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, LAVENDER_CROWN.get()).define('#', LAVENDER.get()).define('F', FROZEN_BRANCH.get()).pattern("###").pattern("#F#").unlockedBy(getHasName(LAVENDER.get()), has(LAVENDER.get())).save(output, getSaveLocation(LAVENDER_CROWN.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ANTLER_HELMET.get()).define('#', Items.BONE).define('F', FROZEN_BRANCH.get()).pattern("F#F").pattern("# #").unlockedBy(getHasName(FROZEN_BRANCH.get()), has(FROZEN_BRANCH.get())).save(output, getSaveLocation(ANTLER_HELMET.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, FEATHER_CLOAK.get()).define('#', ELDER_FEATHER.get()).define('L', Items.LEATHER).define('G', Items.GOLD_INGOT).pattern("L L").pattern("#G#").pattern("###").unlockedBy(getHasName(ELDER_FEATHER.get()), has(ELDER_FEATHER.get())).save(output, getSaveLocation(FEATHER_CLOAK.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, HOLLY_WREATH.get()).define('#', HOLLY_BERRIES.get()).define('B', FROZEN_BRANCH.get()).pattern(" # ").pattern("#B#").pattern(" # ").unlockedBy(getHasName(HOLLY_BERRIES.get()), has(HOLLY_BERRIES.get())).save(output, getSaveLocation(HOLLY_WREATH.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, PINECONE_WREATH.get()).define('#', PINECONE.get()).define('B', FROZEN_BRANCH.get()).pattern(" # ").pattern("#B#").pattern(" # ").unlockedBy(getHasName(PINECONE.get()), has(PINECONE.get())).save(output, getSaveLocation(PINECONE_WREATH.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, VINE_WREATH.get()).define('#', Items.VINE).define('B', FROZEN_BRANCH.get()).pattern(" # ").pattern("#B#").pattern(" # ").unlockedBy(getHasName(Items.VINE), has(Items.VINE)).save(output, getSaveLocation(VINE_WREATH.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, CHERRY_WREATH.get()).define('#', Items.CHERRY_LEAVES).define('B', FROZEN_BRANCH.get()).pattern(" # ").pattern("#B#").pattern(" # ").unlockedBy(getHasName(Items.CHERRY_LEAVES), has(Items.CHERRY_LEAVES)).save(output, getSaveLocation(CHERRY_WREATH.get()));

        // blocks
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GINGER_SOIL.get()).requires(Items.DIRT).requires(GINGER_ROOT.get()).unlockedBy(getHasName(GINGER_ROOT.get()), has(GINGER_ROOT.get())).save(output, getSaveLocation(GINGER_SOIL.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ICE_SHEET.get(), 12).define('#', Items.ICE).pattern("###").pattern("###").unlockedBy(getHasName(Items.ICE), has(Items.ICE)).save(output, getSaveLocation(ICE_SHEET.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, FROSTBITER_TROPHY.get()).define('#', FROZEN_BRANCH.get()).define('P', ItemTags.PLANKS).pattern("# #").pattern("#P#").unlockedBy(getHasName(FROZEN_BRANCH.get()), has(FROZEN_BRANCH.get())).save(output, getSaveLocation(FROSTBITER_TROPHY.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DRY_MOSS_CARPET.get(), 3).define('#', DRY_MOSS_BLOCK.get()).pattern("##").unlockedBy(getHasName(DRY_MOSS_BLOCK.get()), has(DRY_MOSS_BLOCK.get())).save(output, getSaveLocation(DRY_MOSS_CARPET.get()));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, DRY_MOSSY_COBBLESTONE.get()).requires(Items.COBBLESTONE).requires(DRY_MOSS_BLOCK.get()).unlockedBy(getHasName(DRY_MOSS_BLOCK.get()), has(DRY_MOSS_BLOCK.get())).save(output, getSaveLocation("dry_mossy_cobblestone_from_dry_moss"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, DRY_MOSSY_STONE_BRICKS.get()).requires(Items.STONE_BRICKS).requires(DRY_MOSS_BLOCK.get()).unlockedBy(getHasName(DRY_MOSS_BLOCK.get()), has(DRY_MOSS_BLOCK.get())).save(output, getSaveLocation("dry_mossy_stone_bricks_from_dry_moss"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ICY_POLISHED_SHALE_BRICKS.get(), 8).define('#', POLISHED_SHALE_BRICKS.get()).define('I', ICICLES.get()).pattern("###").pattern("#I#").pattern("###").unlockedBy(getHasName(POLISHED_SHALE_BRICKS.get()), has(POLISHED_SHALE_BRICKS.get())).save(output, getSaveLocation(ICY_POLISHED_SHALE_BRICKS.get()));
        woodFromLogs(output, WEATHERED_PINE_WOOD.get(), WEATHERED_PINE_LOG.get());
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, WILL_O_THE_WISP.get()).define('#', CARVED_PINECONE_BLOCK.get()).define('N', NIGHTSHADE.get()).pattern("#").pattern("N").unlockedBy(getHasName(CARVED_PINECONE_BLOCK.get()), has(CARVED_PINECONE_BLOCK.get())).save(output, getSaveLocation(WILL_O_THE_WISP.get()));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, SHALE.get()).requires(Items.COBBLESTONE).requires(Items.BLUE_ICE).unlockedBy(getHasName(SHALE.get()), has(SHALE.get())).save(output, getSaveLocation(SHALE.get()));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LUNALITE.get()), RecipeCategory.BUILDING_BLOCKS, SMOOTH_LUNALITE.get(), .1f, 200).unlockedBy(getHasName(LUNALITE.get()), has(LUNALITE.get())).save(output, getSaveLocation(SMOOTH_LUNALITE.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GINGERBREAD_BLOCK.get()).define('#', GINGERBREAD_COOKIE.get()).pattern("##").pattern("##").unlockedBy(getHasName(GINGERBREAD_COOKIE.get()), has(GINGERBREAD_COOKIE.get())).save(output, getSaveLocation(GINGERBREAD_BLOCK.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, LAVENDER_THATCH.get()).define('#', LAVENDER.get()).pattern("##").pattern("##").unlockedBy(getHasName(LAVENDER.get()), has(LAVENDER.get())).save(output, getSaveLocation(LAVENDER_THATCH.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, LUNALITE.get(), 8).define('#', Items.CALCITE).define('L', Items.LAPIS_LAZULI).pattern("###").pattern("#L#").pattern("###").unlockedBy(getHasName(Items.LAPIS_LAZULI), has(Items.LAPIS_LAZULI)).save(output, getSaveLocation(LUNALITE.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GINGERBREAD_DOOR.get(), 3).define('#', GINGERBREAD_COOKIE.get()).pattern("##").pattern("##").pattern("##").unlockedBy(getHasName(GINGERBREAD_COOKIE.get()), has(GINGERBREAD_COOKIE.get())).save(output, getSaveLocation(GINGERBREAD_DOOR.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GINGERBREAD_TRAPDOOR.get(), 2).define('#', GINGERBREAD_COOKIE.get()).pattern("###").pattern("###").unlockedBy(getHasName(GINGERBREAD_COOKIE.get()), has(GINGERBREAD_COOKIE.get())).save(output, getSaveLocation(GINGERBREAD_TRAPDOOR.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ICE_CHAIN.get(), 2).define('#', ICICLES.get()).pattern("#").pattern("#").pattern("#").unlockedBy(getHasName(ICICLES.get()), has(ICICLES.get())).save(output, getSaveLocation(ICE_CHAIN.get()));

        conditionalRecipe(ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, GLAZED_GINGERBREAD_BLOCK.get()).requires(GINGERBREAD_BLOCK.get()).requires(Items.SUGAR).unlockedBy(getHasName(GINGERBREAD_BLOCK.get()), has(GINGERBREAD_BLOCK.get())), new NotCondition(new ModLoadedCondition("create")), output, getSaveLocation("glazed_gingerbread_block_from_sugar"));
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, GLAZED_GINGERBREAD_BRICKS.get()).requires(GINGERBREAD_BRICKS.get()).requires(Items.SUGAR).unlockedBy(getHasName(GINGERBREAD_BRICKS.get()), has(GINGERBREAD_BRICKS.get())), new NotCondition(new ModLoadedCondition("create")), output, getSaveLocation("glazed_gingerbread_bricks_from_sugar"));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ELDER_WING.get(), 8).define('#', Items.STICK).define('F', ELDER_FEATHER.get()).pattern("#FF").unlockedBy(getHasName(ELDER_FEATHER.get()), has(ELDER_FEATHER.get())).save(output, getSaveLocation(ELDER_WING.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ELDER_ORNAMENT.get(), 12).define('#', Items.STICK).define('F', ELDER_FEATHER.get()).pattern("##").pattern("FF").unlockedBy(getHasName(ELDER_FEATHER.get()), has(ELDER_FEATHER.get())).save(output, getSaveLocation(ELDER_ORNAMENT.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DREAM_CATCHER.get(), 1).define('#', Items.STICK).define('S', Items.STRING).define('F', ELDER_FEATHER.get()).pattern(" # ").pattern("FSF").pattern(" F ").unlockedBy(getHasName(ELDER_FEATHER.get()), has(ELDER_FEATHER.get())).save(output, getSaveLocation(DREAM_CATCHER.get()));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, FAIRY_LIGHT.get()).requires(PINECONE.get()).requires(Items.TORCH).unlockedBy(getHasName(PINECONE.get()), has(PINECONE.get())).save(output, getSaveLocation(FAIRY_LIGHT.get()));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, SOUL_FAIRY_LIGHT.get()).requires(PINECONE.get()).requires(Items.SOUL_TORCH).unlockedBy(getHasName(PINECONE.get()), has(PINECONE.get())).save(output, getSaveLocation(SOUL_FAIRY_LIGHT.get()));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, NIGHT_FAIRY_LIGHT.get()).requires(PINECONE.get()).requires(NIGHTSHADE.get()).unlockedBy(getHasName(PINECONE.get()), has(PINECONE.get())).save(output, getSaveLocation(NIGHT_FAIRY_LIGHT.get()));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, REDSTONE_FAIRY_LIGHT.get()).requires(PINECONE.get()).requires(Items.REDSTONE_TORCH).unlockedBy(getHasName(PINECONE.get()), has(PINECONE.get())).save(output, getSaveLocation(REDSTONE_FAIRY_LIGHT.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ICICLE_BLOCK.get(), 2).define('#', ICICLES.get()).pattern("##").pattern("##").unlockedBy(getHasName(ICICLES.get()), has(ICICLES.get())).save(output, getSaveLocation(ICICLE_BLOCK.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, CHISELED_ICICLE_BLOCK.get(), 2).define('#', ICICLE_BLOCK.get()).pattern("#").pattern("#").unlockedBy(getHasName(ICICLE_BLOCK.get()), has(ICICLE_BLOCK.get())).save(output, getSaveLocation(CHISELED_ICICLE_BLOCK.get()));
        stonecutting(ICICLE_BLOCK.get(), CHISELED_ICICLE_BLOCK.get(), 1, output);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ICICLE_DOOR.get(), 3).define('#', ICICLES.get()).pattern("##").pattern("##").pattern("##").unlockedBy(getHasName(ICICLES.get()), has(ICICLES.get())).save(output, getSaveLocation(ICICLE_DOOR.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ICICLE_TRAPDOOR.get(), 2).define('#', ICICLES.get()).pattern("###").pattern("###").unlockedBy(getHasName(ICICLES.get()), has(ICICLES.get())).save(output, getSaveLocation(ICICLE_TRAPDOOR.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ICICLE_BARS.get(), 16).define('#', ICICLE_BLOCK.get()).pattern("###").pattern("###").unlockedBy(getHasName(ICICLE_BLOCK.get()), has(ICICLE_BLOCK.get())).save(output, getSaveLocation(ICICLE_BARS.get()));
        stonecutting(ICICLE_BLOCK.get(), ICICLE_BARS.get(), 2, output);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ICE_LANTERN.get()).define('#', ICICLES.get()).define('N', NIGHTSHADE.get()).pattern("#").pattern("N").pattern("#").unlockedBy(getHasName(ICICLES.get()), has(ICICLES.get())).save(output, getSaveLocation(ICE_LANTERN.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, PINECONE_SHINGLES.get(), 2).define('#', PINECONE.get()).pattern("##").pattern("##").unlockedBy(getHasName(PINECONE.get()), has(PINECONE.get())).save(output, getSaveLocation(PINECONE_SHINGLES.get()));

        conditionalRecipe(ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DRY_MOSSY_COBBLESTONE_BRICKS.get(), 4).define('#', DRY_MOSSY_COBBLESTONE.get()).pattern("##").pattern("##").unlockedBy(getHasName(DRY_MOSSY_COBBLESTONE.get()), has(DRY_MOSSY_COBBLESTONE.get())), new ModLoadedCondition("caverns_and_chasms"), output, getSaveLocation(DRY_MOSSY_COBBLESTONE_BRICKS.get()));
        conditionalRecipe(ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DRY_MOSSY_COBBLESTONE_TILES.get(), 4).define('#', DRY_MOSSY_COBBLESTONE_BRICKS.get()).pattern("##").pattern("##").unlockedBy(getHasName(DRY_MOSSY_COBBLESTONE_BRICKS.get()), has(DRY_MOSSY_COBBLESTONE_BRICKS.get())), new ModLoadedCondition("caverns_and_chasms"), output, getSaveLocation(DRY_MOSSY_COBBLESTONE_TILES.get()));
        stonecutting(DRY_MOSSY_COBBLESTONE.get(), DRY_MOSSY_COBBLESTONE_BRICKS.get(), 1, new ModLoadedCondition("caverns_and_chasms"), output);
        stonecutting(DRY_MOSSY_COBBLESTONE.get(), DRY_MOSSY_COBBLESTONE_BRICK_SLAB.get(), 2, new ModLoadedCondition("caverns_and_chasms"), output);
        stonecutting(DRY_MOSSY_COBBLESTONE.get(), DRY_MOSSY_COBBLESTONE_BRICK_STAIRS.get(), 1, new ModLoadedCondition("caverns_and_chasms"), output);
        stonecutting(DRY_MOSSY_COBBLESTONE.get(), DRY_MOSSY_COBBLESTONE_BRICK_WALL.get(), 1, new ModLoadedCondition("caverns_and_chasms"), output);
        stonecutting(DRY_MOSSY_COBBLESTONE.get(), DRY_MOSSY_COBBLESTONE_TILES.get(), 1, new ModLoadedCondition("caverns_and_chasms"), output);
        stonecutting(DRY_MOSSY_COBBLESTONE.get(), DRY_MOSSY_COBBLESTONE_TILE_SLAB.get(), 2, new ModLoadedCondition("caverns_and_chasms"), output);
        stonecutting(DRY_MOSSY_COBBLESTONE.get(), DRY_MOSSY_COBBLESTONE_TILE_STAIRS.get(), 1, new ModLoadedCondition("caverns_and_chasms"), output);
        stonecutting(DRY_MOSSY_COBBLESTONE.get(), DRY_MOSSY_COBBLESTONE_TILE_WALL.get(), 1, new ModLoadedCondition("caverns_and_chasms"), output);
        stonecutting(DRY_MOSSY_COBBLESTONE_BRICKS.get(), DRY_MOSSY_COBBLESTONE_TILES.get(), 1, new ModLoadedCondition("caverns_and_chasms"), output);
        stonecutting(DRY_MOSSY_COBBLESTONE_BRICKS.get(), DRY_MOSSY_COBBLESTONE_TILE_SLAB.get(), 2, new ModLoadedCondition("caverns_and_chasms"), output);
        stonecutting(DRY_MOSSY_COBBLESTONE_BRICKS.get(), DRY_MOSSY_COBBLESTONE_TILE_STAIRS.get(), 1, new ModLoadedCondition("caverns_and_chasms"), output);
        stonecutting(DRY_MOSSY_COBBLESTONE_BRICKS.get(), DRY_MOSSY_COBBLESTONE_TILE_WALL.get(), 1, new ModLoadedCondition("caverns_and_chasms"), output);

        stairs(Blocks.SNOW_BLOCK, SNOW_STAIRS.get(), output);
        slab(Blocks.SNOW_BLOCK, SNOW_SLAB.get(), output);

        stonecutting(Blocks.PACKED_ICE, PACKED_ICE_BRICKS.get(), 1, output);
        stonecutting(Blocks.PACKED_ICE, CHISELED_PACKED_ICE_BRICKS.get(), 1, output);
        stonecutting(Blocks.PACKED_ICE, PACKED_ICE_BRICK_SLAB.get(), 2, output);
        stonecutting(Blocks.PACKED_ICE, PACKED_ICE_BRICK_STAIRS.get(), 1, output);
        stonecutting(Blocks.PACKED_ICE, PACKED_ICE_BRICK_WALL.get(), 1, output);
        stonecutting(Blocks.BLUE_ICE, BLUE_ICE_BRICKS.get(), 1, output);
        stonecutting(Blocks.BLUE_ICE, CHISELED_BLUE_ICE_BRICKS.get(), 1, output);
        stonecutting(Blocks.BLUE_ICE, BLUE_ICE_BRICK_SLAB.get(), 2, output);
        stonecutting(Blocks.BLUE_ICE, BLUE_ICE_BRICK_STAIRS.get(), 1, output);
        stonecutting(Blocks.BLUE_ICE, BLUE_ICE_BRICK_WALL.get(), 1, output);

        stonecutting(SHALE.get(), POLISHED_SHALE_BRICKS.get(), 1, output);
        stonecutting(SHALE.get(), CHISELED_POLISHED_SHALE_BRICKS.get(), 1, output);
        stonecutting(SHALE.get(), POLISHED_SHALE_BRICK_SLAB.get(), 2, output);
        stonecutting(SHALE.get(), POLISHED_SHALE_BRICK_STAIRS.get(), 1, output);
        stonecutting(SHALE.get(), POLISHED_SHALE_BRICK_WALL.get(), 1, output);
        stonecutting(SHALE.get(), POLISHED_SHALE.get(), 1, output);
        stonecutting(SHALE.get(), POLISHED_SHALE_SLAB.get(), 2, output);
        stonecutting(SHALE.get(), POLISHED_SHALE_STAIRS.get(), 1, output);
        stonecutting(SHALE.get(), POLISHED_SHALE_WALL.get(), 1, output);
        stonecutting(POLISHED_SHALE.get(), POLISHED_SHALE_BRICKS.get(), 1, output);
        stonecutting(POLISHED_SHALE.get(), CHISELED_POLISHED_SHALE_BRICKS.get(), 1, output);
        stonecutting(POLISHED_SHALE.get(), POLISHED_SHALE_BRICK_SLAB.get(), 2, output);
        stonecutting(POLISHED_SHALE.get(), POLISHED_SHALE_BRICK_STAIRS.get(), 1, output);
        stonecutting(POLISHED_SHALE.get(), POLISHED_SHALE_BRICK_WALL.get(), 1, output);

        stonecutting(LUNALITE.get(), CUT_LUNALITE_BRICKS.get(), 1, output);
        stonecutting(LUNALITE.get(), CHISELED_CUT_LUNALITE_BRICKS.get(), 1, output);
        stonecutting(LUNALITE.get(), CUT_LUNALITE_BRICK_SLAB.get(), 2, output);
        stonecutting(LUNALITE.get(), CUT_LUNALITE_BRICK_STAIRS.get(), 1, output);
        stonecutting(LUNALITE.get(), CUT_LUNALITE_BRICK_WALL.get(), 1, output);
        stonecutting(LUNALITE.get(), CUT_LUNALITE.get(), 1, output);
        stonecutting(LUNALITE.get(), CUT_LUNALITE_SLAB.get(), 2, output);
        stonecutting(LUNALITE.get(), CUT_LUNALITE_STAIRS.get(), 1, output);
        stonecutting(LUNALITE.get(), CUT_LUNALITE_WALL.get(), 1, output);
        stonecutting(CUT_LUNALITE.get(), CUT_LUNALITE_BRICKS.get(), 1, output);
        stonecutting(CUT_LUNALITE.get(), CHISELED_CUT_LUNALITE_BRICKS.get(), 1, output);
        stonecutting(CUT_LUNALITE.get(), CUT_LUNALITE_BRICK_SLAB.get(), 2, output);
        stonecutting(CUT_LUNALITE.get(), CUT_LUNALITE_BRICK_STAIRS.get(), 1, output);
        stonecutting(CUT_LUNALITE.get(), CUT_LUNALITE_BRICK_WALL.get(), 1, output);

        blockset(null, Blocks.PACKED_ICE, null, PACKED_ICE_SLAB.get(), PACKED_ICE_STAIRS.get(), null, true, output);
        blockset(null, Blocks.BLUE_ICE, null, BLUE_ICE_SLAB.get(), BLUE_ICE_STAIRS.get(), null, true, output);
        blockset(Blocks.PACKED_ICE, PACKED_ICE_BRICKS.get(), CHISELED_PACKED_ICE_BRICKS.get(), PACKED_ICE_BRICK_SLAB.get(), PACKED_ICE_BRICK_STAIRS.get(), PACKED_ICE_BRICK_WALL.get(), true, output);
        blockset(Blocks.BLUE_ICE, BLUE_ICE_BRICKS.get(), CHISELED_BLUE_ICE_BRICKS.get(), BLUE_ICE_BRICK_SLAB.get(), BLUE_ICE_BRICK_STAIRS.get(), BLUE_ICE_BRICK_WALL.get(), true, output);
        blockset(Blocks.SNOW_BLOCK, SNOW_BRICKS.get(), null, SNOW_BRICK_SLAB.get(), SNOW_BRICK_STAIRS.get(), SNOW_BRICK_WALL.get(), true, output);
        blockset(null, DRY_MOSSY_COBBLESTONE.get(), null, DRY_MOSSY_COBBLESTONE_SLAB.get(), DRY_MOSSY_COBBLESTONE_STAIRS.get(), DRY_MOSSY_COBBLESTONE_WALL.get(), true, output);
        blockset(null, DRY_MOSSY_STONE_BRICKS.get(), null, DRY_MOSSY_STONE_BRICK_SLAB.get(), DRY_MOSSY_STONE_BRICK_STAIRS.get(), DRY_MOSSY_STONE_BRICK_WALL.get(), true, output);
        blockset(null, SHALE.get(), null, SHALE_SLAB.get(), SHALE_STAIRS.get(), SHALE_WALL.get(), true, output);
        blockset(SHALE.get(), POLISHED_SHALE.get(), null, POLISHED_SHALE_SLAB.get(), POLISHED_SHALE_STAIRS.get(), POLISHED_SHALE_WALL.get(), true, output);
        blockset(POLISHED_SHALE.get(), POLISHED_SHALE_BRICKS.get(), CHISELED_POLISHED_SHALE_BRICKS.get(), POLISHED_SHALE_BRICK_SLAB.get(), POLISHED_SHALE_BRICK_STAIRS.get(), POLISHED_SHALE_BRICK_WALL.get(), true, output);
        blockset(null, PINECONE_SHINGLES.get(), null, PINECONE_SHINGLE_SLAB.get(), PINECONE_SHINGLE_STAIRS.get(), null, false, output);
        blockset(null, LUNALITE.get(), null, LUNALITE_SLAB.get(), LUNALITE_STAIRS.get(), LUNALITE_WALL.get(), true, output);
        blockset(LUNALITE.get(), CUT_LUNALITE.get(), null, CUT_LUNALITE_SLAB.get(), CUT_LUNALITE_STAIRS.get(), CUT_LUNALITE_WALL.get(), true, output);
        blockset(CUT_LUNALITE.get(), CUT_LUNALITE_BRICKS.get(), CHISELED_CUT_LUNALITE_BRICKS.get(), CUT_LUNALITE_BRICK_SLAB.get(), CUT_LUNALITE_BRICK_STAIRS.get(), CUT_LUNALITE_BRICK_WALL.get(), true, output);
        blockset(null, SMOOTH_LUNALITE.get(), null, SMOOTH_LUNALITE_SLAB.get(), SMOOTH_LUNALITE_STAIRS.get(), null, true, output);
        blockset(GINGERBREAD_BLOCK.get(), GINGERBREAD_BRICKS.get(), null, GINGERBREAD_BRICK_SLAB.get(), GINGERBREAD_BRICK_STAIRS.get(), GINGERBREAD_BRICK_WALL.get(), false, output);
        blockset(GLAZED_GINGERBREAD_BLOCK.get(), GLAZED_GINGERBREAD_BRICKS.get(), null, GLAZED_GINGERBREAD_BRICK_SLAB.get(), GLAZED_GINGERBREAD_BRICK_STAIRS.get(), GLAZED_GINGERBREAD_BRICK_WALL.get(), false, output);
        blockset(null, DRY_MOSSY_COBBLESTONE_BRICKS.get(), null, DRY_MOSSY_COBBLESTONE_BRICK_SLAB.get(), DRY_MOSSY_COBBLESTONE_BRICK_STAIRS.get(), DRY_MOSSY_COBBLESTONE_BRICK_WALL.get(), true, output);
        blockset(null, DRY_MOSSY_COBBLESTONE_TILES.get(), null, DRY_MOSSY_COBBLESTONE_TILE_SLAB.get(), DRY_MOSSY_COBBLESTONE_TILE_STAIRS.get(), DRY_MOSSY_COBBLESTONE_TILE_WALL.get(), true, output);
        blockset(null, LAVENDER_THATCH.get(), null, LAVENDER_THATCH_SLAB.get(), LAVENDER_THATCH_STAIRS.get(), null, false, output);

        blockset(null, WHITE_PINECONE_SHINGLES.get(), null, WHITE_PINECONE_SHINGLE_SLAB.get(), WHITE_PINECONE_SHINGLE_STAIRS.get(), null, false, output);
        blockset(null, LIGHT_GRAY_PINECONE_SHINGLES.get(), null, LIGHT_GRAY_PINECONE_SHINGLE_SLAB.get(), LIGHT_GRAY_PINECONE_SHINGLE_STAIRS.get(), null, false, output);
        blockset(null, GRAY_PINECONE_SHINGLES.get(), null, GRAY_PINECONE_SHINGLE_SLAB.get(), GRAY_PINECONE_SHINGLE_STAIRS.get(), null, false, output);
        blockset(null, BLACK_PINECONE_SHINGLES.get(), null, BLACK_PINECONE_SHINGLE_SLAB.get(), BLACK_PINECONE_SHINGLE_STAIRS.get(), null, false, output);
        blockset(null, BROWN_PINECONE_SHINGLES.get(), null, BROWN_PINECONE_SHINGLE_SLAB.get(), BROWN_PINECONE_SHINGLE_STAIRS.get(), null, false, output);
        blockset(null, RED_PINECONE_SHINGLES.get(), null, RED_PINECONE_SHINGLE_SLAB.get(), RED_PINECONE_SHINGLE_STAIRS.get(), null, false, output);
        blockset(null, ORANGE_PINECONE_SHINGLES.get(), null, ORANGE_PINECONE_SHINGLE_SLAB.get(), ORANGE_PINECONE_SHINGLE_STAIRS.get(), null, false, output);
        blockset(null, YELLOW_PINECONE_SHINGLES.get(), null, YELLOW_PINECONE_SHINGLE_SLAB.get(), YELLOW_PINECONE_SHINGLE_STAIRS.get(), null, false, output);
        blockset(null, LIME_PINECONE_SHINGLES.get(), null, LIME_PINECONE_SHINGLE_SLAB.get(), LIME_PINECONE_SHINGLE_STAIRS.get(), null, false, output);
        blockset(null, GREEN_PINECONE_SHINGLES.get(), null, GREEN_PINECONE_SHINGLE_SLAB.get(), GREEN_PINECONE_SHINGLE_STAIRS.get(), null, false, output);
        blockset(null, CYAN_PINECONE_SHINGLES.get(), null, CYAN_PINECONE_SHINGLE_SLAB.get(), CYAN_PINECONE_SHINGLE_STAIRS.get(), null, false, output);
        blockset(null, LIGHT_BLUE_PINECONE_SHINGLES.get(), null, LIGHT_BLUE_PINECONE_SHINGLE_SLAB.get(), LIGHT_BLUE_PINECONE_SHINGLE_STAIRS.get(), null, false, output);
        blockset(null, BLUE_PINECONE_SHINGLES.get(), null, BLUE_PINECONE_SHINGLE_SLAB.get(), BLUE_PINECONE_SHINGLE_STAIRS.get(), null, false, output);
        blockset(null, PURPLE_PINECONE_SHINGLES.get(), null, PURPLE_PINECONE_SHINGLE_SLAB.get(), PURPLE_PINECONE_SHINGLE_STAIRS.get(), null, false, output);
        blockset(null, MAGENTA_PINECONE_SHINGLES.get(), null, MAGENTA_PINECONE_SHINGLE_SLAB.get(), MAGENTA_PINECONE_SHINGLE_STAIRS.get(), null, false, output);
        blockset(null, PINK_PINECONE_SHINGLES.get(), null, PINK_PINECONE_SHINGLE_SLAB.get(), PINK_PINECONE_SHINGLE_STAIRS.get(), null, false, output);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, WHITE_PINECONE_SHINGLES.get(), 8).define('D', Tags.Items.DYES_WHITE).define('#', PINECONE_SHINGLES.get()).pattern("###").pattern("#D#").pattern("###").unlockedBy(getHasName(PINECONE_SHINGLES.get()), has(PINECONE_SHINGLES.get())).save(output, getSaveLocation(WHITE_PINECONE_SHINGLES.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, LIGHT_GRAY_PINECONE_SHINGLES.get(), 8).define('D', Tags.Items.DYES_LIGHT_GRAY).define('#', PINECONE_SHINGLES.get()).pattern("###").pattern("#D#").pattern("###").unlockedBy(getHasName(PINECONE_SHINGLES.get()), has(PINECONE_SHINGLES.get())).save(output, getSaveLocation(LIGHT_GRAY_PINECONE_SHINGLES.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GRAY_PINECONE_SHINGLES.get(), 8).define('D', Tags.Items.DYES_GRAY).define('#', PINECONE_SHINGLES.get()).pattern("###").pattern("#D#").pattern("###").unlockedBy(getHasName(PINECONE_SHINGLES.get()), has(PINECONE_SHINGLES.get())).save(output, getSaveLocation(GRAY_PINECONE_SHINGLES.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BLACK_PINECONE_SHINGLES.get(), 8).define('D', Tags.Items.DYES_BLACK).define('#', PINECONE_SHINGLES.get()).pattern("###").pattern("#D#").pattern("###").unlockedBy(getHasName(PINECONE_SHINGLES.get()), has(PINECONE_SHINGLES.get())).save(output, getSaveLocation(BLACK_PINECONE_SHINGLES.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BROWN_PINECONE_SHINGLES.get(), 8).define('D', Tags.Items.DYES_BROWN).define('#', PINECONE_SHINGLES.get()).pattern("###").pattern("#D#").pattern("###").unlockedBy(getHasName(PINECONE_SHINGLES.get()), has(PINECONE_SHINGLES.get())).save(output, getSaveLocation(BROWN_PINECONE_SHINGLES.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, RED_PINECONE_SHINGLES.get(), 8).define('D', Tags.Items.DYES_RED).define('#', PINECONE_SHINGLES.get()).pattern("###").pattern("#D#").pattern("###").unlockedBy(getHasName(PINECONE_SHINGLES.get()), has(PINECONE_SHINGLES.get())).save(output, getSaveLocation(RED_PINECONE_SHINGLES.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ORANGE_PINECONE_SHINGLES.get(), 8).define('D', Tags.Items.DYES_ORANGE).define('#', PINECONE_SHINGLES.get()).pattern("###").pattern("#D#").pattern("###").unlockedBy(getHasName(PINECONE_SHINGLES.get()), has(PINECONE_SHINGLES.get())).save(output, getSaveLocation(ORANGE_PINECONE_SHINGLES.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, YELLOW_PINECONE_SHINGLES.get(), 8).define('D', Tags.Items.DYES_YELLOW).define('#', PINECONE_SHINGLES.get()).pattern("###").pattern("#D#").pattern("###").unlockedBy(getHasName(PINECONE_SHINGLES.get()), has(PINECONE_SHINGLES.get())).save(output, getSaveLocation(YELLOW_PINECONE_SHINGLES.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, LIME_PINECONE_SHINGLES.get(), 8).define('D', Tags.Items.DYES_LIME).define('#', PINECONE_SHINGLES.get()).pattern("###").pattern("#D#").pattern("###").unlockedBy(getHasName(PINECONE_SHINGLES.get()), has(PINECONE_SHINGLES.get())).save(output, getSaveLocation(LIME_PINECONE_SHINGLES.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GREEN_PINECONE_SHINGLES.get(), 8).define('D', Tags.Items.DYES_GREEN).define('#', PINECONE_SHINGLES.get()).pattern("###").pattern("#D#").pattern("###").unlockedBy(getHasName(PINECONE_SHINGLES.get()), has(PINECONE_SHINGLES.get())).save(output, getSaveLocation(GREEN_PINECONE_SHINGLES.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, CYAN_PINECONE_SHINGLES.get(), 8).define('D', Tags.Items.DYES_CYAN).define('#', PINECONE_SHINGLES.get()).pattern("###").pattern("#D#").pattern("###").unlockedBy(getHasName(PINECONE_SHINGLES.get()), has(PINECONE_SHINGLES.get())).save(output, getSaveLocation(CYAN_PINECONE_SHINGLES.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, LIGHT_BLUE_PINECONE_SHINGLES.get(), 8).define('D', Tags.Items.DYES_LIGHT_BLUE).define('#', PINECONE_SHINGLES.get()).pattern("###").pattern("#D#").pattern("###").unlockedBy(getHasName(PINECONE_SHINGLES.get()), has(PINECONE_SHINGLES.get())).save(output, getSaveLocation(LIGHT_BLUE_PINECONE_SHINGLES.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BLUE_PINECONE_SHINGLES.get(), 8).define('D', Tags.Items.DYES_BLUE).define('#', PINECONE_SHINGLES.get()).pattern("###").pattern("#D#").pattern("###").unlockedBy(getHasName(PINECONE_SHINGLES.get()), has(PINECONE_SHINGLES.get())).save(output, getSaveLocation(BLUE_PINECONE_SHINGLES.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, PURPLE_PINECONE_SHINGLES.get(), 8).define('D', Tags.Items.DYES_PURPLE).define('#', PINECONE_SHINGLES.get()).pattern("###").pattern("#D#").pattern("###").unlockedBy(getHasName(PINECONE_SHINGLES.get()), has(PINECONE_SHINGLES.get())).save(output, getSaveLocation(PURPLE_PINECONE_SHINGLES.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, MAGENTA_PINECONE_SHINGLES.get(), 8).define('D', Tags.Items.DYES_MAGENTA).define('#', PINECONE_SHINGLES.get()).pattern("###").pattern("#D#").pattern("###").unlockedBy(getHasName(PINECONE_SHINGLES.get()), has(PINECONE_SHINGLES.get())).save(output, getSaveLocation(MAGENTA_PINECONE_SHINGLES.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, PINK_PINECONE_SHINGLES.get(), 8).define('D', Tags.Items.DYES_PINK).define('#', PINECONE_SHINGLES.get()).pattern("###").pattern("#D#").pattern("###").unlockedBy(getHasName(PINECONE_SHINGLES.get()), has(PINECONE_SHINGLES.get())).save(output, getSaveLocation(PINK_PINECONE_SHINGLES.get()));

        // wood sets
        woodSet(WindsweptItemTags.HOLLY_LOGS, HOLLY_PLANKS.get(), HOLLY_SLAB.get(), HOLLY_STAIRS.get(), HOLLY_LOG.get(), HOLLY_WOOD.get(), STRIPPED_HOLLY_LOG.get(), STRIPPED_HOLLY_WOOD.get(), HOLLY_BOAT.getFirst().get(), HOLLY_BOAT.getSecond().get(), HOLLY_BUTTON.get(), HOLLY_DOOR.get(), HOLLY_TRAPDOOR.get(), HOLLY_FENCE.get(), HOLLY_FENCE_GATE.get(), HOLLY_PRESSURE_PLATE.get(), HOLLY_SIGNS.getFirst().get(), HOLLY_BOARDS.get(), HOLLY_BEEHIVE.get(), HOLLY_LADDER.get(), HOLLY_BOOKSHELF.get(), HOLLY_CHEST.get(), TRAPPED_HOLLY_CHEST.get(), LARGE_HOLLY_BOAT.get(), HOLLY_FURNACE_BOAT.get(), HOLLY_HANGING_SIGNS.getFirst().get(), CHISELED_HOLLY_BOOKSHELF.get(), output);
        woodSet(WindsweptItemTags.CHESTNUT_LOGS, CHESTNUT_PLANKS.get(), CHESTNUT_SLAB.get(), CHESTNUT_STAIRS.get(), CHESTNUT_LOG.get(), CHESTNUT_WOOD.get(), STRIPPED_CHESTNUT_LOG.get(), STRIPPED_CHESTNUT_WOOD.get(), CHESTNUT_BOAT.getFirst().get(), CHESTNUT_BOAT.getSecond().get(), CHESTNUT_BUTTON.get(), CHESTNUT_DOOR.get(), CHESTNUT_TRAPDOOR.get(), CHESTNUT_FENCE.get(), CHESTNUT_FENCE_GATE.get(), CHESTNUT_PRESSURE_PLATE.get(), CHESTNUT_SIGNS.getFirst().get(), CHESTNUT_BOARDS.get(), CHESTNUT_BEEHIVE.get(), CHESTNUT_LADDER.get(), CHESTNUT_BOOKSHELF.get(), CHESTNUT_CHEST.get(), TRAPPED_CHESTNUT_CHEST.get(), LARGE_CHESTNUT_BOAT.get(), CHESTNUT_FURNACE_BOAT.get(), CHESTNUT_HANGING_SIGNS.getFirst().get(), CHISELED_CHESTNUT_BOOKSHELF.get(), output);
        woodSet(WindsweptItemTags.PINE_LOGS, PINE_PLANKS.get(), PINE_SLAB.get(), PINE_STAIRS.get(), PINE_LOG.get(), PINE_WOOD.get(), STRIPPED_PINE_LOG.get(), STRIPPED_PINE_WOOD.get(), PINE_BOAT.getFirst().get(), PINE_BOAT.getSecond().get(), PINE_BUTTON.get(), PINE_DOOR.get(), PINE_TRAPDOOR.get(), PINE_FENCE.get(), PINE_FENCE_GATE.get(), PINE_PRESSURE_PLATE.get(), PINE_SIGNS.getFirst().get(), PINE_BOARDS.get(), PINE_BEEHIVE.get(), PINE_LADDER.get(), PINE_BOOKSHELF.get(), PINE_CHEST.get(), TRAPPED_PINE_CHEST.get(), LARGE_PINE_BOAT.get(), PINE_FURNACE_BOAT.get(), PINE_HANGING_SIGNS.getFirst().get(), CHISELED_PINE_BOOKSHELF.get(), output);
        leafPile(HOLLY_LEAVES.get(), HOLLY_LEAF_PILE.get(), output);
        leafPile(CHESTNUT_LEAVES.get(), CHESTNUT_LEAF_PILE.get(), output);
        leafPile(PINE_LEAVES.get(), PINE_LEAF_PILE.get(), output);
        leafPile(FLOWERING_ACACIA_LEAVES.get(), FLOWERING_ACACIA_LEAF_PILE.get(), output);

        // compressed blocks
        compressedBlock(WILD_BERRY_BASKET.get(), WILD_BERRIES.get(), output);
        compressedBlock(HOLLY_BERRY_BASKET.get(), HOLLY_BERRIES.get(), output);
        compressedBlock(CHESTNUT_CRATE.get(), CHESTNUTS.get(), output);
        compressedBlock(ROASTED_CHESTNUT_CRATE.get(), ROASTED_CHESTNUTS.get(), output);
        compressedBlock(GINGER_ROOT_CRATE.get(), GINGER_ROOT.get(), output);
        compressedBlock(FROZEN_FLESH_BLOCK.get(), FROZEN_FLESH.get(), new ModLoadedCondition("caverns_and_chasms"), output);
        compressedBlock(PINECONE_BLOCK.get(), PINECONE.get(), output);
        compressedBlock(GINGERBREAD_COOKIE_BLOCK.get(), GINGERBREAD_COOKIE.get(), output);
        compressedBlock(CANDY_CANE_BLOCK.get(), CANDY_CANE.get(), output);
        compressedBlock(LAVENDER_BALE.get(), LAVENDER.get(), output);
    }

}
