package com.rosemods.windswept.core.registry.datapack;

import com.rosemods.windswept.core.Windswept;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BannerPattern;

public final class WindsweptBannerPatterns {
    public static final ResourceKey<BannerPattern> SNOW_CHARGE = createKey("snow_charge");
    public static final ResourceKey<BannerPattern> SNOW_GOLEM = createKey("snow_golem");
    public static final ResourceKey<BannerPattern> ROSE_FLOWER = createKey("rose_flower");

    public static void bootstrap(BootstrapContext<BannerPattern> context) {
        register(context, SNOW_CHARGE);
        register(context, SNOW_GOLEM);
        register(context, ROSE_FLOWER);
    }

    private static ResourceKey<BannerPattern> createKey(String name) {
        return ResourceKey.create(Registries.BANNER_PATTERN, Windswept.location(name));
    }

    private static void register(BootstrapContext<BannerPattern> context, ResourceKey<BannerPattern> key) {
        ResourceLocation location = key.location();
        context.register(key, new BannerPattern(location, "block.minecraft.banner." + location.getNamespace() + "." + location.getPath()));
    }

}
