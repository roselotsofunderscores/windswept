package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.core.Windswept;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class WindsweptAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(Registries.ATTRIBUTE, Windswept.MOD_ID);

    public static final DeferredHolder<Attribute, Attribute> SNOW_SPEED = register("snow_speed");
    public static final DeferredHolder<Attribute, Attribute> FRAGRANCE = register("fragrance");
    public static final DeferredHolder<Attribute, Attribute> SPRINT_DAMAGE = register("sprint_damage");

    private static DeferredHolder<Attribute, Attribute> register(String name) {
        return ATTRIBUTES.register(name, () -> new RangedAttribute("attribute." + Windswept.MOD_ID + ".name.generic." + name, 0d, 0d, 1d));
    }

}
