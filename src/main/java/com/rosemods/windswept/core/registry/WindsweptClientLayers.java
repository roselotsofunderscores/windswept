package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.client.layer.FeatherCloakLegsLayer;
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
        MeshDefinition mesh = HumanoidArmorModel.createBodyLayer(new CubeDeformation(0.25F));
        LayerDefinition layerDefinition = LayerDefinition.create(mesh, 64, 32);
        event.registerLayerDefinition(WindsweptModelLayers.FEATHER_CLOAK_LEGS, () -> layerDefinition);
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

            playerRenderer.addLayer(new FeatherCloakLegsLayer<>(playerRenderer, legsModel));
        }
    }
}