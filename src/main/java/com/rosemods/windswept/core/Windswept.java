package com.rosemods.windswept.core;

import com.rosemods.windswept.client.particle.AcaciaLeavesParticle;
import com.rosemods.windswept.client.particle.FrostLeafParticle;
import com.rosemods.windswept.client.particle.WillOTheWispParticle;
import com.rosemods.windswept.client.render.entity.ChilledRenderer;
import com.rosemods.windswept.client.render.entity.FrostArrowRenderer;
import com.rosemods.windswept.client.render.entity.FrostbiterRenderer;
import com.rosemods.windswept.client.render.gui.CarvedPineconeOverlay;
import com.rosemods.windswept.common.entity.Chilled;
import com.rosemods.windswept.common.entity.Frostbiter;
import com.rosemods.windswept.core.data.client.*;
import com.rosemods.windswept.core.data.server.WindsweptDatapackProvider;
import com.rosemods.windswept.core.data.server.WindsweptLootTableProvider;
import com.rosemods.windswept.core.data.server.WindsweptRecipeProvider;
import com.rosemods.windswept.core.data.server.modifiers.WindsweptAdvancementModifierProvider;
import com.rosemods.windswept.core.data.server.modifiers.WindsweptChunkGeneratorModifierProvider;
import com.rosemods.windswept.core.data.server.modifiers.WindsweptLootModifierProvider;
import com.rosemods.windswept.core.data.server.tags.*;
import com.rosemods.windswept.core.other.*;
import com.rosemods.windswept.core.registry.*;
import com.rosemods.windswept.core.registry.util.EffectSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.client.particle.EndRodParticle;
import net.minecraft.client.renderer.blockentity.BrushableBlockRenderer;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

@Mod(Windswept.MOD_ID)
public class Windswept {
    public static final String MOD_ID = "windswept";
    public static final RegistryHelper REGISTRY_HELPER = RegistryHelper.create(MOD_ID, h -> h.putSubHelper(Registries.MOB_EFFECT, new EffectSubRegistryHelper(h)));

