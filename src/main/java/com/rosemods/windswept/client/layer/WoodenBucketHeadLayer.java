package com.rosemods.windswept.client.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.WindsweptItems;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

public class WoodenBucketHeadLayer<T extends LivingEntity, M extends HumanoidModel<T>> extends RenderLayer<T, M> {
    private static final ResourceLocation TEXTURE = Windswept.location("textures/models/armor/wooden_bucket_layer_1.png");

    private final HumanoidModel<T> headModel;

    public WoodenBucketHeadLayer(RenderLayerParent<T, M> parent, HumanoidModel<T> headModel) {
        super(parent);
        this.headModel = headModel;
    }

    @Override
    public void render(PoseStack pose, MultiBufferSource source, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entity.getItemBySlot(EquipmentSlot.HEAD).is(WindsweptItems.WOODEN_BUCKET.get())) return;
        if (entity.isInvisible()) return;

        this.getParentModel().copyPropertiesTo(this.headModel);
        this.headModel.setAllVisible(false);
        this.headModel.head.visible = true;
        this.headModel.hat.visible = true;

        this.headModel.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
        this.headModel.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        VertexConsumer vertexConsumer = ItemRenderer.getArmorFoilBuffer(source, RenderType.armorCutoutNoCull(TEXTURE), false);
        this.headModel.renderToBuffer(pose, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY);
    }

}