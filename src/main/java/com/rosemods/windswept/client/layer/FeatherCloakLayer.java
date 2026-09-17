package com.rosemods.windswept.client.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
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

public class FeatherCloakLayer<T extends LivingEntity, M extends HumanoidModel<T>> extends RenderLayer<T, M> {
    private static final ResourceLocation TEXTURE = Windswept.location("textures/models/armor/feather_cloak_layer_1.png");

    private final HumanoidModel<T> cloakModel;

    public FeatherCloakLayer(RenderLayerParent<T, M> parent, HumanoidModel<T> cloakModel) {
        super(parent);
        this.cloakModel = cloakModel;
    }

    @Override
    public void render(PoseStack pose, MultiBufferSource source, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entity.getItemBySlot(EquipmentSlot.CHEST).is(WindsweptItems.FEATHER_CLOAK.get())) return;
        if (entity.isInvisible()) return;

        this.getParentModel().copyPropertiesTo(this.cloakModel);
        this.cloakModel.setAllVisible(false);
        this.cloakModel.body.visible = true;
        this.cloakModel.leftArm.visible = true;
        this.cloakModel.rightArm.visible = true;
        this.cloakModel.leftLeg.visible = true;
        this.cloakModel.rightLeg.visible = true;

        this.cloakModel.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
        this.cloakModel.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        VertexConsumer vertexConsumer = source.getBuffer(RenderType.entityCutoutNoCull(TEXTURE));
        this.cloakModel.renderToBuffer(pose, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY);
    }

}