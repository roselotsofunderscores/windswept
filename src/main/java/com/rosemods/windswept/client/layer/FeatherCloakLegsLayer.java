package com.rosemods.windswept.client.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.logging.LogUtils;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.WindsweptItems;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import org.slf4j.Logger;

public class FeatherCloakLegsLayer<T extends LivingEntity, M extends HumanoidModel<T>> extends RenderLayer<T, M> {

    private static final Logger WINDSWEPT_LOG = LogUtils.getLogger();

    private static final ResourceLocation TEXTURE =
            Windswept.location("textures/models/armor/feather_cloak_layer_1.png");

    private final HumanoidModel<T> legsModel;

    public FeatherCloakLegsLayer(RenderLayerParent<T, M> parent, HumanoidModel<T> legsModel) {
        super(parent);
        this.legsModel = legsModel;
    }

    @Override
    public void render(PoseStack pose, MultiBufferSource source, int packedLight, T entity,
                       float limbSwing, float limbSwingAmount, float partialTicks,
                       float ageInTicks, float netHeadYaw, float headPitch) {

        WINDSWEPT_LOG.info("[Windswept-DEBUG] FeatherCloakLegsLayer.render() called, chest = {}",
                entity.getItemBySlot(EquipmentSlot.CHEST).getItem());

        if (!entity.getItemBySlot(EquipmentSlot.CHEST).is(WindsweptItems.FEATHER_CLOAK.get())) {
            return;
        }

        if (entity.isInvisible()) {
            return;
        }

        this.getParentModel().copyPropertiesTo(this.legsModel);
        this.legsModel.setAllVisible(false);
        this.legsModel.leftLeg.visible = true;
        this.legsModel.rightLeg.visible = true;

        this.legsModel.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
        this.legsModel.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        VertexConsumer vertexConsumer = source.getBuffer(RenderType.entityCutoutNoCull(TEXTURE));
        this.legsModel.renderToBuffer(pose, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY);
    }
}