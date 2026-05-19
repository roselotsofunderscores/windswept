package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.common.enchantment.curse.SlippingCurseEnchantment;
import com.rosemods.windswept.core.Windswept;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class WindsweptEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(Registries.ENCHANTMENT, Windswept.MOD_ID);

    public static final DeferredHolder<Enchantment, Enchantment> SLIPPING_CURSE = ENCHANTMENTS.register("slipping_curse", SlippingCurseEnchantment::new);
}
