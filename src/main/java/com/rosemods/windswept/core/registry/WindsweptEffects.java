package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.util.EffectSubRegistryHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.neoforge.registries.DeferredHolder;

public class WindsweptEffects {
    public static final EffectSubRegistryHelper EFFECTS = Windswept.REGISTRY_HELPER.getSubHelper(Registries.MOB_EFFECT);

    // Effects //
    public static final DeferredHolder<MobEffect, MobEffect> THORNS = EFFECTS.createEffect("thorns", MobEffectCategory.BENEFICIAL, 0x295230);
    public static final DeferredHolder<MobEffect, MobEffect> FROST_RESISTANCE = EFFECTS.createEffect("frost_resistance", MobEffectCategory.BENEFICIAL, 0x618cff);
    //public static final DeferredHolder<MobEffect> PLENTY = HELPER.createEffect("plenty", MobEffectCategory.BENEFICIAL, 0xe68834);

    // Potions //
    public static final DeferredHolder<Potion, Potion> THORNS_POTION = EFFECTS.createPotion("thorns", THORNS, 3600, 0);
    public static final DeferredHolder<Potion, Potion> LONG_THORNS_POTION = EFFECTS.createPotion("long_thorns", THORNS, 9600, 0);
    public static final DeferredHolder<Potion, Potion> STRONG_THORNS_POTION = EFFECTS.createPotion("strong_thorns", THORNS, 1800, 1);

    public static final DeferredHolder<Potion, Potion> FROST_RESISTANCE_POTION = EFFECTS.createPotion("frost_resistance", FROST_RESISTANCE, 3600, 0);
    public static final DeferredHolder<Potion, Potion> LONG_FROST_RESISTANCE_POTION = EFFECTS.createPotion("long_frost_resistance", FROST_RESISTANCE, 9600, 0);

    public static void registerPotionRecipes() {
        //DataUtil.addMix(Potions.AWKWARD, WindsweptBlocks.NIGHTSHADE.get().asItem(), Potions.NIGHT_VISION);

        //DataUtil.addMix(Potions.AWKWARD, WindsweptItems.HOLLY_BERRIES.get(), THORNS_POTION.get());
        //DataUtil.addMix(THORNS_POTION.get(), Items.REDSTONE, LONG_THORNS_POTION.get());
        //DataUtil.addMix(THORNS_POTION.get(), Items.GLOWSTONE_DUST, STRONG_THORNS_POTION.get());

        //DataUtil.addMix(Potions.AWKWARD, WindsweptItems.FROZEN_BRANCH.get(), FROST_RESISTANCE_POTION.get());
        //DataUtil.addMix(FROST_RESISTANCE_POTION.get(), Items.REDSTONE, LONG_FROST_RESISTANCE_POTION.get());
    }

}
