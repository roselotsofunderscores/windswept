package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.client.layer.FeatherCloakLayer;
import com.rosemods.windswept.client.layer.WoodenBucketHeadLayer;
import com.rosemods.windswept.client.layer.WindsweptModelLayers;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.resources.PlayerSkin;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class WindsweptClientLayers {

    @SubscribeEvent
    public void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        MeshDefinition legsMesh = HumanoidArmorModel.createBodyLayer(new CubeDeformation(0.25F));
        event.registerLayerDefinition(WindsweptModelLayers.FEATHER_CLOAK_LEGS,
                () -> LayerDefinition.create(legsMesh, 64, 32));

        MeshDefinition headMesh = HumanoidModel.createMesh(new CubeDeformation(0.5F), 0.0F);
        event.registerLayerDefinition(WindsweptModelLayers.WOODEN_BUCKET_HEAD,
                () -> LayerDefinition.create(headMesh, 64, 32));
    }

    @SubscribeEvent
    public void addLayers(EntityRenderersEvent.AddLayers event) {
        for (PlayerSkin.Model skin : event.getSkins()) {
            PlayerRenderer playerRenderer = event.getSkin(skin);
            if (playerRenderer == null) {
                continue;
            }

            HumanoidModel<AbstractClientPlayer> legsModel =
                    new HumanoidModel<>(event.getEntityModels().bakeLayer(WindsweptModelLayers.FEATHER_CLOAK_LEGS));
            playerRenderer.addLayer(new FeatherCloakLayer<>(playerRenderer, legsModel));

            HumanoidModel<AbstractClientPlayer> headModel =
                    new HumanoidModel<>(event.getEntityModels().bakeLayer(WindsweptModelLayers.WOODEN_BUCKET_HEAD));
            playerRenderer.addLayer(new WoodenBucketHeadLayer<>(playerRenderer, headModel));
        }
    }
}