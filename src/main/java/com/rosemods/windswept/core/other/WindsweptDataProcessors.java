package com.rosemods.windswept.core.other;

import com.mojang.serialization.Codec;
import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.common.world.storage.tracking.TrackedData;
import com.teamabnormals.blueprint.common.world.storage.tracking.TrackedDataManager;
import net.minecraft.network.codec.ByteBufCodecs;

public final class WindsweptDataProcessors {
    public static final TrackedData<Integer> FREEZE_CONVERT_TIME = TrackedData.Builder.create(ByteBufCodecs.INT, () -> 0).enableSaving(Codec.INT.fieldOf("Integer")).build();
    public static final TrackedData<Integer> POWDER_SNOW_TIME = TrackedData.Builder.create(ByteBufCodecs.INT, () -> 0).enableSaving(Codec.INT.fieldOf("Integer")).build();
    public static final TrackedData<Boolean> IS_FREEZE_CONVERTING = TrackedData.Builder.create(ByteBufCodecs.BOOL, () -> false).enableSaving(Codec.BOOL.fieldOf("Boolean")).build();
    public static final TrackedData<Boolean> CLOAKED = TrackedData.Builder.create(ByteBufCodecs.BOOL, () -> false).enableSaving(Codec.BOOL.fieldOf("Boolean")).build();

    public static void registerData() {
        register("freeze_convert_time", FREEZE_CONVERT_TIME);
        register("powder_snow_time", POWDER_SNOW_TIME);
        register("is_freeze_converting", IS_FREEZE_CONVERTING);
        register("cloaked", CLOAKED);
    }

    private static void register(String name, TrackedData<?> data) {
        TrackedDataManager.INSTANCE.registerData(Windswept.location(name), data);
    }

}
