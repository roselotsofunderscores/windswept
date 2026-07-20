package com.rosemods.windswept.core.other.events;

import com.rosemods.windswept.common.entity.Chilled;
import com.rosemods.windswept.common.item.FeatherCloakItem;
import com.rosemods.windswept.common.item.SnowBootsItem;
import com.rosemods.windswept.common.item.WoodenMilkBucketItem;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.WindsweptConfig;
import com.rosemods.windswept.core.other.WindsweptDataProcessors;
import com.rosemods.windswept.core.other.tags.WindsweptEntityTypeTags;
import com.rosemods.windswept.core.registry.WindsweptEffects;
import com.rosemods.windswept.core.registry.WindsweptEntityTypes;
import com.rosemods.windswept.core.registry.WindsweptItems;
import com.teamabnormals.blueprint.common.world.storage.tracking.IDataManager;
import com.teamabnormals.blueprint.common.world.storage.tracking.TrackedData;
import com.teamabnormals.blueprint.core.other.tags.BlueprintEntityTypeTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.living.BabyEntitySpawnEvent;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

import java.util.List;

@EventBusSubscriber(modid = Windswept.MOD_ID)
public class WindsweptEntityEvents {
    private static final List<MobSpawnType> NATURAL_SPAWNS = List.of(MobSpawnType.NATURAL, MobSpawnType.CHUNK_GENERATION, MobSpawnType.PATROL, MobSpawnType.REINFORCEMENT, MobSpawnType.JOCKEY);

    @SubscribeEvent
    public static void onEntityHurt(LivingIncomingDamageEvent event) {
        LivingEntity entity = event.getEntity();
        DamageSource source = event.getSource();
        Entity attacker = source.getEntity();

        if (attacker == null || entity == null)
            return;

        if (entity.hasEffect(WindsweptEffects.THORNS)) {
            int amplifier = entity.getEffect(WindsweptEffects.THORNS).getAmplifier() + 1;
            RandomSource rand = entity.getRandom();

            if (rand.nextFloat() < (float) amplifier * 0.15F) {
                attacker.hurt(entity.damageSources().thorns(entity), (float) (1 + rand.nextInt(4)));
            }
        }
    }

    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        ItemStack stack = event.getItemStack();
        Entity target = event.getTarget();

        if (stack.is(WindsweptItems.WOODEN_BUCKET.get()) && target.getType().is(BlueprintEntityTypeTags.MILKABLE)) {
            if (target instanceof Animal animal && animal.isBaby())
                return;

            WoodenMilkBucketItem.milkAnimal(event.getEntity(), event.getHand(), stack);
            event.setCancellationResult(InteractionResult.sidedSuccess(event.getLevel().isClientSide));
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onBabySpawn(BabyEntitySpawnEvent event) {
        if (WindsweptConfig.COMMON.rabbitLitters.get() && event.getParentA() instanceof Rabbit parent && event.getParentB() instanceof Rabbit parentB) {
            Level level = parent.level();
            int litter = level.random.nextInt(3);

            for (int i = 0; i < litter; i++) {
                Rabbit baby = EntityType.RABBIT.create(level);
                if (baby != null) {
                    baby.setBaby(true);
                    baby.setVariant(level.random.nextBoolean() ? parent.getVariant() : parentB.getVariant());
                    baby.moveTo(parent.getX(), parent.getY(), parent.getZ(), 0f, 0f);
                    level.addFreshEntity(baby);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLivingSpawn(FinalizeSpawnEvent event) {
        Mob mob = event.getEntity();
        LevelAccessor level = event.getLevel();

        if (NATURAL_SPAWNS.contains(event.getSpawnType()) && mob != null && level instanceof ServerLevel && mob.getY() > 60 && level.getBiome(mob.blockPosition()).is(Tags.Biomes.IS_SNOWY)) {
            if (mob.getType() == EntityType.ZOMBIE) {
                mob = mob.convertTo((EntityType<? extends Mob>) WindsweptEntityTypes.CHILLED.get(), true);

                if (mob instanceof Chilled chilled)
                    chilled.cncCompat(level.getRandom());
            } else if (mob.getType() == EntityType.SKELETON && WindsweptConfig.COMMON.strays.get()) {
                mob = mob.convertTo(EntityType.STRAY, true);

                if (mob != null)
                    mob.setItemInHand(InteractionHand.MAIN_HAND, Items.BOW.getDefaultInstance());
            }
        }
    }

    @SubscribeEvent
    public static void onEntityUpdate(EntityTickEvent.Post event) {
        Entity baseEntity = event.getEntity();
        if (!(baseEntity instanceof LivingEntity entity))
            return;

        IDataManager data = (IDataManager) entity;

        if (SnowBootsItem.canSpawnSnowParticle(entity))
            SnowBootsItem.spawnSnowParticle(entity);

        if (!entity.level().isClientSide) {
            boolean flag = entity.isCrouching() && entity.getItemBySlot(EquipmentSlot.CHEST).is(WindsweptItems.FEATHER_CLOAK.get());

            if (flag != data.getValue(WindsweptDataProcessors.CLOAKED)) {
                data.setValue(WindsweptDataProcessors.CLOAKED, flag);
                FeatherCloakItem.spawnFeatherCloakParticle(entity);
            }
        }

        if (entity.getType().is(WindsweptEntityTypeTags.CONVERT_TO_CHILLED) && entity instanceof Mob mob && !mob.level().isClientSide && mob.isAlive() && !mob.isNoAi()) {
            if (data.getValue(WindsweptDataProcessors.IS_FREEZE_CONVERTING)) {
                ammendData(data, WindsweptDataProcessors.FREEZE_CONVERT_TIME, -1);
                if (data.getValue(WindsweptDataProcessors.FREEZE_CONVERT_TIME) < 0) {
                    mob.convertTo((EntityType<? extends Mob>) WindsweptEntityTypes.CHILLED.get(), true);
                    data.clean();
                    if (!mob.isSilent())
                        mob.level().levelEvent(null, 1048, mob.blockPosition(), 0);
                }
            } else if (mob.isInPowderSnow) {
                ammendData(data, WindsweptDataProcessors.POWDER_SNOW_TIME, 1);
                if (data.getValue(WindsweptDataProcessors.POWDER_SNOW_TIME) >= 140) {
                    data.setValue(WindsweptDataProcessors.FREEZE_CONVERT_TIME, 300);
                    data.setValue(WindsweptDataProcessors.IS_FREEZE_CONVERTING, true);
                }
            } else {
                data.setValue(WindsweptDataProcessors.POWDER_SNOW_TIME, -1);
            }
        }
    }

    private static void ammendData(IDataManager data, TrackedData<Integer> tracked, int change) {
        data.setValue(tracked, data.getValue(tracked) + change);
    }
}