package com.rosemods.windswept.core.other;

import com.rosemods.windswept.client.layer.FeatherCloakLayer;
import com.rosemods.windswept.client.layer.WoodenBucketHeadLayer;
import com.rosemods.windswept.client.particle.AcaciaLeavesParticle;
import com.rosemods.windswept.client.particle.FrostLeafParticle;
import com.rosemods.windswept.client.particle.WillOTheWispParticle;
import com.rosemods.windswept.client.render.entity.ChilledRenderer;
import com.rosemods.windswept.client.render.entity.FrostArrowRenderer;
import com.rosemods.windswept.client.render.entity.FrostbiterRenderer;
import com.rosemods.windswept.client.render.gui.CarvedPineconeOverlay;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.WindsweptBlockEntities;
import com.rosemods.windswept.core.registry.WindsweptEntityTypes;
import com.rosemods.windswept.core.registry.WindsweptItems;
import com.rosemods.windswept.core.registry.WindsweptParticleTypes;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.particle.EndRodParticle;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.blockentity.BrushableBlockRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

import static com.rosemods.windswept.core.registry.WindsweptBlocks.*;

@EventBusSubscriber(modid = Windswept.MOD_ID, value = Dist.CLIENT)
public class WindsweptClientCompat {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(WindsweptCreativeTabs::setupTabEditors);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(WindsweptModelLayers.CHILLED, WindsweptModelLayers::createChilledBodyLayer);
        event.registerLayerDefinition(WindsweptModelLayers.FROSTBITER, WindsweptModelLayers::createFrostbiterBodyLayer);
        event.registerLayerDefinition(WindsweptModelLayers.FEATHER_CLOAK, WindsweptModelLayers::createFeatherCloakLayer);
        event.registerLayerDefinition(WindsweptModelLayers.WOODEN_BUCKET_HEAD, WindsweptModelLayers::createWoodenBucketHelmetLayer);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(WindsweptEntityTypes.CHILLED.get(), ChilledRenderer::new);
        event.registerEntityRenderer(WindsweptEntityTypes.FROSTBITER.get(), FrostbiterRenderer::new);
        event.registerEntityRenderer(WindsweptEntityTypes.FROST_ARROW.get(), FrostArrowRenderer::new);
        event.registerBlockEntityRenderer(WindsweptBlockEntities.SUSPICIOUS_SNOW.get(), BrushableBlockRenderer::new);
    }

    @SubscribeEvent
    public static void registerArmourLayers(EntityRenderersEvent.AddLayers event) {
        for (PlayerSkin.Model skin : event.getSkins()) {
            PlayerRenderer renderer = event.getSkin(skin);

            if (renderer != null) {
                renderer.addLayer(new FeatherCloakLayer<>(renderer, new HumanoidModel<>(event.getEntityModels().bakeLayer(WindsweptModelLayers.FEATHER_CLOAK))));
                renderer.addLayer(new WoodenBucketHeadLayer<>(renderer, new HumanoidModel<>(event.getEntityModels().bakeLayer(WindsweptModelLayers.WOODEN_BUCKET_HEAD))));
            }
        }
    }

    @SubscribeEvent
    public static void registerSpriteSets(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(WindsweptParticleTypes.WILL_O_THE_WISP.get(), WillOTheWispParticle.Provider::new);
        event.registerSpriteSet(WindsweptParticleTypes.FROST_LEAF.get(), FrostLeafParticle.Provider::new);
        event.registerSpriteSet(WindsweptParticleTypes.FEATHER_CLOAK.get(), EndRodParticle.Provider::new);
        event.registerSpriteSet(WindsweptParticleTypes.ACACIA_LEAVES.get(), AcaciaLeavesParticle.Provider::new);
    }

    @SubscribeEvent
    public static void registerGuiOverlays(RegisterGuiLayersEvent event) {
        event.registerAbove(VanillaGuiLayers.EFFECTS, Windswept.location("carved_pinecone"), new CarvedPineconeOverlay());
    }

    @SubscribeEvent
    public static void registerItemColours(RegisterColorHandlersEvent.Item event) {
        Block[] foliage = new Block[]{CHESTNUT_LEAVES.get(), CHESTNUT_LEAF_PILE.get(), FLOWERING_ACACIA_LEAVES.get(), FLOWERING_ACACIA_LEAF_PILE.get()};

        event.register((stack, color) -> color > 0 ? -1 : DyedItemColor.getOrDefault(stack, DyedItemColor.LEATHER_COLOR), WindsweptItems.SNOW_BOOTS.get());
        event.register((stack, tintIndex) -> event.getBlockColors().getColor(((BlockItem) stack.getItem()).getBlock().defaultBlockState(), null, null, tintIndex), foliage);
    }

    @SubscribeEvent
    public static void registerBlockColours(RegisterColorHandlersEvent.Block event) {
        Block[] foliage = new Block[]{CHESTNUT_LEAVES.get(), CHESTNUT_LEAF_PILE.get(), FLOWERING_ACACIA_LEAVES.get(), FLOWERING_ACACIA_LEAF_PILE.get()};

        event.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getAverageFoliageColor(world, pos) : FoliageColor.getDefaultColor(), foliage);
        event.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.getDefaultColor(), YELLOW_PETALS.get());
    }
}
