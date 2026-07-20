package com.rosemods.windswept.common.item;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.WindsweptAttributes;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class LavenderCrownItem extends ArmorItem {
    private static final ResourceLocation FRAGRANCE_ID = Windswept.location("fragrance_modifier");

    public LavenderCrownItem(Holder<ArmorMaterial> material, ArmorItem.Type type, Properties properties) {
        super(material, type, properties);
    }

    // Использование метода без ItemStack убирает Deprecation warning
    @Override
    public ItemAttributeModifiers getDefaultAttributeModifiers() {
        return super.getDefaultAttributeModifiers()
                .withModifierAdded(WindsweptAttributes.FRAGRANCE,
                        new AttributeModifier(FRAGRANCE_ID, 1.0D, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.HEAD);
    }
}