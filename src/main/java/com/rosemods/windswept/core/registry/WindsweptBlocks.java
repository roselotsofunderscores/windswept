package com.rosemods.windswept.core.registry;

import com.mojang.datafixers.util.Pair;
import com.rosemods.windswept.common.block.*;
import com.rosemods.windswept.common.item.WearableBlockItem;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.other.WindsweptSoundTypes;
import com.rosemods.windswept.core.other.WindsweptTreeGrowers;
import com.teamabnormals.blueprint.common.block.BlueprintBeehiveBlock;
import com.teamabnormals.blueprint.common.block.BlueprintChiseledBookShelfBlock;
import com.teamabnormals.blueprint.common.block.LeafPileBlock;
import com.teamabnormals.blueprint.common.block.LogBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintChestBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintTrappedChestBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintCeilingHangingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintStandingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallHangingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallSignBlock;
import com.teamabnormals.blueprint.common.block.thatch.ThatchBlock;
import com.teamabnormals.blueprint.common.block.thatch.ThatchSlabBlock;
import com.teamabnormals.blueprint.common.block.thatch.ThatchStairBlock;
import com.teamabnormals.blueprint.core.api.BlockSetTypeRegistryHelper;
import com.teamabnormals.blueprint.core.api.WoodTypeRegistryHelper;
import com.teamabnormals.blueprint.core.util.PropertyUtil;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;

public final class WindsweptBlocks {
    public static final BlockSubRegistryHelper BLOCKS = Windswept.REGISTRY_HELPER.getBlockSubHelper();

    public static final DeferredBlock<Block> STRIPPED_HOLLY_LOG = BLOCKS.createBlock("stripped_holly_log", () -> new RotatedPillarBlock(Properties.HOLLY.log()));
    public static final DeferredBlock<Block> STRIPPED_HOLLY_WOOD = BLOCKS.createBlock("stripped_holly_wood", () -> new RotatedPillarBlock(Properties.HOLLY.log()));
    public static final DeferredBlock<Block> HOLLY_LOG = BLOCKS.createBlock("holly_log", () -> new LogBlock(STRIPPED_HOLLY_LOG, Properties.HOLLY.log()));
    public static final DeferredBlock<Block> HOLLY_WOOD = BLOCKS.createBlock("holly_wood", () -> new LogBlock(STRIPPED_HOLLY_WOOD, Properties.HOLLY.log()));
    public static final DeferredBlock<Block> HOLLY_PLANKS = BLOCKS.createBlock("holly_planks", () -> new Block(Properties.HOLLY.planks()));
    public static final DeferredBlock<Block> HOLLY_STAIRS = BLOCKS.createBlock("holly_stairs", () -> new StairBlock(HOLLY_PLANKS.get().defaultBlockState(), Properties.HOLLY.planks()));
    public static final DeferredBlock<Block> HOLLY_SLAB = BLOCKS.createBlock("holly_slab", () -> new SlabBlock(Properties.HOLLY.planks()));
    public static final DeferredBlock<Block> HOLLY_FENCE = BLOCKS.createBlock("holly_fence", () -> new FenceBlock(Properties.HOLLY.planks()));
    public static final DeferredBlock<Block> HOLLY_FENCE_GATE = BLOCKS.createBlock("holly_fence_gate", () -> new FenceGateBlock(Properties.HOLLY_WOOD_TYPE, Properties.HOLLY.planks()));
    public static final DeferredBlock<Block> HOLLY_PRESSURE_PLATE = BLOCKS.createBlock("holly_pressure_plate", () -> new PressurePlateBlock(Properties.HOLLY_BLOCK_SET, Properties.HOLLY.pressurePlate().sound(SoundType.CHERRY_WOOD)));
    public static final DeferredBlock<Block> HOLLY_BUTTON = BLOCKS.createBlock("holly_button", () -> new ButtonBlock(Properties.HOLLY_BLOCK_SET, 30, Properties.HOLLY.button()));
    public static final DeferredBlock<Block> HOLLY_DOOR = BLOCKS.createBlock("holly_door", () -> new DoorBlock(Properties.HOLLY_BLOCK_SET, Properties.HOLLY.door()));
    public static final DeferredBlock<Block> HOLLY_TRAPDOOR = BLOCKS.createBlock("holly_trapdoor", () -> new TrapDoorBlock(Properties.HOLLY_BLOCK_SET, Properties.HOLLY.trapdoor()));
    public static final Pair<DeferredBlock<BlueprintStandingSignBlock>, DeferredBlock<BlueprintWallSignBlock>> HOLLY_SIGNS = BLOCKS.createSignBlock("holly", Properties.HOLLY_WOOD_TYPE, Properties.HOLLY.sign().sound(SoundType.CHERRY_WOOD));
    public static final Pair<DeferredBlock<BlueprintCeilingHangingSignBlock>, DeferredBlock<BlueprintWallHangingSignBlock>> HOLLY_HANGING_SIGNS = BLOCKS.createHangingSignBlock("holly", Properties.HOLLY_WOOD_TYPE, Properties.HOLLY.hangingSign().sound(SoundType.CHERRY_WOOD_HANGING_SIGN));

    public static final DeferredBlock<Block> HOLLY_BEEHIVE = BLOCKS.createBlock("holly_beehive", () -> new BlueprintBeehiveBlock(Properties.HOLLY.beehive()));
    public static final DeferredBlock<Block> HOLLY_LADDER = BLOCKS.createBlock("holly_ladder", () -> new LadderBlock(Properties.HOLLY.ladder()));
    public static final DeferredBlock<Block> HOLLY_BOOKSHELF = BLOCKS.createBlock("holly_bookshelf", () -> new Block(Properties.HOLLY.bookshelf()));
    public static final DeferredBlock<Block> CHISELED_HOLLY_BOOKSHELF = BLOCKS.createBlock("chiseled_holly_bookshelf", () -> new BlueprintChiseledBookShelfBlock(Properties.HOLLY.chiseledBookshelf()));
    public static final DeferredBlock<Block> HOLLY_BOARDS = BLOCKS.createBlock("holly_boards", () -> new RotatedPillarBlock(Properties.HOLLY.planks()));
    public static final DeferredBlock<BlueprintChestBlock> HOLLY_CHEST = BLOCKS.createChestBlock("holly", Properties.HOLLY.chest());
    public static final DeferredBlock<BlueprintTrappedChestBlock> TRAPPED_HOLLY_CHEST = BLOCKS.createTrappedChestBlock("holly", Properties.HOLLY.chest());

    public static final DeferredBlock<Block> HOLLY_SAPLING = BLOCKS.createBlock("holly_sapling", () -> new HollySaplingBlock(WindsweptTreeGrowers.HOLLY, PropertyUtil.sapling()));
    public static final DeferredBlock<Block> POTTED_HOLLY_SAPLING = BLOCKS.createBlockNoItem("potted_holly_sapling", () -> new FlowerPotBlock(HOLLY_SAPLING.get(), PropertyUtil.flowerPot()));
    public static final DeferredBlock<Block> HOLLY_LEAVES = BLOCKS.createBlock("holly_leaves", () -> new HollyLeavesBlock(Properties.HOLLY.leaves()));
    public static final DeferredBlock<Block> HOLLY_LEAF_PILE = BLOCKS.createBlock("holly_leaf_pile", () -> new LeafPileBlock(Properties.HOLLY.leafPile()));

