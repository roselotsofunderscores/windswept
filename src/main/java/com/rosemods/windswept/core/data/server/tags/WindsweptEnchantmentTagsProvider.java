package com.rosemods.windswept.core.data.server.tags;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.data.server.WindsweptDatapackProvider;
import com.rosemods.windswept.core.registry.WindsweptEnchantments;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class WindsweptEnchantmentTagsProvider extends TagsProvider<Enchantment> {

    public WindsweptEnchantmentTagsProvider(GatherDataEvent event, WindsweptBlockTagProvider blockTags, WindsweptDatapackProvider dataPack) {
        super(event.getGenerator().getPackOutput(), Registries.ENCHANTMENT, dataPack.getRegistryProvider(), Windswept.MOD_ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(EnchantmentTags.CURSE).add(WindsweptEnchantments.SLIPPING_CURSE);
    }

    @Override
    public String getName() {
        return "Windswept Enchantment Tags";
    }
}