    public Windswept(IEventBus bus, ModContainer container) {
        WindsweptDataProcessors.registerData();

        WindsweptBlocks.BLOCKS.register(bus);
        WindsweptItems.ITEMS.register(bus);
        WindsweptEntityTypes.ENTITIES.register(bus);
        WindsweptBlockEntities.BLOCK_ENTITIES.register(bus);
        WindsweptEffects.EFFECTS.register(bus);
        WindsweptSounds.SOUNDS.register(bus);
        WindsweptTreeDecorators.DECORATORS.register(bus);
        WindsweptFoliagePlacers.FOLIAGE_PLACERS.register(bus);
        WindsweptFeatures.FEATURES.register(bus);
        WindsweptAttributes.ATTRIBUTES.register(bus);
        WindsweptTrunkPlacers.TRUNK_PLACERS.register(bus);
        WindsweptParticleTypes.PARTICLE_TYPES.register(bus);
        WindsweptPotPatterns.DECORATED_POT_PATTERNS.register(bus);
        WindsweptVillagerTypes.VILLAGER_TYPES.register(bus);
        WindsweptArmorMaterials.ARMOR_MATERIALS.register(bus);

        bus.addListener(this::commonSetup);
        bus.addListener(this::registerEntityAttributes);
        bus.addListener(this::registerEntitySpawns);
        bus.addListener(this::dataSetup);

        if (FMLEnvironment.dist == Dist.CLIENT) {
            WindsweptCreativeTabs.setupTabEditors();
            bus.addListener(this::registerLayerDefinitions);
            bus.addListener(this::registerEntityRenderers);
            bus.addListener(this::registerSpriteSets);
            bus.addListener(this::registerGuiOverlays);
        }

        container.registerConfig(ModConfig.Type.COMMON, WindsweptConfig.COMMON_SPEC);
        container.registerConfig(ModConfig.Type.CLIENT, WindsweptConfig.CLIENT_SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            DispenserBlock.registerProjectileBehavior(WindsweptItems.FROST_ARROW);
            WindsweptVillagerTypes.registerVillagerBiomes();
            WindsweptBlockInfo.registerBlockColors();
            WindsweptBlockInfo.changeLocalisation();
            WindsweptBlockInfo.registerCompostables();
            WindsweptBlockInfo.registerFlammables();
            WindsweptEffects.registerPotionRecipes();
            WindsweptDispenseBehaviors.registerDispenseBehaviors();
            WindsweptCauldronInteractions.registerCauldronInteractions();
            WindsweptPotPatterns.registerPatterns();
        });
    }

    private void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(WindsweptEntityTypes.CHILLED.get(), Chilled.createChilledAttributes().build());
        event.put(WindsweptEntityTypes.FROSTBITER.get(), Frostbiter.createFrostbiterAttributes().build());
    }

    private void registerEntitySpawns(RegisterSpawnPlacementsEvent event) {
        event.register(WindsweptEntityTypes.CHILLED.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND);
        event.register(WindsweptEntityTypes.FROSTBITER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Frostbiter::checkFrostbiterSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND);
    }

    private void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(WindsweptModelLayers.CHILLED, WindsweptModelLayers::createChilledBodyLayer);
        event.registerLayerDefinition(WindsweptModelLayers.FROSTBITER, WindsweptModelLayers::createFrostbiterBodyLayer);
    }

    private void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(WindsweptEntityTypes.CHILLED.get(), ChilledRenderer::new);
        event.registerEntityRenderer(WindsweptEntityTypes.FROSTBITER.get(), FrostbiterRenderer::new);
        event.registerEntityRenderer(WindsweptEntityTypes.FROST_ARROW.get(), FrostArrowRenderer::new);
        event.registerBlockEntityRenderer(WindsweptBlockEntities.SUSPICIOUS_SNOW.get(), BrushableBlockRenderer::new);
    }

    private void registerSpriteSets(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(WindsweptParticleTypes.WILL_O_THE_WISP.get(), WillOTheWispParticle.Provider::new);
        event.registerSpriteSet(WindsweptParticleTypes.FROST_LEAF.get(), FrostLeafParticle.Provider::new);
        event.registerSpriteSet(WindsweptParticleTypes.FEATHER_CLOAK.get(), EndRodParticle.Provider::new);
        event.registerSpriteSet(WindsweptParticleTypes.ACACIA_LEAVES.get(), AcaciaLeavesParticle.Provider::new);
    }

    private void registerGuiOverlays(RegisterGuiLayersEvent event) {
        event.registerAbove(VanillaGuiLayers.EFFECTS, location("carved_pinecone"), new CarvedPineconeOverlay());
    }

    private void dataSetup(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        boolean client = event.includeClient();
        boolean server = event.includeServer();

        gen.addProvider(client, new WindsweptSoundProvider(event));
        gen.addProvider(client, new WindsweptLangProvider(event));
        gen.addProvider(client, new WindsweptModelProvider(event));
        gen.addProvider(client, new WindsweptSplashProvider(event));
        gen.addProvider(client, new WindsweptParticleProvider(event));
        gen.addProvider(client, new WindsweptSpriteSourceProvider(event));
        //gen.addProvider(client, new GalleryAssetsRemolderProvider(MOD_ID, event.getGenerator().getPackOutput(), event.getLookupProvider()));

        WindsweptDatapackProvider dataPack;
        WindsweptBlockTagProvider blockTags;
        gen.addProvider(server, dataPack = new WindsweptDatapackProvider(event));
        gen.addProvider(server, blockTags = new WindsweptBlockTagProvider(event, dataPack));
        gen.addProvider(server, new WindsweptItemTagProvider(event, blockTags, dataPack));
        gen.addProvider(server, new WindsweptEnchantmentTagsProvider(event, blockTags, dataPack));
        gen.addProvider(server, new WindsweptEntityTagProvider(event, dataPack));
        gen.addProvider(server, new WindsweptBiomeTagProvider(event, dataPack));
        gen.addProvider(server, new WindsweptStructureTagsProvider(event, dataPack));
        gen.addProvider(server, new WindsweptTrimMaterialTagsProvider(event, dataPack));
        gen.addProvider(server, new WindsweptBannerPatternTagProvider(event, dataPack));
        gen.addProvider(server, new WindsweptLootTableProvider(event));
        gen.addProvider(server, new WindsweptRecipeProvider(event));
        gen.addProvider(server, new WindsweptAdvancementModifierProvider(event, dataPack));
        //gen.addProvider(server, new WindsweptLootModifierProvider(event, dataPack));
        gen.addProvider(server, new WindsweptPaintingVariantTagsProvider(event, dataPack));
        gen.addProvider(server, new WindsweptChunkGeneratorModifierProvider(event, dataPack));
    }

    public static ResourceLocation location(String id) {
        return ResourceLocation.tryBuild(MOD_ID, id);
    }

}