    public static final DeferredBlock<Block> STRIPPED_CHESTNUT_LOG = BLOCKS.createBlock("stripped_chestnut_log", () -> new RotatedPillarBlock(Properties.CHESTNUT.log()));
    public static final DeferredBlock<Block> STRIPPED_CHESTNUT_WOOD = BLOCKS.createBlock("stripped_chestnut_wood", () -> new RotatedPillarBlock(Properties.CHESTNUT.log()));
    public static final DeferredBlock<Block> CHESTNUT_LOG = BLOCKS.createBlock("chestnut_log", () -> new LogBlock(STRIPPED_CHESTNUT_LOG, Properties.CHESTNUT.log()));
    public static final DeferredBlock<Block> CHESTNUT_WOOD = BLOCKS.createBlock("chestnut_wood", () -> new LogBlock(STRIPPED_CHESTNUT_WOOD, Properties.CHESTNUT.log()));
    public static final DeferredBlock<Block> CHESTNUT_PLANKS = BLOCKS.createBlock("chestnut_planks", () -> new Block(Properties.CHESTNUT.planks()));
    public static final DeferredBlock<Block> CHESTNUT_STAIRS = BLOCKS.createBlock("chestnut_stairs", () -> new StairBlock(CHESTNUT_PLANKS.get().defaultBlockState(), Properties.CHESTNUT.planks()));
    public static final DeferredBlock<Block> CHESTNUT_SLAB = BLOCKS.createBlock("chestnut_slab", () -> new SlabBlock(Properties.CHESTNUT.planks()));
    public static final DeferredBlock<Block> CHESTNUT_FENCE = BLOCKS.createBlock("chestnut_fence", () -> new FenceBlock(Properties.CHESTNUT.planks()));
    public static final DeferredBlock<Block> CHESTNUT_FENCE_GATE = BLOCKS.createBlock("chestnut_fence_gate", () -> new FenceGateBlock(Properties.CHESTNUT_WOOD_TYPE, Properties.CHESTNUT.planks()));
    public static final DeferredBlock<Block> CHESTNUT_PRESSURE_PLATE = BLOCKS.createBlock("chestnut_pressure_plate", () -> new PressurePlateBlock(Properties.CHESTNUT_BLOCK_SET, Properties.CHESTNUT.pressurePlate()));
    public static final DeferredBlock<Block> CHESTNUT_BUTTON = BLOCKS.createBlock("chestnut_button", () -> new ButtonBlock(Properties.CHESTNUT_BLOCK_SET, 30, Properties.CHESTNUT.button()));
    public static final DeferredBlock<Block> CHESTNUT_DOOR = BLOCKS.createBlock("chestnut_door", () -> new DoorBlock(Properties.CHESTNUT_BLOCK_SET, Properties.CHESTNUT.door()));
    public static final DeferredBlock<Block> CHESTNUT_TRAPDOOR = BLOCKS.createBlock("chestnut_trapdoor", () -> new TrapDoorBlock(Properties.CHESTNUT_BLOCK_SET, Properties.CHESTNUT.trapdoor()));
    public static final Pair<DeferredBlock<BlueprintStandingSignBlock>, DeferredBlock<BlueprintWallSignBlock>> CHESTNUT_SIGNS = BLOCKS.createSignBlock("chestnut", Properties.CHESTNUT_WOOD_TYPE, Properties.CHESTNUT.sign());
    public static final Pair<DeferredBlock<BlueprintCeilingHangingSignBlock>, DeferredBlock<BlueprintWallHangingSignBlock>> CHESTNUT_HANGING_SIGNS = BLOCKS.createHangingSignBlock("chestnut", Properties.CHESTNUT_WOOD_TYPE, Properties.CHESTNUT.hangingSign());

    public static final DeferredBlock<Block> CHESTNUT_BEEHIVE = BLOCKS.createBlock("chestnut_beehive", () -> new BlueprintBeehiveBlock(Properties.CHESTNUT.beehive()));
    public static final DeferredBlock<Block> CHESTNUT_LADDER = BLOCKS.createBlock("chestnut_ladder", () -> new LadderBlock(Properties.CHESTNUT.ladder()));
    public static final DeferredBlock<Block> CHESTNUT_BOOKSHELF = BLOCKS.createBlock("chestnut_bookshelf", () -> new Block(Properties.CHESTNUT.bookshelf()));
    public static final DeferredBlock<Block> CHISELED_CHESTNUT_BOOKSHELF = BLOCKS.createBlock("chiseled_chestnut_bookshelf", () -> new BlueprintChiseledBookShelfBlock(Properties.CHESTNUT.chiseledBookshelf()));
    public static final DeferredBlock<Block> CHESTNUT_BOARDS = BLOCKS.createBlock("chestnut_boards", () -> new RotatedPillarBlock(Properties.CHESTNUT.planks()));
    public static final DeferredBlock<BlueprintChestBlock> CHESTNUT_CHEST = BLOCKS.createChestBlock("chestnut", Properties.CHESTNUT.chest());
    public static final DeferredBlock<BlueprintTrappedChestBlock> TRAPPED_CHESTNUT_CHEST = BLOCKS.createTrappedChestBlock("chestnut", Properties.CHESTNUT.chest());

    public static final DeferredBlock<Block> CHESTNUT_SAPLING = BLOCKS.createBlock("chestnut_sapling", () -> new SaplingBlock(WindsweptTreeGrowers.CHESTNUT, PropertyUtil.sapling()));
    public static final DeferredBlock<Block> POTTED_CHESTNUT_SAPLING = BLOCKS.createBlockNoItem("potted_chestnut_sapling", () -> new FlowerPotBlock(CHESTNUT_SAPLING.get(), PropertyUtil.flowerPot()));
    public static final DeferredBlock<Block> CHESTNUT_LEAVES = BLOCKS.createBlock("chestnut_leaves", () -> new LeavesBlock(Properties.CHESTNUT.leaves()));
    public static final DeferredBlock<Block> CHESTNUT_LEAF_PILE = BLOCKS.createBlock("chestnut_leaf_pile", () -> new LeafPileBlock(Properties.CHESTNUT.leafPile()));

    public static final DeferredBlock<Block> STRIPPED_PINE_LOG = BLOCKS.createBlock("stripped_pine_log", () -> new RotatedPillarBlock(Properties.PINE.log()));
    public static final DeferredBlock<Block> STRIPPED_PINE_WOOD = BLOCKS.createBlock("stripped_pine_wood", () -> new RotatedPillarBlock(Properties.PINE.log()));
    public static final DeferredBlock<Block> WEATHERED_PINE_LOG = BLOCKS.createBlock("weathered_pine_log", () -> new LogBlock(STRIPPED_PINE_LOG, Properties.PINE.log()));
    public static final DeferredBlock<Block> WEATHERED_PINE_WOOD = BLOCKS.createBlock("weathered_pine_wood", () -> new LogBlock(STRIPPED_PINE_WOOD, Properties.PINE.log()));
    public static final DeferredBlock<Block> PINE_LOG = BLOCKS.createBlock("pine_log", () -> new LogBlock(WEATHERED_PINE_LOG, Properties.PINE.log()));
    public static final DeferredBlock<Block> PINE_WOOD = BLOCKS.createBlock("pine_wood", () -> new LogBlock(WEATHERED_PINE_WOOD, Properties.PINE.log()));
    public static final DeferredBlock<Block> PINE_PLANKS = BLOCKS.createBlock("pine_planks", () -> new Block(Properties.PINE.planks()));
    public static final DeferredBlock<Block> PINE_STAIRS = BLOCKS.createBlock("pine_stairs", () -> new StairBlock(PINE_PLANKS.get().defaultBlockState(), Properties.PINE.planks()));
    public static final DeferredBlock<Block> PINE_SLAB = BLOCKS.createBlock("pine_slab", () -> new SlabBlock(Properties.PINE.planks()));
    public static final DeferredBlock<Block> PINE_FENCE = BLOCKS.createBlock("pine_fence", () -> new FenceBlock(Properties.PINE.planks()));
    public static final DeferredBlock<Block> PINE_FENCE_GATE = BLOCKS.createBlock("pine_fence_gate", () -> new FenceGateBlock(Properties.PINE_WOOD_TYPE, Properties.PINE.planks()));
    public static final DeferredBlock<Block> PINE_PRESSURE_PLATE = BLOCKS.createBlock("pine_pressure_plate", () -> new PressurePlateBlock(Properties.PINE_BLOCK_SET, Properties.PINE.pressurePlate()));
    public static final DeferredBlock<Block> PINE_BUTTON = BLOCKS.createBlock("pine_button", () -> new ButtonBlock(Properties.PINE_BLOCK_SET, 30, Properties.PINE.button()));
    public static final DeferredBlock<Block> PINE_DOOR = BLOCKS.createBlock("pine_door", () -> new DoorBlock(Properties.PINE_BLOCK_SET, Properties.PINE.door()));
    public static final DeferredBlock<Block> PINE_TRAPDOOR = BLOCKS.createBlock("pine_trapdoor", () -> new TrapDoorBlock(Properties.PINE_BLOCK_SET, Properties.PINE.trapdoor()));
    public static final Pair<DeferredBlock<BlueprintStandingSignBlock>, DeferredBlock<BlueprintWallSignBlock>> PINE_SIGNS = BLOCKS.createSignBlock("pine", Properties.PINE_WOOD_TYPE, Properties.PINE.sign());
    public static final Pair<DeferredBlock<BlueprintCeilingHangingSignBlock>, DeferredBlock<BlueprintWallHangingSignBlock>> PINE_HANGING_SIGNS = BLOCKS.createHangingSignBlock("pine", Properties.PINE_WOOD_TYPE, Properties.PINE.hangingSign());

    public static final DeferredBlock<Block> PINE_BEEHIVE = BLOCKS.createBlock("pine_beehive", () -> new BlueprintBeehiveBlock(Properties.PINE.beehive()));
    public static final DeferredBlock<Block> PINE_LADDER = BLOCKS.createBlock("pine_ladder", () -> new LadderBlock(Properties.PINE.ladder()));
    public static final DeferredBlock<Block> PINE_BOOKSHELF = BLOCKS.createBlock("pine_bookshelf", () -> new Block(Properties.PINE.bookshelf()));
    public static final DeferredBlock<Block> CHISELED_PINE_BOOKSHELF = BLOCKS.createBlock("chiseled_pine_bookshelf", () -> new BlueprintChiseledBookShelfBlock(Properties.PINE.chiseledBookshelf()));
    public static final DeferredBlock<Block> PINE_BOARDS = BLOCKS.createBlock("pine_boards", () -> new RotatedPillarBlock(Properties.PINE.planks()));
    public static final DeferredBlock<BlueprintChestBlock> PINE_CHEST = BLOCKS.createChestBlock("pine", Properties.PINE.chest());
    public static final DeferredBlock<BlueprintTrappedChestBlock> TRAPPED_PINE_CHEST = BLOCKS.createTrappedChestBlock("pine", Properties.PINE.chest());

