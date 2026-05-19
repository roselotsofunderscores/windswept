package com.rosemods.windswept.core.registry.util;

import com.teamabnormals.blueprint.common.effect.BlueprintMobEffect;
import com.teamabnormals.blueprint.core.util.registry.ISubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class EffectSubRegistryHelper implements ISubRegistryHelper<MobEffect> {
    protected final RegistryHelper parent;
    protected final DeferredRegister<MobEffect> effectRegister;
    protected final DeferredRegister<Potion> potionRegister;

    public EffectSubRegistryHelper(RegistryHelper parent) {
        this(parent, DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, parent.getModId()), DeferredRegister.create(BuiltInRegistries.POTION, parent.getModId()));
    }

    public EffectSubRegistryHelper(RegistryHelper parent, DeferredRegister<MobEffect> effectRegister, DeferredRegister<Potion> potionRegister) {
        this.parent = parent;
        this.effectRegister = effectRegister;
        this.potionRegister = potionRegister;
    }

    public <E extends MobEffect> DeferredHolder<MobEffect, E> createEffect(String name, Supplier<? extends E> effect) {
        return this.effectRegister.register(name, effect);
    }

    public DeferredHolder<MobEffect, MobEffect> createEffect(String name, MobEffectCategory effectType, int liquidColor) {
        return this.effectRegister.register(name, () -> new BlueprintMobEffect(effectType, liquidColor));
    }

    public DeferredHolder<Potion, Potion> createPotion(String name, Holder<MobEffect> effect, int duration, int strength) {
        return this.potionRegister.register(name, () -> new Potion(new MobEffectInstance(effect, duration, strength)));
    }

    @Override
    public RegistryHelper getParent() {
        return this.parent;
    }

    @Override
    public DeferredRegister<MobEffect> getDeferredRegister() {
        return this.effectRegister;
    }

    @Override
    public void register(IEventBus eventBus) {
        this.effectRegister.register(eventBus);
        this.potionRegister.register(eventBus);
    }

}
