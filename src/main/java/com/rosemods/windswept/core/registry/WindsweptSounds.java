package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.core.util.registry.SoundSubRegistryHelper;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

public final class WindsweptSounds {
    public static final SoundSubRegistryHelper SOUNDS = Windswept.REGISTRY_HELPER.getSoundSubHelper();

    public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_DISC_RAIN = SOUNDS.createSoundEvent("music_disc.rain");
    public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_DISC_SNOW = SOUNDS.createSoundEvent("music_disc.snow");
    public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_DISC_BUMBLEBEE = SOUNDS.createSoundEvent("music_disc.bumblebee");
    public static final DeferredHolder<SoundEvent, SoundEvent> PINECONE_NOTE = SOUNDS.createSoundEvent("block.pinecone.note");

    public static final DeferredHolder<SoundEvent, SoundEvent> CHILLED_DEATH = SOUNDS.createSoundEvent("entity.chilled.death");
    public static final DeferredHolder<SoundEvent, SoundEvent> CHILLED_HURT = SOUNDS.createSoundEvent("entity.chilled.hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> CHILLED_AMBIENT = SOUNDS.createSoundEvent("entity.chilled.ambient");

}
