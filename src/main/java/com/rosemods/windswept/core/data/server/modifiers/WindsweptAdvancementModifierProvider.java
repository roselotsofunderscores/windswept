package com.rosemods.windswept.core.data.server.modifiers;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.data.server.WindsweptDatapackProvider;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.rosemods.windswept.core.registry.WindsweptEffects;
import com.rosemods.windswept.core.registry.WindsweptEntityTypes;
import com.rosemods.windswept.core.registry.WindsweptItems;
import com.teamabnormals.blueprint.common.advancement.modification.AdvancementModifierProvider;
import com.teamabnormals.blueprint.common.advancement.modification.modifiers.CriteriaModifier;
import com.teamabnormals.blueprint.common.advancement.modification.modifiers.EffectsChangedModifier;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Collection;
import java.util.List;

public class WindsweptAdvancementModifierProvider extends AdvancementModifierProvider {
    public WindsweptAdvancementModifierProvider(GatherDataEvent event, WindsweptDatapackProvider dataPack) {
        super(Windswept.MOD_ID, event.getGenerator().getPackOutput(), dataPack.getRegistryProvider());
    }

    @Override
    protected void registerEntries(HolderLookup.Provider provider) {
        final List<Block> seedsBlocks = List.of(WindsweptBlocks.WILD_BERRY_BUSH.get());
        final List<EntityType<?>> killedMobs = List.of(WindsweptEntityTypes.CHILLED.get());

        this.entry("balanced_diet").selects("husbandry/balanced_diet").addModifier(this.food().requirements(AdvancementRequirements.Strategy.AND).build());
        this.entry("all_effects").selects("nether/all_effects").addModifier(new EffectsChangedModifier("all_effects", false, this.effects()));
        this.entry("plant_seed").selects("husbandry/plant_seed").addModifier(this.seedsBlocks(seedsBlocks).addIndexedRequirements(0, false, this.getNames(BuiltInRegistries.BLOCK, seedsBlocks)).build());
        this.entry("kill_a_mob").selects("adventure/kill_a_mob").addModifier(this.killedMobs(killedMobs).addIndexedRequirements(0, false, this.getNames(BuiltInRegistries.ENTITY_TYPE, killedMobs)).build());
        this.entry("kill_all_mobs").selects("adventure/kill_all_mobs").addModifier(this.killedMobs(killedMobs).requirements(AdvancementRequirements.Strategy.AND).build());
        this.entry("walk_on_powder_snow_with_leather_boots").selects("adventure/walk_on_powder_snow_with_leather_boots").addModifier(this.snowBoots().addIndexedRequirements(0, false, "snow_boots").build());
    }

    private CriteriaModifier.Builder builder() {
        return CriteriaModifier.builder(this.modId);
    }

    private <T> String[] getNames(Registry<T> registry, List<? extends T> listIn) {
        String[] names = new String[listIn.size()];
        for (int i = 0; i < listIn.size(); i++) names[i] = registry.getKey(listIn.get(i)).getPath();
        return names;
    }

    private CriteriaModifier.Builder food() {
        CriteriaModifier.Builder food = CriteriaModifier.builder(this.modId);
        Collection<DeferredHolder<Item, ? extends Item>> items = WindsweptItems.ITEMS.getDeferredRegister().getEntries().stream().filter(i -> i.get().getDefaultInstance().getFoodProperties(null) != null).toList();
        items.forEach(item -> food.addCriterion(BuiltInRegistries.ITEM.getKey(item.get()).getPath(), ConsumeItemTrigger.TriggerInstance.usedItem(item.get())));
        return food;
    }

    private MobEffectsPredicate effects() {
        MobEffectsPredicate.Builder effects = MobEffectsPredicate.Builder.effects();
        WindsweptEffects.EFFECTS.getDeferredRegister().getEntries().forEach(effects::and);
        return effects.build().get();
    }

    private CriteriaModifier.Builder seedsBlocks(List<Block> seedsBlocksIn) {
        CriteriaModifier.Builder seedsBlocks = this.builder();
        seedsBlocksIn.forEach(s -> seedsBlocks.addCriterion(BuiltInRegistries.BLOCK.getKey(s).getPath(), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(s)));
        return seedsBlocks;
    }

    private CriteriaModifier.Builder killedMobs(List<EntityType<?>> killedMobsIn) {
        CriteriaModifier.Builder killedMobs = this.builder();
        killedMobsIn.forEach(e -> killedMobs.addCriterion(BuiltInRegistries.ENTITY_TYPE.getKey(e).getPath(), KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(e))));
        return killedMobs;
    }

    private CriteriaModifier.Builder snowBoots() {
        return this.builder().addCriterion("snow_boots", PlayerTrigger.TriggerInstance.walkOnBlockWithEquipment(Blocks.POWDER_SNOW, WindsweptItems.SNOW_BOOTS.get()));
    }

}
