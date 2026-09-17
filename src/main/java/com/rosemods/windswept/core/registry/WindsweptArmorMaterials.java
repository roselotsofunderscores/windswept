package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.core.Windswept;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public final class WindsweptArmorMaterials {
    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, Windswept.MOD_ID);

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> SNOW_BOOTS = registerSnowBoots();
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> LAVENDER_CROWN = register("lavender_crown", new int[]{0, 0, 0, 0}, 10, Holder.direct(SoundEvents.AZALEA_BREAK), 0f, 0f, false, () -> Ingredient.of(WindsweptBlocks.LAVENDER.get()));
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> ANTLER_HELMET = register("antler_helmet", new int[]{0, 0, 0, 1}, 17, SoundEvents.ARMOR_EQUIP_GENERIC, 0f, .1f, false, () -> Ingredient.of(Items.BONE));
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> FEATHER_CLOAK = register("feather_cloak", new int[]{0, 0, 1, 0}, 17, SoundEvents.ARMOR_EQUIP_GENERIC, 0f, 0f, false, () -> Ingredient.of(WindsweptItems.ELDER_FEATHER.get()));

    private static DeferredHolder<ArmorMaterial, ArmorMaterial> registerSnowBoots() {
        EnumMap<ArmorItem.Type, Integer> enummap = Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
            map.put(ArmorItem.Type.BOOTS, 1);
            map.put(ArmorItem.Type.LEGGINGS, 0);
            map.put(ArmorItem.Type.CHESTPLATE, 0);
            map.put(ArmorItem.Type.HELMET, 0);
        });

        return ARMOR_MATERIALS.register("snow_boots", () -> new ArmorMaterial(
                enummap,
                17,
                SoundEvents.ARMOR_EQUIP_GOLD,
                () -> Ingredient.of(Items.LEATHER),
                List.of(
                        new ArmorMaterial.Layer(Windswept.location("snow_boots"), "", true),
                        new ArmorMaterial.Layer(Windswept.location("snow_boots"), "_overlay", false)
                ),
                0f,
                0f
        ));
    }

    private static DeferredHolder<ArmorMaterial, ArmorMaterial> register(String name, int[] defense, int enchantmentValue, Holder<SoundEvent> equipSound, float toughness, float knockbackResistance, boolean dyeable, Supplier<Ingredient> repairIngridient) {
        EnumMap<ArmorItem.Type, Integer> enummap = Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
            map.put(ArmorItem.Type.BOOTS, defense[0]);
            map.put(ArmorItem.Type.LEGGINGS, defense[1]);
            map.put(ArmorItem.Type.CHESTPLATE, defense[2]);
            map.put(ArmorItem.Type.HELMET, defense[3]);
        });

        return ARMOR_MATERIALS.register(name, () -> new ArmorMaterial(enummap, enchantmentValue, equipSound, repairIngridient, List.of(new ArmorMaterial.Layer(Windswept.location(name), "", dyeable)), toughness, knockbackResistance));
    }

}
