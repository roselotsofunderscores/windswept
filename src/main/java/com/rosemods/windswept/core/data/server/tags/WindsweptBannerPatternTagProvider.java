package com.rosemods.windswept.core.data.server.tags;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.data.server.WindsweptDatapackProvider;
import com.rosemods.windswept.core.other.tags.WindsweptBannerPatternTags;
import com.rosemods.windswept.core.registry.datapack.WindsweptBannerPatterns;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.BannerPatternTagsProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class WindsweptBannerPatternTagProvider extends BannerPatternTagsProvider {

    public WindsweptBannerPatternTagProvider(GatherDataEvent event, WindsweptDatapackProvider dataPack) {
        super(event.getGenerator().getPackOutput(), dataPack.getRegistryProvider(), Windswept.MOD_ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(WindsweptBannerPatternTags.SNOW_CHARGE).add(WindsweptBannerPatterns.SNOW_CHARGE);
        this.tag(WindsweptBannerPatternTags.SNOW_GOLEM).add(WindsweptBannerPatterns.SNOW_GOLEM);
        this.tag(WindsweptBannerPatternTags.ROSE_FLOWER).add(WindsweptBannerPatterns.ROSE_FLOWER);
    }

}