    public static final DeferredBlock<Block> PINE_SAPLING = BLOCKS.createBlock("pine_sapling", () -> new SaplingBlock(WindsweptTreeGrowers.PINE, PropertyUtil.sapling()));
    public static final DeferredBlock<Block> POTTED_PINE_SAPLING = BLOCKS.createBlockNoItem("potted_pine_sapling", () -> new FlowerPotBlock(PINE_SAPLING.get(), PropertyUtil.flowerPot()));
    public static final DeferredBlock<Block> PINE_LEAVES = BLOCKS.createBlock("pine_leaves", () -> new LeavesBlock(Properties.PINE.leaves()));
    public static final DeferredBlock<Block> PINE_LEAF_PILE = BLOCKS.createBlock("pine_leaf_pile", () -> new LeafPileBlock(Properties.PINE.leafPile()));

    public static final DeferredBlock<Block> MOSSY_SPROUTS = BLOCKS.createBlock("mossy_sprouts", () -> new SproutsBlock(Properties.SPROUTS));
    public static final DeferredBlock<Block> DRY_MOSSY_SPROUTS = BLOCKS.createBlock("dry_mossy_sprouts", () -> new SproutsBlock(Properties.SPROUTS));
    public static final DeferredBlock<Block> DRY_MOSS_CARPET = BLOCKS.createBlock("dry_moss_carpet", () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSS_CARPET)));
    public static final DeferredBlock<Block> DRY_MOSS_BLOCK = BLOCKS.createBlock("dry_moss_block", () -> new DryMossBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSS_BLOCK)));

    public static final DeferredBlock<Block> GELISOL_GRASS = BLOCKS.createBlock("gelisol_grass", () -> new SproutsBlock(Properties.SPROUTS));
    public static final DeferredBlock<Block> GELISOL = BLOCKS.createBlock("gelisol", () -> new GelisolBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PODZOL)));
    public static final DeferredBlock<Block> GELISOL_PATH = BLOCKS.createBlock("gelisol_path", () -> new DirtPathBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT_PATH)));

    public static final DeferredBlock<Block> RED_ROSE = BLOCKS.createBlock("red_rose", () -> new RoseFlowerBlock(WindsweptBlocks.RED_ROSE_BUSH, MobEffects.WITHER, 5, PropertyUtil.flower()));
    public static final DeferredBlock<Block> BLUE_ROSE = BLOCKS.createBlock("blue_rose", () -> new RoseFlowerBlock(WindsweptBlocks.BLUE_ROSE_BUSH, MobEffects.WITHER, 5, PropertyUtil.flower()));
    public static final DeferredBlock<Block> WHITE_ROSE = BLOCKS.createBlock("white_rose", () -> new RoseFlowerBlock(WindsweptBlocks.WHITE_ROSE_BUSH, MobEffects.WITHER, 5, PropertyUtil.flower()));
    public static final DeferredBlock<Block> YELLOW_ROSE = BLOCKS.createBlock("yellow_rose", () -> new RoseFlowerBlock(WindsweptBlocks.YELLOW_ROSE_BUSH, MobEffects.WITHER, 5, PropertyUtil.flower()));
    public static final DeferredBlock<Block> FOXGLOVE = BLOCKS.createBlock("foxglove", () -> new FlowerBlock(MobEffects.MOVEMENT_SPEED, 5, PropertyUtil.flower()));
    public static final DeferredBlock<Block> BLUEBELLS = BLOCKS.createBlock("bluebells", () -> new FlowerBlock(MobEffects.SLOW_FALLING, 5, PropertyUtil.flower()));
    public static final DeferredBlock<Block> SNOWY_SPROUTS = BLOCKS.createBlock("snowy_sprouts", () -> new SproutsBlock(Properties.SPROUTS));
    public static final DeferredBlock<Block> SNOWDROP = BLOCKS.createBlock("snowdrop", () -> new SnowdropBlock(WindsweptEffects.FROST_RESISTANCE, 5, PropertyUtil.flower().sound(SoundType.NETHER_SPROUTS)));
    public static final DeferredBlock<Block> MOSS_CAMPION = BLOCKS.createBlock("moss_campion", () -> new MossCampionBlock(WindsweptEffects.THORNS, 5, PropertyUtil.flower().sound(SoundType.AZALEA)));
    public static final DeferredBlock<Block> WILD_GINGER = BLOCKS.createBlock("wild_ginger", () -> new FlowerBlock(MobEffects.DAMAGE_BOOST, 5, PropertyUtil.flower()));
    public static final DeferredBlock<Block> NIGHTSHADE = BLOCKS.createBlock("nightshade", () -> new NightshadeFlowerBlock(MobEffects.NIGHT_VISION, 5, BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).lightLevel(state -> 9)));
    public static final DeferredBlock<Block> MIMOSA = BLOCKS.createBlock("mimosa", () -> new MimosaBlock(MobEffects.FIRE_RESISTANCE, 5, PropertyUtil.flower().sound(SoundType.NETHER_SPROUTS)));

    public static final DeferredBlock<Block> LUPINE = BLOCKS.createBlock("lupine", () -> new TallFlowerBlock(PropertyUtil.flower()));
    public static final DeferredBlock<Block> LIONS_TAIL = BLOCKS.createBlock("lions_tail", () -> new TallFlowerBlock(PropertyUtil.flower()));
    public static final DeferredBlock<Block> RED_ROSE_BUSH = BLOCKS.createBlock("red_rose_bush", () -> new TallFlowerBlock(PropertyUtil.flower()));
    public static final DeferredBlock<Block> BLUE_ROSE_BUSH = BLOCKS.createBlock("blue_rose_bush", () -> new TallFlowerBlock(PropertyUtil.flower()));
    public static final DeferredBlock<Block> WHITE_ROSE_BUSH = BLOCKS.createBlock("white_rose_bush", () -> new TallFlowerBlock(PropertyUtil.flower()));
    public static final DeferredBlock<Block> YELLOW_ROSE_BUSH = BLOCKS.createBlock("yellow_rose_bush", () -> new TallFlowerBlock(PropertyUtil.flower()));

    public static final DeferredBlock<Block> LAVENDER = BLOCKS.createBlock("lavender", () -> new LavenderBlock(PropertyUtil.flower().sound(SoundType.AZALEA).randomTicks()));
    public static final DeferredBlock<Block> LAVENDER_BALE = BLOCKS.createBlock("lavender_bale", () -> new LavenderBaleBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.HAY_BLOCK)));
    public static final DeferredBlock<Block> LAVENDER_THATCH = BLOCKS.createBlock("lavender_thatch", () -> new ThatchBlock(Properties.LAVENDER_THATCH));
    public static final DeferredBlock<Block> LAVENDER_THATCH_STAIRS = BLOCKS.createBlock("lavender_thatch_stairs", () -> new ThatchStairBlock(LAVENDER_THATCH.get().defaultBlockState(), Properties.LAVENDER_THATCH));
    public static final DeferredBlock<Block> LAVENDER_THATCH_SLAB = BLOCKS.createBlock("lavender_thatch_slab", () -> new ThatchSlabBlock(Properties.LAVENDER_THATCH));

    public static final DeferredBlock<Block> FLOWERING_ACACIA_SAPLING = BLOCKS.createBlock("flowering_acacia_sapling", () -> new SaplingBlock(WindsweptTreeGrowers.FLOWERING_ACACIA, PropertyUtil.sapling()));
    public static final DeferredBlock<Block> POTTED_FLOWERING_ACACIA_SAPLING = BLOCKS.createBlockNoItem("potted_flowering_acacia_sapling", () -> new FlowerPotBlock(FLOWERING_ACACIA_SAPLING.get(), PropertyUtil.flowerPot()));
    public static final DeferredBlock<Block> FLOWERING_ACACIA_LEAVES = BLOCKS.createBlock("flowering_acacia_leaves", () -> new FloweringAcaciaLeavesBlock(Properties.HOLLY.leaves()));
    public static final DeferredBlock<Block> FLOWERING_ACACIA_LEAF_PILE = BLOCKS.createBlock("flowering_acacia_leaf_pile", () -> new LeafPileBlock(Properties.HOLLY.leafPile()));
    public static final DeferredBlock<Block> YELLOW_PETALS = BLOCKS.createBlock("yellow_petals", () -> new PinkPetalsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PINK_PETALS)));

    public static final DeferredBlock<Block> HOLLY_WREATH = BLOCKS.createBlockWithItem("holly_wreath", () -> new WallDecorationBlock(Block.Properties.of().instabreak().sound(SoundType.AZALEA).noCollission()), () -> new WearableBlockItem(WindsweptBlocks.HOLLY_WREATH.get(), new Item.Properties()));
    public static final DeferredBlock<Block> PINECONE_WREATH = BLOCKS.createBlockWithItem("pinecone_wreath", () -> new WallDecorationBlock(Block.Properties.of().instabreak().sound(SoundType.AZALEA).noCollission()), () -> new WearableBlockItem(WindsweptBlocks.PINECONE_WREATH.get(), new Item.Properties()));
    public static final DeferredBlock<Block> VINE_WREATH = BLOCKS.createBlockWithItem("vine_wreath", () -> new WallDecorationBlock(Block.Properties.of().instabreak().sound(SoundType.AZALEA).noCollission()), () -> new WearableBlockItem(WindsweptBlocks.VINE_WREATH.get(), new Item.Properties()));
    public static final DeferredBlock<Block> CHERRY_WREATH = BLOCKS.createBlockWithItem("cherry_wreath", () -> new WallDecorationBlock(Block.Properties.of().instabreak().sound(SoundType.AZALEA).noCollission()), () -> new WearableBlockItem(WindsweptBlocks.CHERRY_WREATH.get(), new Item.Properties()));
    public static final DeferredBlock<Block> CHRISTMAS_PUDDING = BLOCKS.createBlock("christmas_pudding", () -> new ChristmasPuddingBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE).noOcclusion()), new Item.Properties().stacksTo(1));
    public static final DeferredBlock<Block> FROSTBITER_TROPHY = BLOCKS.createBlock("frostbiter_trophy", () -> new WallDecorationBlock(Properties.HOLLY.ladder().sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> ICE_SHEET = BLOCKS.createBlock("ice_sheet", () -> new IceSheetBlock(BlockBehaviour.Properties.of().strength(.3f).sound(SoundType.GLASS).noOcclusion().friction(.98f)));

    public static final DeferredBlock<Block> PINECONE = BLOCKS.createBlock("pinecone", () -> new PineconeBlock(Block.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(.25f).offsetType(BlockBehaviour.OffsetType.XZ).dynamicShape().noOcclusion().sound(WindsweptSoundTypes.PINECONE)));
    public static final DeferredBlock<Block> PINECONE_JAM_BLOCK = BLOCKS.createBlock("pinecone_jam_block", () -> new PineconeJamBlock(Block.Properties.of().mapColor(MapColor.COLOR_RED).noOcclusion().sound(SoundType.HONEY_BLOCK)));

    public static final DeferredBlock<Block> FAIRY_LIGHT = BLOCKS.createBlock("fairy_light", () -> new PineconeBlock(BlockBehaviour.Properties.ofFullCopy(PINECONE.get()).lightLevel(s -> 14)));
    public static final DeferredBlock<Block> SOUL_FAIRY_LIGHT = BLOCKS.createBlock("soul_fairy_light", () -> new PineconeBlock(BlockBehaviour.Properties.ofFullCopy(PINECONE.get()).lightLevel(s -> 10)));
    public static final DeferredBlock<Block> CUPRIC_FAIRY_LIGHT = BLOCKS.createBlock("cupric_fairy_light", () -> new PineconeBlock(BlockBehaviour.Properties.ofFullCopy(PINECONE.get()).lightLevel(s -> 10)));
    public static final DeferredBlock<Block> ENDER_FAIRY_LIGHT = BLOCKS.createBlock("ender_fairy_light", () -> new PineconeBlock(BlockBehaviour.Properties.ofFullCopy(PINECONE.get()).lightLevel(s -> 14)));
    public static final DeferredBlock<Block> NIGHT_FAIRY_LIGHT = BLOCKS.createBlock("night_fairy_light", () -> new NightFairyLightBlock(BlockBehaviour.Properties.ofFullCopy(PINECONE.get()).lightLevel(s -> s.getValue(NightFairyLightBlock.LIT) ? 9 : 0)));
    public static final DeferredBlock<Block> REDSTONE_FAIRY_LIGHT = BLOCKS.createBlock("redstone_fairy_light", () -> new RedstoneFairyLightBlock(BlockBehaviour.Properties.ofFullCopy(PINECONE.get()).lightLevel(s -> s.getValue(RedstoneFairyLightBlock.LIT) ? 7 : 0)));

    public static final DeferredBlock<Block> ELDER_WING = BLOCKS.createBlock("elder_wing", () -> new FeatherWingBlock(Block.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(.1f).noOcclusion().noCollission().sound(WindsweptSoundTypes.PINECONE)));
    public static final DeferredBlock<Block> ELDER_ORNAMENT = BLOCKS.createBlock("elder_ornament", () -> new FeatherOrnamentBlock(Properties.ELDER_ORNAMENT));
    public static final DeferredBlock<Block> DREAM_CATCHER = BLOCKS.createBlock("dream_catcher", () -> new DreamCatcherBlock(Properties.ELDER_ORNAMENT));

    public static final DeferredBlock<Block> PINECONE_BLOCK = BLOCKS.createBlock("pinecone_block", () -> new PineconeBlockBlock(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> CARVED_PINECONE_BLOCK = BLOCKS.createBlockWithItem("carved_pinecone_block", () -> new CarvedPineconeBlock(BlockBehaviour.Properties.ofFullCopy(PINECONE_BLOCK.get()).randomTicks()), () -> new WearableBlockItem(WindsweptBlocks.CARVED_PINECONE_BLOCK.get(), new Item.Properties()));
    public static final DeferredBlock<Block> WILL_O_THE_WISP = BLOCKS.createBlock("will_o_the_wisp", () -> new WillOTheWispBlock(BlockBehaviour.Properties.ofFullCopy(PINECONE_BLOCK.get()).lightLevel(s -> 10)), new Item.Properties().rarity(Rarity.RARE));

    public static final DeferredBlock<Block> PINECONE_SHINGLES = BLOCKS.createBlock("pinecone_shingles", () -> new Block(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> PINECONE_SHINGLE_STAIRS = BLOCKS.createBlock("pinecone_shingle_stairs", () -> new StairBlock(PINECONE_SHINGLES.get().defaultBlockState(), Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> PINECONE_SHINGLE_SLAB = BLOCKS.createBlock("pinecone_shingle_slab", () -> new SlabBlock(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> WHITE_PINECONE_SHINGLES = BLOCKS.createBlock("white_pinecone_shingles", () -> new Block(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> WHITE_PINECONE_SHINGLE_STAIRS = BLOCKS.createBlock("white_pinecone_shingle_stairs", () -> new StairBlock(PINECONE_SHINGLES.get().defaultBlockState(), Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> WHITE_PINECONE_SHINGLE_SLAB = BLOCKS.createBlock("white_pinecone_shingle_slab", () -> new SlabBlock(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> LIGHT_GRAY_PINECONE_SHINGLES = BLOCKS.createBlock("light_gray_pinecone_shingles", () -> new Block(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> LIGHT_GRAY_PINECONE_SHINGLE_STAIRS = BLOCKS.createBlock("light_gray_pinecone_shingle_stairs", () -> new StairBlock(PINECONE_SHINGLES.get().defaultBlockState(), Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> LIGHT_GRAY_PINECONE_SHINGLE_SLAB = BLOCKS.createBlock("light_gray_pinecone_shingle_slab", () -> new SlabBlock(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> GRAY_PINECONE_SHINGLES = BLOCKS.createBlock("gray_pinecone_shingles", () -> new Block(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> GRAY_PINECONE_SHINGLE_STAIRS = BLOCKS.createBlock("gray_pinecone_shingle_stairs", () -> new StairBlock(PINECONE_SHINGLES.get().defaultBlockState(), Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> GRAY_PINECONE_SHINGLE_SLAB = BLOCKS.createBlock("gray_pinecone_shingle_slab", () -> new SlabBlock(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> BLACK_PINECONE_SHINGLES = BLOCKS.createBlock("black_pinecone_shingles", () -> new Block(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> BLACK_PINECONE_SHINGLE_STAIRS = BLOCKS.createBlock("black_pinecone_shingle_stairs", () -> new StairBlock(PINECONE_SHINGLES.get().defaultBlockState(), Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> BLACK_PINECONE_SHINGLE_SLAB = BLOCKS.createBlock("black_pinecone_shingle_slab", () -> new SlabBlock(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> BROWN_PINECONE_SHINGLES = BLOCKS.createBlock("brown_pinecone_shingles", () -> new Block(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> BROWN_PINECONE_SHINGLE_STAIRS = BLOCKS.createBlock("brown_pinecone_shingle_stairs", () -> new StairBlock(PINECONE_SHINGLES.get().defaultBlockState(), Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> BROWN_PINECONE_SHINGLE_SLAB = BLOCKS.createBlock("brown_pinecone_shingle_slab", () -> new SlabBlock(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> RED_PINECONE_SHINGLES = BLOCKS.createBlock("red_pinecone_shingles", () -> new Block(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> RED_PINECONE_SHINGLE_STAIRS = BLOCKS.createBlock("red_pinecone_shingle_stairs", () -> new StairBlock(PINECONE_SHINGLES.get().defaultBlockState(), Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> RED_PINECONE_SHINGLE_SLAB = BLOCKS.createBlock("red_pinecone_shingle_slab", () -> new SlabBlock(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> ORANGE_PINECONE_SHINGLES = BLOCKS.createBlock("orange_pinecone_shingles", () -> new Block(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> ORANGE_PINECONE_SHINGLE_STAIRS = BLOCKS.createBlock("orange_pinecone_shingle_stairs", () -> new StairBlock(PINECONE_SHINGLES.get().defaultBlockState(), Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> ORANGE_PINECONE_SHINGLE_SLAB = BLOCKS.createBlock("orange_pinecone_shingle_slab", () -> new SlabBlock(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> YELLOW_PINECONE_SHINGLES = BLOCKS.createBlock("yellow_pinecone_shingles", () -> new Block(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> YELLOW_PINECONE_SHINGLE_STAIRS = BLOCKS.createBlock("yellow_pinecone_shingle_stairs", () -> new StairBlock(PINECONE_SHINGLES.get().defaultBlockState(), Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> YELLOW_PINECONE_SHINGLE_SLAB = BLOCKS.createBlock("yellow_pinecone_shingle_slab", () -> new SlabBlock(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> LIME_PINECONE_SHINGLES = BLOCKS.createBlock("lime_pinecone_shingles", () -> new Block(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> LIME_PINECONE_SHINGLE_STAIRS = BLOCKS.createBlock("lime_pinecone_shingle_stairs", () -> new StairBlock(PINECONE_SHINGLES.get().defaultBlockState(), Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> LIME_PINECONE_SHINGLE_SLAB = BLOCKS.createBlock("lime_pinecone_shingle_slab", () -> new SlabBlock(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> GREEN_PINECONE_SHINGLES = BLOCKS.createBlock("green_pinecone_shingles", () -> new Block(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> GREEN_PINECONE_SHINGLE_STAIRS = BLOCKS.createBlock("green_pinecone_shingle_stairs", () -> new StairBlock(PINECONE_SHINGLES.get().defaultBlockState(), Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> GREEN_PINECONE_SHINGLE_SLAB = BLOCKS.createBlock("green_pinecone_shingle_slab", () -> new SlabBlock(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> CYAN_PINECONE_SHINGLES = BLOCKS.createBlock("cyan_pinecone_shingles", () -> new Block(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> CYAN_PINECONE_SHINGLE_STAIRS = BLOCKS.createBlock("cyan_pinecone_shingle_stairs", () -> new StairBlock(PINECONE_SHINGLES.get().defaultBlockState(), Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> CYAN_PINECONE_SHINGLE_SLAB = BLOCKS.createBlock("cyan_pinecone_shingle_slab", () -> new SlabBlock(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> LIGHT_BLUE_PINECONE_SHINGLES = BLOCKS.createBlock("light_blue_pinecone_shingles", () -> new Block(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> LIGHT_BLUE_PINECONE_SHINGLE_STAIRS = BLOCKS.createBlock("light_blue_pinecone_shingle_stairs", () -> new StairBlock(PINECONE_SHINGLES.get().defaultBlockState(), Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> LIGHT_BLUE_PINECONE_SHINGLE_SLAB = BLOCKS.createBlock("light_blue_pinecone_shingle_slab", () -> new SlabBlock(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> BLUE_PINECONE_SHINGLES = BLOCKS.createBlock("blue_pinecone_shingles", () -> new Block(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> BLUE_PINECONE_SHINGLE_STAIRS = BLOCKS.createBlock("blue_pinecone_shingle_stairs", () -> new StairBlock(PINECONE_SHINGLES.get().defaultBlockState(), Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> BLUE_PINECONE_SHINGLE_SLAB = BLOCKS.createBlock("blue_pinecone_shingle_slab", () -> new SlabBlock(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> PURPLE_PINECONE_SHINGLES = BLOCKS.createBlock("purple_pinecone_shingles", () -> new Block(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> PURPLE_PINECONE_SHINGLE_STAIRS = BLOCKS.createBlock("purple_pinecone_shingle_stairs", () -> new StairBlock(PINECONE_SHINGLES.get().defaultBlockState(), Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> PURPLE_PINECONE_SHINGLE_SLAB = BLOCKS.createBlock("purple_pinecone_shingle_slab", () -> new SlabBlock(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> MAGENTA_PINECONE_SHINGLES = BLOCKS.createBlock("magenta_pinecone_shingles", () -> new Block(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> MAGENTA_PINECONE_SHINGLE_STAIRS = BLOCKS.createBlock("magenta_pinecone_shingle_stairs", () -> new StairBlock(PINECONE_SHINGLES.get().defaultBlockState(), Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> MAGENTA_PINECONE_SHINGLE_SLAB = BLOCKS.createBlock("magenta_pinecone_shingle_slab", () -> new SlabBlock(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> PINK_PINECONE_SHINGLES = BLOCKS.createBlock("pink_pinecone_shingles", () -> new Block(Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> PINK_PINECONE_SHINGLE_STAIRS = BLOCKS.createBlock("pink_pinecone_shingle_stairs", () -> new StairBlock(PINECONE_SHINGLES.get().defaultBlockState(), Properties.PINECONE_BLOCK));
    public static final DeferredBlock<Block> PINK_PINECONE_SHINGLE_SLAB = BLOCKS.createBlock("pink_pinecone_shingle_slab", () -> new SlabBlock(Properties.PINECONE_BLOCK));

    public static final DeferredBlock<Block> ICICLES = BLOCKS.createBlock("icicles", () -> new IciclesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_ICE).noOcclusion()));
    public static final DeferredBlock<Block> ICICLE_BLOCK = BLOCKS.createBlock("icicle_block", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_ICE).strength(2f)));
    public static final DeferredBlock<Block> CHISELED_ICICLE_BLOCK = BLOCKS.createBlock("chiseled_icicle_block", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_ICE).strength(2f)));
    public static final DeferredBlock<Block> ICICLE_DOOR = BLOCKS.createBlock("icicle_door", () -> new DoorBlock(Properties.ICICLE_SET, Block.Properties.of().mapColor(MapColor.ICE).strength(3f).sound(SoundType.GLASS).noOcclusion()));
    public static final DeferredBlock<Block> ICICLE_TRAPDOOR = BLOCKS.createBlock("icicle_trapdoor", () -> new TrapDoorBlock(Properties.ICICLE_SET, Block.Properties.of().mapColor(MapColor.ICE).strength(3f).sound(SoundType.GLASS).noOcclusion().isValidSpawn(PropertyUtil::never)));
    public static final DeferredBlock<Block> ICICLE_BARS = BLOCKS.createBlock("icicle_bars", () -> new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_ICE).strength(2f)));
    public static final DeferredBlock<Block> ICE_LANTERN = BLOCKS.createBlock("ice_lantern", () -> new IceLanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).lightLevel(s -> 13)));
    public static final DeferredBlock<Block> ICE_CHAIN = BLOCKS.createBlock("ice_chain", () -> new ChainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHAIN)));

    public static final DeferredBlock<Block> SHALE = BLOCKS.createBlock("shale", () -> new Block(Properties.SHALE));
    public static final DeferredBlock<Block> SHALE_STAIRS = BLOCKS.createBlock("shale_stairs", () -> new StairBlock(SHALE.get().defaultBlockState(), Properties.SHALE));
    public static final DeferredBlock<Block> SHALE_SLAB = BLOCKS.createBlock("shale_slab", () -> new SlabBlock(Properties.SHALE));
    public static final DeferredBlock<Block> SHALE_WALL = BLOCKS.createBlock("shale_wall", () -> new WallBlock(Properties.SHALE));

    public static final DeferredBlock<Block> POLISHED_SHALE = BLOCKS.createBlock("polished_shale", () -> new Block(Properties.SHALE));
    public static final DeferredBlock<Block> POLISHED_SHALE_STAIRS = BLOCKS.createBlock("polished_shale_stairs", () -> new StairBlock(POLISHED_SHALE.get().defaultBlockState(), Properties.SHALE));
    public static final DeferredBlock<Block> POLISHED_SHALE_SLAB = BLOCKS.createBlock("polished_shale_slab", () -> new SlabBlock(Properties.SHALE));
    public static final DeferredBlock<Block> POLISHED_SHALE_WALL = BLOCKS.createBlock("polished_shale_wall", () -> new WallBlock(Properties.SHALE));

    public static final DeferredBlock<Block> POLISHED_SHALE_BRICKS = BLOCKS.createBlock("polished_shale_bricks", () -> new Block(Properties.SHALE));
    public static final DeferredBlock<Block> ICY_POLISHED_SHALE_BRICKS = BLOCKS.createBlock("icy_polished_shale_bricks", () -> new Block(Properties.SHALE));
    public static final DeferredBlock<Block> CHISELED_POLISHED_SHALE_BRICKS = BLOCKS.createBlock("chiseled_polished_shale_bricks", () -> new Block(Properties.SHALE));
    public static final DeferredBlock<Block> POLISHED_SHALE_BRICK_STAIRS = BLOCKS.createBlock("polished_shale_brick_stairs", () -> new StairBlock(POLISHED_SHALE_BRICKS.get().defaultBlockState(), Properties.SHALE));
    public static final DeferredBlock<Block> POLISHED_SHALE_BRICK_SLAB = BLOCKS.createBlock("polished_shale_brick_slab", () -> new SlabBlock(Properties.SHALE));
    public static final DeferredBlock<Block> POLISHED_SHALE_BRICK_WALL = BLOCKS.createBlock("polished_shale_brick_wall", () -> new WallBlock(Properties.SHALE));

    public static final DeferredBlock<Block> ARKOSE = BLOCKS.createBlock("arkose", () -> new Block(Properties.ARKOSE));
    public static final DeferredBlock<Block> ARKOSE_STAIRS = BLOCKS.createBlock("arkose_stairs", () -> new StairBlock(ARKOSE.get().defaultBlockState(), Properties.ARKOSE));
    public static final DeferredBlock<Block> ARKOSE_SLAB = BLOCKS.createBlock("arkose_slab", () -> new SlabBlock(Properties.ARKOSE));
    public static final DeferredBlock<Block> ARKOSE_WALL = BLOCKS.createBlock("arkose_wall", () -> new WallBlock(Properties.ARKOSE));

    public static final DeferredBlock<Block> POLISHED_ARKOSE = BLOCKS.createBlock("polished_arkose", () -> new Block(Properties.ARKOSE));
    public static final DeferredBlock<Block> POLISHED_ARKOSE_STAIRS = BLOCKS.createBlock("polished_arkose_stairs", () -> new StairBlock(POLISHED_ARKOSE.get().defaultBlockState(), Properties.ARKOSE));
    public static final DeferredBlock<Block> POLISHED_ARKOSE_SLAB = BLOCKS.createBlock("polished_arkose_slab", () -> new SlabBlock(Properties.ARKOSE));
    public static final DeferredBlock<Block> POLISHED_ARKOSE_WALL = BLOCKS.createBlock("polished_arkose_wall", () -> new WallBlock(Properties.ARKOSE));

    public static final DeferredBlock<Block> ARKOSE_BRICKS = BLOCKS.createBlock("arkose_bricks", () -> new Block(Properties.ARKOSE));
    public static final DeferredBlock<Block> ARKOSE_BRICK_STAIRS = BLOCKS.createBlock("arkose_brick_stairs", () -> new StairBlock(ARKOSE_BRICKS.get().defaultBlockState(), Properties.ARKOSE));
    public static final DeferredBlock<Block> ARKOSE_BRICK_SLAB = BLOCKS.createBlock("arkose_brick_slab", () -> new SlabBlock(Properties.ARKOSE));
    public static final DeferredBlock<Block> ARKOSE_BRICK_WALL = BLOCKS.createBlock("arkose_brick_wall", () -> new WallBlock(Properties.ARKOSE));

    public static final DeferredBlock<Block> ARKOSE_TILES = BLOCKS.createBlock("arkose_tiles", () -> new Block(Properties.ARKOSE));
    public static final DeferredBlock<Block> ARKOSE_TILE_STAIRS = BLOCKS.createBlock("arkose_tile_stairs", () -> new StairBlock(ARKOSE_TILES.get().defaultBlockState(), Properties.ARKOSE));
    public static final DeferredBlock<Block> ARKOSE_TILE_SLAB = BLOCKS.createBlock("arkose_tile_slab", () -> new SlabBlock(Properties.ARKOSE));
    public static final DeferredBlock<Block> ARKOSE_TILE_WALL = BLOCKS.createBlock("arkose_tile_wall", () -> new WallBlock(Properties.ARKOSE));

    public static final DeferredBlock<Block> ARKOSE_PILLAR = BLOCKS.createBlock("arkose_pillar", () -> new RotatedPillarBlock(Properties.ARKOSE));

    public static final DeferredBlock<Block> PACKED_ICE_STAIRS = BLOCKS.createBlock("packed_ice_stairs", () -> new StairBlock(Blocks.PACKED_ICE.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_ICE)));
    public static final DeferredBlock<Block> PACKED_ICE_SLAB = BLOCKS.createBlock("packed_ice_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_ICE)));

    public static final DeferredBlock<Block> PACKED_ICE_BRICKS = BLOCKS.createBlock("packed_ice_bricks", () -> new Block(Properties.ICE_BRICKS));
    public static final DeferredBlock<Block> CHISELED_PACKED_ICE_BRICKS = BLOCKS.createBlock("chiseled_packed_ice_bricks", () -> new Block(Properties.ICE_BRICKS));
    public static final DeferredBlock<Block> PACKED_ICE_BRICK_STAIRS = BLOCKS.createBlock("packed_ice_brick_stairs", () -> new StairBlock(PACKED_ICE_BRICKS.get().defaultBlockState(), Properties.ICE_BRICKS));
    public static final DeferredBlock<Block> PACKED_ICE_BRICK_SLAB = BLOCKS.createBlock("packed_ice_brick_slab", () -> new SlabBlock(Properties.ICE_BRICKS));
    public static final DeferredBlock<Block> PACKED_ICE_BRICK_WALL = BLOCKS.createBlock("packed_ice_brick_wall", () -> new WallBlock(Properties.ICE_BRICKS));

    public static final DeferredBlock<Block> BLUE_ICE_STAIRS = BLOCKS.createBlock("blue_ice_stairs", () -> new StairBlock(Blocks.BLUE_ICE.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_ICE)));
    public static final DeferredBlock<Block> BLUE_ICE_SLAB = BLOCKS.createBlock("blue_ice_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_ICE)));

    public static final DeferredBlock<Block> BLUE_ICE_BRICKS = BLOCKS.createBlock("blue_ice_bricks", () -> new Block(Properties.ICE_BRICKS));
    public static final DeferredBlock<Block> CHISELED_BLUE_ICE_BRICKS = BLOCKS.createBlock("chiseled_blue_ice_bricks", () -> new Block(Properties.ICE_BRICKS));
    public static final DeferredBlock<Block> BLUE_ICE_BRICK_STAIRS = BLOCKS.createBlock("blue_ice_brick_stairs", () -> new StairBlock(BLUE_ICE_BRICKS.get().defaultBlockState(), Properties.ICE_BRICKS));
    public static final DeferredBlock<Block> BLUE_ICE_BRICK_SLAB = BLOCKS.createBlock("blue_ice_brick_slab", () -> new SlabBlock(Properties.ICE_BRICKS));
    public static final DeferredBlock<Block> BLUE_ICE_BRICK_WALL = BLOCKS.createBlock("blue_ice_brick_wall", () -> new WallBlock(Properties.ICE_BRICKS));

    public static final DeferredBlock<Block> SNOW_STAIRS = BLOCKS.createBlock("snow_stairs", () -> new StairBlock(Blocks.SNOW_BLOCK.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK)));
    public static final DeferredBlock<Block> SNOW_SLAB = BLOCKS.createBlock("snow_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK)));

    public static final DeferredBlock<Block> SNOW_BRICKS = BLOCKS.createBlock("snow_bricks", () -> new Block(Properties.SNOW_BRICKS));
    public static final DeferredBlock<Block> SNOW_BRICK_STAIRS = BLOCKS.createBlock("snow_brick_stairs", () -> new StairBlock(SNOW_BRICKS.get().defaultBlockState(), Properties.SNOW_BRICKS));
    public static final DeferredBlock<Block> SNOW_BRICK_SLAB = BLOCKS.createBlock("snow_brick_slab", () -> new SlabBlock(Properties.SNOW_BRICKS));
    public static final DeferredBlock<Block> SNOW_BRICK_WALL = BLOCKS.createBlock("snow_brick_wall", () -> new WallBlock(Properties.SNOW_BRICKS));

    public static final DeferredBlock<Block> SUSPICIOUS_SNOW = BLOCKS.createBlock("suspicious_snow", () -> new SuspiciousSnowBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK).sound(WindsweptSoundTypes.SUSPICIOUS_SNOW)));

    public static final DeferredBlock<Block> CANDY_CANE_BLOCK = BLOCKS.createBlock("candy_cane_block", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_PILLAR)));

    public static final DeferredBlock<Block> GINGER_SOIL = BLOCKS.createBlock("ginger_soil", () -> new GingerSoilBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).strength(.5f).sound(SoundType.GRAVEL)));
    public static final DeferredBlock<Block> GINGER = BLOCKS.createBlockNoItem("ginger", () -> new GingerCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT)));

    public static final DeferredBlock<Block> GINGERBREAD_BLOCK = BLOCKS.createBlock("gingerbread_block", () -> new Block(Properties.GINGERBREAD));
    public static final DeferredBlock<Block> GLAZED_GINGERBREAD_BLOCK = BLOCKS.createBlock("glazed_gingerbread_block", () -> new Block(Properties.GINGERBREAD));
    public static final DeferredBlock<Block> GINGERBREAD_COOKIE_BLOCK = BLOCKS.createBlock("gingerbread_cookie_block", () -> new Block(Properties.GINGERBREAD));

    public static final DeferredBlock<Block> GINGERBREAD_DOOR = BLOCKS.createBlock("gingerbread_door", () -> new DoorBlock(Properties.GINGERBREAD_SET, Properties.PINE.door()));
    public static final DeferredBlock<Block> GINGERBREAD_TRAPDOOR = BLOCKS.createBlock("gingerbread_trapdoor", () -> new TrapDoorBlock(Properties.GINGERBREAD_SET, Properties.PINE.trapdoor()));

    public static final DeferredBlock<Block> GINGERBREAD_BRICKS = BLOCKS.createBlock("gingerbread_bricks", () -> new Block(Properties.GINGERBREAD));
    public static final DeferredBlock<Block> GINGERBREAD_BRICK_STAIRS = BLOCKS.createBlock("gingerbread_brick_stairs", () -> new StairBlock(GINGERBREAD_BRICKS.get().defaultBlockState(), Properties.GINGERBREAD));
    public static final DeferredBlock<Block> GINGERBREAD_BRICK_SLAB = BLOCKS.createBlock("gingerbread_brick_slab", () -> new SlabBlock(Properties.GINGERBREAD));
    public static final DeferredBlock<Block> GINGERBREAD_BRICK_WALL = BLOCKS.createBlock("gingerbread_brick_wall", () -> new WallBlock(Properties.GINGERBREAD));

    public static final DeferredBlock<Block> GLAZED_GINGERBREAD_BRICKS = BLOCKS.createBlock("glazed_gingerbread_bricks", () -> new Block(Properties.GINGERBREAD));
    public static final DeferredBlock<Block> GLAZED_GINGERBREAD_BRICK_STAIRS = BLOCKS.createBlock("glazed_gingerbread_brick_stairs", () -> new StairBlock(GLAZED_GINGERBREAD_BRICKS.get().defaultBlockState(), Properties.GINGERBREAD));
    public static final DeferredBlock<Block> GLAZED_GINGERBREAD_BRICK_SLAB = BLOCKS.createBlock("glazed_gingerbread_brick_slab", () -> new SlabBlock(Properties.GINGERBREAD));
    public static final DeferredBlock<Block> GLAZED_GINGERBREAD_BRICK_WALL = BLOCKS.createBlock("glazed_gingerbread_brick_wall", () -> new WallBlock(Properties.GINGERBREAD));

    public static final DeferredBlock<Block> LUNALITE = BLOCKS.createBlock("lunalite", () -> new LunaliteBlock(Properties.LUNALITE));
    public static final DeferredBlock<Block> LUNALITE_STAIRS = BLOCKS.createBlock("lunalite_stairs", () -> new StairBlock(LUNALITE.get().defaultBlockState(), Properties.LUNALITE));
    public static final DeferredBlock<Block> LUNALITE_SLAB = BLOCKS.createBlock("lunalite_slab", () -> new LunaliteSlabBlock(Properties.LUNALITE));
    public static final DeferredBlock<Block> LUNALITE_WALL = BLOCKS.createBlock("lunalite_wall", () -> new WallBlock(Properties.LUNALITE));

    public static final DeferredBlock<Block> CUT_LUNALITE = BLOCKS.createBlock("cut_lunalite", () -> new Block(Properties.LUNALITE));
    public static final DeferredBlock<Block> CUT_LUNALITE_STAIRS = BLOCKS.createBlock("cut_lunalite_stairs", () -> new StairBlock(CUT_LUNALITE.get().defaultBlockState(), Properties.LUNALITE));
    public static final DeferredBlock<Block> CUT_LUNALITE_SLAB = BLOCKS.createBlock("cut_lunalite_slab", () -> new SlabBlock(Properties.LUNALITE));
    public static final DeferredBlock<Block> CUT_LUNALITE_WALL = BLOCKS.createBlock("cut_lunalite_wall", () -> new WallBlock(Properties.LUNALITE));

    public static final DeferredBlock<Block> CUT_LUNALITE_BRICKS = BLOCKS.createBlock("cut_lunalite_bricks", () -> new Block(Properties.LUNALITE));
    public static final DeferredBlock<Block> CHISELED_CUT_LUNALITE_BRICKS = BLOCKS.createBlock("chiseled_cut_lunalite_bricks", () -> new Block(Properties.LUNALITE));
    public static final DeferredBlock<Block> CUT_LUNALITE_BRICK_STAIRS = BLOCKS.createBlock("cut_lunalite_brick_stairs", () -> new StairBlock(CUT_LUNALITE_BRICKS.get().defaultBlockState(), Properties.LUNALITE));
    public static final DeferredBlock<Block> CUT_LUNALITE_BRICK_SLAB = BLOCKS.createBlock("cut_lunalite_brick_slab", () -> new SlabBlock(Properties.LUNALITE));
    public static final DeferredBlock<Block> CUT_LUNALITE_BRICK_WALL = BLOCKS.createBlock("cut_lunalite_brick_wall", () -> new WallBlock(Properties.LUNALITE));

    public static final DeferredBlock<Block> SMOOTH_LUNALITE = BLOCKS.createBlock("smooth_lunalite", () -> new Block(Properties.LUNALITE));
    public static final DeferredBlock<Block> SMOOTH_LUNALITE_STAIRS = BLOCKS.createBlock("smooth_lunalite_stairs", () -> new StairBlock(SMOOTH_LUNALITE.get().defaultBlockState(), Properties.LUNALITE));
    public static final DeferredBlock<Block> SMOOTH_LUNALITE_SLAB = BLOCKS.createBlock("smooth_lunalite_slab", () -> new SlabBlock(Properties.LUNALITE));

    public static final DeferredBlock<Block> DRY_MOSSY_COBBLESTONE = BLOCKS.createBlock("dry_mossy_cobblestone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_COBBLESTONE)));
    public static final DeferredBlock<Block> DRY_MOSSY_COBBLESTONE_STAIRS = BLOCKS.createBlock("dry_mossy_cobblestone_stairs", () -> new StairBlock(DRY_MOSSY_COBBLESTONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_COBBLESTONE)));
    public static final DeferredBlock<Block> DRY_MOSSY_COBBLESTONE_SLAB = BLOCKS.createBlock("dry_mossy_cobblestone_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_COBBLESTONE)));
    public static final DeferredBlock<Block> DRY_MOSSY_COBBLESTONE_WALL = BLOCKS.createBlock("dry_mossy_cobblestone_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_COBBLESTONE)));

    public static final DeferredBlock<Block> DRY_MOSSY_STONE_BRICKS = BLOCKS.createBlock("dry_mossy_stone_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_STONE_BRICKS)));
    public static final DeferredBlock<Block> DRY_MOSSY_STONE_BRICK_STAIRS = BLOCKS.createBlock("dry_mossy_stone_brick_stairs", () -> new StairBlock(DRY_MOSSY_STONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_STONE_BRICKS)));
    public static final DeferredBlock<Block> DRY_MOSSY_STONE_BRICK_SLAB = BLOCKS.createBlock("dry_mossy_stone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_STONE_BRICKS)));
    public static final DeferredBlock<Block> DRY_MOSSY_STONE_BRICK_WALL = BLOCKS.createBlock("dry_mossy_stone_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_STONE_BRICKS)));

    public static final DeferredBlock<Block> DRY_MOSSY_COBBLESTONE_BRICKS = BLOCKS.createBlock("dry_mossy_cobblestone_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_STONE_BRICKS)));
    public static final DeferredBlock<Block> DRY_MOSSY_COBBLESTONE_BRICK_STAIRS = BLOCKS.createBlock("dry_mossy_cobblestone_brick_stairs", () -> new StairBlock(DRY_MOSSY_COBBLESTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_STONE_BRICKS)));
    public static final DeferredBlock<Block> DRY_MOSSY_COBBLESTONE_BRICK_SLAB = BLOCKS.createBlock("dry_mossy_cobblestone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_STONE_BRICKS)));
    public static final DeferredBlock<Block> DRY_MOSSY_COBBLESTONE_BRICK_WALL = BLOCKS.createBlock("dry_mossy_cobblestone_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_STONE_BRICKS)));

    public static final DeferredBlock<Block> DRY_MOSSY_COBBLESTONE_TILES = BLOCKS.createBlock("dry_mossy_cobblestone_tiles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_STONE_BRICKS)));
    public static final DeferredBlock<Block> DRY_MOSSY_COBBLESTONE_TILE_STAIRS = BLOCKS.createBlock("dry_mossy_cobblestone_tile_stairs", () -> new StairBlock(DRY_MOSSY_COBBLESTONE_TILES.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_STONE_BRICKS)));
    public static final DeferredBlock<Block> DRY_MOSSY_COBBLESTONE_TILE_SLAB = BLOCKS.createBlock("dry_mossy_cobblestone_tile_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_STONE_BRICKS)));
    public static final DeferredBlock<Block> DRY_MOSSY_COBBLESTONE_TILE_WALL = BLOCKS.createBlock("dry_mossy_cobblestone_tile_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_STONE_BRICKS)));

    public static final DeferredBlock<Block> CHESTNUT_CRATE = BLOCKS.createBlock("chestnut_crate", () -> new WindsweptCompressedBlock(Properties.CRATE));
    public static final DeferredBlock<Block> ROASTED_CHESTNUT_CRATE = BLOCKS.createBlock("roasted_chestnut_crate", () -> new WindsweptCompressedBlock(Properties.CRATE));
    public static final DeferredBlock<Block> GINGER_ROOT_CRATE = BLOCKS.createBlock("ginger_root_crate", () -> new WindsweptCompressedBlock(Properties.CRATE));
    public static final DeferredBlock<Block> HOLLY_BERRY_BASKET = BLOCKS.createBlock("holly_berry_basket", () -> new WindsweptCompressedBlock(Properties.HOLLY_BERRY_BASKET));
    public static final DeferredBlock<Block> WILD_BERRY_BASKET = BLOCKS.createBlock("wild_berry_basket", () -> new WindsweptCompressedBlock(Properties.CRATE));
    public static final DeferredBlock<Block> FROZEN_FLESH_BLOCK = BLOCKS.createBlock("frozen_flesh_block", () -> new Block(BlockBehaviour.Properties.of().strength(.8f).sound(SoundType.CORAL_BLOCK)));

    public static final DeferredBlock<Block> POTTED_RED_ROSE = BLOCKS.createBlockNoItem("potted_red_rose", () -> new FlowerPotBlock(RED_ROSE.get(), PropertyUtil.flowerPot()));
    public static final DeferredBlock<Block> POTTED_BLUE_ROSE = BLOCKS.createBlockNoItem("potted_blue_rose", () -> new FlowerPotBlock(BLUE_ROSE.get(), PropertyUtil.flowerPot()));
    public static final DeferredBlock<Block> POTTED_WHITE_ROSE = BLOCKS.createBlockNoItem("potted_white_rose", () -> new FlowerPotBlock(WHITE_ROSE.get(), PropertyUtil.flowerPot()));
    public static final DeferredBlock<Block> POTTED_YELLOW_ROSE = BLOCKS.createBlockNoItem("potted_yellow_rose", () -> new FlowerPotBlock(YELLOW_ROSE.get(), PropertyUtil.flowerPot()));
    public static final DeferredBlock<Block> POTTED_FOXGLOVE = BLOCKS.createBlockNoItem("potted_foxglove", () -> new FlowerPotBlock(FOXGLOVE.get(), PropertyUtil.flowerPot()));
    public static final DeferredBlock<Block> POTTED_BLUEBELLS = BLOCKS.createBlockNoItem("potted_bluebells", () -> new FlowerPotBlock(BLUEBELLS.get(), PropertyUtil.flowerPot()));
    public static final DeferredBlock<Block> POTTED_SNOWDROP = BLOCKS.createBlockNoItem("potted_snowdrop", () -> new FlowerPotBlock(SNOWDROP.get(), PropertyUtil.flowerPot()));
    public static final DeferredBlock<Block> POTTED_MOSS_CAMPION = BLOCKS.createBlockNoItem("potted_moss_campion", () -> new FlowerPotBlock(MOSS_CAMPION.get(), PropertyUtil.flowerPot()));
    public static final DeferredBlock<Block> POTTED_WILD_GINGER = BLOCKS.createBlockNoItem("potted_wild_ginger", () -> new FlowerPotBlock(WILD_GINGER.get(), PropertyUtil.flowerPot()));
    public static final DeferredBlock<Block> POTTED_NIGHTSHADE = BLOCKS.createBlockNoItem("potted_nightshade", () -> new NightShadeFlowerPotBlock(NIGHTSHADE.get(), BlockBehaviour.Properties.of().instabreak().noOcclusion().lightLevel(state -> 9)));
    public static final DeferredBlock<Block> POTTED_SNOWY_SPROUTS = BLOCKS.createBlockNoItem("potted_snowy_sprouts", () -> new FlowerPotBlock(SNOWY_SPROUTS.get(), PropertyUtil.flowerPot()));
    public static final DeferredBlock<Block> POTTED_GELISOL_GRASS = BLOCKS.createBlockNoItem("potted_gelisol_grass", () -> new FlowerPotBlock(GELISOL_GRASS.get(), PropertyUtil.flowerPot()));
    public static final DeferredBlock<Block> POTTED_DRY_MOSSY_SPROUTS = BLOCKS.createBlockNoItem("potted_dry_mossy_sprouts", () -> new FlowerPotBlock(DRY_MOSSY_SPROUTS.get(), PropertyUtil.flowerPot()));
    public static final DeferredBlock<Block> POTTED_MOSSY_SPROUTS = BLOCKS.createBlockNoItem("potted_mossy_sprouts", () -> new FlowerPotBlock(MOSSY_SPROUTS.get(), PropertyUtil.flowerPot()));
    public static final DeferredBlock<Block> POTTED_LAVENDER = BLOCKS.createBlockNoItem("potted_lavender", () -> new FlowerPotBlock(LAVENDER.get(), PropertyUtil.flowerPot()));
    public static final DeferredBlock<Block> POTTED_MIMOSA = BLOCKS.createBlockNoItem("potted_mimosa", () -> new FlowerPotBlock(MIMOSA.get(), PropertyUtil.flowerPot()));

    public static final DeferredBlock<Block> WILD_BERRY_BUSH = BLOCKS.createBlockNoItem("wild_berry_bush", () -> new WildBerryBushBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH).offsetType(BlockBehaviour.OffsetType.XZ)));

    public static class Properties {
        public static final BlockSetType HOLLY_BLOCK_SET = BlockSetTypeRegistryHelper.register(new BlockSetType(Windswept.MOD_ID + ":holly"));
        public static final BlockSetType CHESTNUT_BLOCK_SET = BlockSetTypeRegistryHelper.register(new BlockSetType(Windswept.MOD_ID + ":chestnut"));
        public static final BlockSetType PINE_BLOCK_SET = BlockSetTypeRegistryHelper.register(new BlockSetType(Windswept.MOD_ID + ":pine"));
        public static final BlockSetType ICICLE_SET = BlockSetTypeRegistryHelper.register(new BlockSetType(Windswept.MOD_ID + ":icicles", true, true, true, BlockSetType.PressurePlateSensitivity.EVERYTHING, SoundType.GLASS, SoundEvents.CHERRY_WOOD_DOOR_CLOSE, SoundEvents.CHERRY_WOOD_DOOR_OPEN, SoundEvents.CHERRY_WOOD_TRAPDOOR_CLOSE, SoundEvents.CHERRY_WOOD_TRAPDOOR_OPEN, SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON));
        public static final BlockSetType GINGERBREAD_SET = BlockSetTypeRegistryHelper.register(new BlockSetType(Windswept.MOD_ID + ":gingerbread"));

        public static final WoodType HOLLY_WOOD_TYPE = WoodTypeRegistryHelper.registerWoodType(new WoodType(Windswept.MOD_ID + ":holly", HOLLY_BLOCK_SET));
        public static final WoodType CHESTNUT_WOOD_TYPE = WoodTypeRegistryHelper.registerWoodType(new WoodType(Windswept.MOD_ID + ":chestnut", CHESTNUT_BLOCK_SET));
        public static final WoodType PINE_WOOD_TYPE = WoodTypeRegistryHelper.registerWoodType(new WoodType(Windswept.MOD_ID + ":pine", PINE_BLOCK_SET));

        public static final PropertyUtil.WoodSetProperties HOLLY = PropertyUtil.WoodSetProperties.builder(MapColor.COLOR_PURPLE).sound(SoundType.CHERRY_WOOD).logSound(SoundType.CHERRY_WOOD).build();
        public static final PropertyUtil.WoodSetProperties CHESTNUT = PropertyUtil.WoodSetProperties.builder(MapColor.COLOR_BROWN).build();
        public static final PropertyUtil.WoodSetProperties PINE = PropertyUtil.WoodSetProperties.builder(MapColor.TERRACOTTA_BROWN).build();

        public static final BlockBehaviour.Properties SNOW_BRICKS = BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).strength(.85f).sound(SoundType.SNOW);
        public static final BlockBehaviour.Properties ICE_BRICKS = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).requiresCorrectToolForDrops().strength(2.8f).sound(SoundType.STONE);
        public static final BlockBehaviour.Properties CRATE = BlockBehaviour.Properties.of().strength(1.5f).sound(SoundType.WOOD);
        public static final BlockBehaviour.Properties HOLLY_BERRY_BASKET = BlockBehaviour.Properties.of().strength(1.5f).sound(SoundType.CHERRY_WOOD);
        public static final BlockBehaviour.Properties SPROUTS = BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.NETHER_SPROUTS).offsetType(BlockBehaviour.OffsetType.XZ).replaceable();
        public static final BlockBehaviour.Properties PINECONE_BLOCK = Block.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(2f, 3f).sound(WindsweptSoundTypes.PINECONE);
        public static final BlockBehaviour.Properties SHALE = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).sound(SoundType.DRIPSTONE_BLOCK).requiresCorrectToolForDrops().strength(1.5f, 1f);
        public static final BlockBehaviour.Properties ARKOSE = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).sound(SoundType.DRIPSTONE_BLOCK).requiresCorrectToolForDrops().strength(1.5f, 1f);
        public static final BlockBehaviour.Properties LUNALITE = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).sound(SoundType.CALCITE).requiresCorrectToolForDrops().strength(.8f);
        public static final BlockBehaviour.Properties GINGERBREAD = Block.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).strength(2f, 3f).sound(SoundType.CHERRY_WOOD);
        public static final BlockBehaviour.Properties ELDER_ORNAMENT = Block.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(.1f).noOcclusion().noCollission().sound(SoundType.AZALEA);
        public static final BlockBehaviour.Properties LAVENDER_THATCH = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(.5f).sound(SoundType.NETHER_SPROUTS).noOcclusion();
    }
}