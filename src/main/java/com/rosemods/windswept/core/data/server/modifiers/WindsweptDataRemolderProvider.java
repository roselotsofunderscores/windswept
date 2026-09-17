package com.rosemods.windswept.core.data.server.modifiers;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.WindsweptBlocks;
import com.rosemods.windswept.core.registry.WindsweptItems;
import com.teamabnormals.blueprint.common.remolder.data.RemolderProvider;
import net.minecraft.advancements.critereon.EntityFlagsPredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemDamageFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static com.teamabnormals.blueprint.common.remolder.util.LootRemolders.addPool;
import static com.teamabnormals.blueprint.common.remolder.util.LootRemolders.replacePools;

public class WindsweptDataRemolderProvider extends RemolderProvider {

    public WindsweptDataRemolderProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registryProvider) {
        super(Windswept.MOD_ID, PackOutput.Target.DATA_PACK, output, registryProvider);
    }

    @Override
    protected void registerEntries(HolderLookup.Provider provider) {
        LootPool goatPool = LootPool.lootPool().name("windswept:goat").setRolls(ConstantValue.exactly(1f))
                .add(LootItem.lootTableItem(WindsweptItems.GOAT.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))
                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(provider, UniformGenerator.between(0, 1)))
                        .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                                EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true)).build()))))
                .build();
        this.entry("goat_meat")
                .path("loot_table/entities/goat")
                .remolder(replacePools(goatPool));

        LootPool drownedPool = LootPool.lootPool().name("windswept:rain_disc")
                .add(LootItem.lootTableItem(WindsweptItems.MUSIC_DISC_RAIN.get()))
                .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.ATTACKER,
                        EntityPredicate.Builder.entity().of(EntityTypeTags.SKELETONS).build()))
                .build();
        this.entry("drowned_disc")
                .path("loot_table/entities/drowned")
                .remolder(addPool(drownedPool));

        LootPool arrowPool = LootPool.lootPool().name("minecraft:arrow").setRolls(ConstantValue.exactly(1f))
                .add(LootItem.lootTableItem(Items.ARROW)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0f, 2f)))
                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(provider, UniformGenerator.between(0f, 1f))))
                .build();
        LootPool bonePool = LootPool.lootPool().name("minecraft:bone").setRolls(ConstantValue.exactly(1f))
                .add(LootItem.lootTableItem(Items.BONE)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0f, 2f)))
                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(provider, UniformGenerator.between(0f, 1f))))
                .build();
        LootPool frostArrowPool = LootPool.lootPool().name("windswept:frost_arrow").setRolls(ConstantValue.exactly(1f))
                .add(LootItem.lootTableItem(WindsweptItems.FROST_ARROW.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0f, 1f)))
                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(provider, UniformGenerator.between(0f, 1f)).setLimit(1)))
                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                .build();
        this.entry("stray")
                .path("loot_table/entities/stray")
                .remolder(replacePools(arrowPool, bonePool, frostArrowPool));

        this.chestEntry("village_taiga_house", "chests/village/village_taiga_house", WindsweptItems.MUTTON_PIE, b -> b.setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(-1, 2))));
        this.chestEntry("village_snowy_house", "chests/village/village_snowy_house", WindsweptItems.MUTTON_PIE, b -> b.setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(-1, 2))));
        this.chestEntry("underwater_ruin_small", "chests/underwater_ruin_small", WindsweptItems.WOODEN_BUCKET, b -> b.setWeight(2).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(3, 20))));
        this.chestEntry("village_fisher", "chests/village/village_fisher", WindsweptItems.WOODEN_WATER_BUCKET, b -> b.setWeight(2).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(3, 20))));

        LootPool savannaPool = LootPool.lootPool().name(Windswept.MOD_ID + ":village_savanna_house").setRolls(UniformGenerator.between(1f, 3f))
                .add(LootItem.lootTableItem(WindsweptBlocks.YELLOW_PETALS.get()).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 12))))
                .add(LootItem.lootTableItem(WindsweptBlocks.FLOWERING_ACACIA_SAPLING.get()).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 4))))
                .add(LootItem.lootTableItem(WindsweptBlocks.MIMOSA.get()).setWeight(4).apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 7))))
                .build();
        this.entry("village_savanna_house")
                .path("loot_table/chests/village/village_savanna_house")
                .remolder(addPool(savannaPool));
    }

    private void chestEntry(String name, String target, DeferredHolder<Item, ? extends ItemLike> item, Consumer<LootPoolSingletonContainer.Builder<?>> b) {
        LootPoolSingletonContainer.Builder<?> builder = LootItem.lootTableItem(item.get());
        b.accept(builder);
        LootPool pool = LootPool.lootPool().name(Windswept.MOD_ID + ":" + name).setRolls(ConstantValue.exactly(1f)).add(builder).build();
        this.entry(name)
                .path("loot_table/" + target)
                .remolder(addPool(pool));
    }

}