package com.rosemods.windswept.core.registry.datapack;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.WindsweptSounds;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;

public class WindsweptJukeboxSongs {
    public static final ResourceKey<JukeboxSong> RAIN = create("rain");
    public static final ResourceKey<JukeboxSong> SNOW = create("snow");
    public static final ResourceKey<JukeboxSong> BUMBLEBEE = create("bumblebee");

    public static void bootstrap(BootstrapContext<JukeboxSong> context) {
        register(context, RAIN, WindsweptSounds.MUSIC_DISC_RAIN, 124, 6);
        register(context, SNOW, WindsweptSounds.MUSIC_DISC_SNOW, 121, 13);
        register(context, BUMBLEBEE, WindsweptSounds.MUSIC_DISC_BUMBLEBEE, 55, 9);
    }

    private static ResourceKey<JukeboxSong> create(String name) {
        return ResourceKey.create(Registries.JUKEBOX_SONG, Windswept.location(name));
    }

    private static void register(BootstrapContext<JukeboxSong> context, ResourceKey<JukeboxSong> key, Holder<SoundEvent> soundEvent, int lengthInSeconds, int comparatorOutput) {
        context.register(key, new JukeboxSong(soundEvent, Component.translatable(Util.makeDescriptionId("jukebox_song", key.location())), lengthInSeconds, comparatorOutput));
    }

}
