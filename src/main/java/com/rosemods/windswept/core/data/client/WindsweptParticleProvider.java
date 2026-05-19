package com.rosemods.windswept.core.data.client;

import com.rosemods.windswept.core.Windswept;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

import static com.rosemods.windswept.core.registry.WindsweptParticleTypes.*;

public class WindsweptParticleProvider extends ParticleProvider {
    public WindsweptParticleProvider(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), Windswept.MOD_ID);
    }

    @Override
    protected void addParticles() {
        this.add(WILL_O_THE_WISP, 3);
        this.add(FROST_LEAF, 3);
        this.add(FEATHER_CLOAK, 10);
        this.add(ACACIA_LEAVES, 12);
    }

    private <T extends ParticleType<?>> void add(DeferredHolder<ParticleType<?>, T> particle, int amount) {
        String name = BuiltInRegistries.PARTICLE_TYPE.getKey(particle.get()).getPath();
        String[] textures = new String[amount];

        for (int i = 0; i < amount; i++)
            textures[i] = name + "_" + i;

        this.add(particle.get(), textures);
    }

}
