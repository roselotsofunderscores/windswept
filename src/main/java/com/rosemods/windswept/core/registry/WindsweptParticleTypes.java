package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.core.Windswept;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class WindsweptParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(Registries.PARTICLE_TYPE, Windswept.MOD_ID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> WILL_O_THE_WISP = PARTICLE_TYPES.register("will_o_the_wisp", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FROST_LEAF = PARTICLE_TYPES.register("frost_leaf", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FEATHER_CLOAK = PARTICLE_TYPES.register("feather_cloak", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ACACIA_LEAVES = PARTICLE_TYPES.register("acacia_leaves", () -> new SimpleParticleType(false));

}
