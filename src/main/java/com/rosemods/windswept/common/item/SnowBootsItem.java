package com.rosemods.windswept.common.item;

import com.rosemods.windswept.common.entity.Frostbiter;
import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.other.tags.WindsweptBlockTags;
import com.rosemods.windswept.core.registry.WindsweptAttributes;
import com.rosemods.windswept.core.registry.WindsweptItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.phys.Vec3;

public class SnowBootsItem extends ArmorItem {
    private static final ResourceLocation SNOW_SPEED_ID = Windswept.location("snow_speed_modifier");
    private static final ResourceLocation SPEED_BOOST_ID = Windswept.location("snow_speed_boost");

    public SnowBootsItem(Holder<ArmorMaterial> material, ArmorItem.Type type, Properties properties) {
        super(material, type, properties);
    }

    public static boolean canApplySnowSpeed(LivingEntity entity) {
        BlockPos below = entity.getOnPos(0.500001f);

        return isSnowingAt(entity) || ((entity.level().getBlockState(below).is(WindsweptBlockTags.SNOW_BOOTS_BLOCKS)
                || entity.level().getBlockState(below.above()).is(WindsweptBlockTags.SNOW_BOOTS_BLOCKS)) && !entity.level().getBlockState(entity.getOnPos()).isAir());
    }

    // Util //

    private static boolean isSnowingAt(LivingEntity entity) {
        BlockPos pos = entity.blockPosition();
        return entity.level().isRaining() && entity.level().getBiome(pos).value().getPrecipitationAt(pos) == Biome.Precipitation.SNOW;
    }

    public static boolean canSpawnSnowParticle(LivingEntity entity) {
        return entity.tickCount % 5 == 0 && entity.getDeltaMovement().x != 0d && entity.getDeltaMovement().z != 0d
                && !entity.isSpectator() && canApplySnowSpeed(entity)
                && (entity.getItemBySlot(EquipmentSlot.FEET).is(WindsweptItems.SNOW_BOOTS.get()) || entity instanceof Frostbiter);
    }

    public static void spawnSnowParticle(LivingEntity entity) {
        Vec3 vec3 = entity.getDeltaMovement();
        entity.level().addParticle(ParticleTypes.SNOWFLAKE,
                entity.getX() + (entity.level().random.nextDouble() - 0.5d) * (double) entity.getBbWidth(),
                entity.getY() + 0.1d,
                entity.getZ() + (entity.level().random.nextDouble() - 0.5d) * (double) entity.getBbWidth(),
                vec3.x * -0.2d, 0.1d, vec3.z * -0.2d);
    }

    public static void removeSnowSpeed(LivingEntity entity) {
        AttributeInstance speed = entity.getAttribute(Attributes.MOVEMENT_SPEED);

        if (speed != null && speed.getModifier(SPEED_BOOST_ID) != null)
            speed.removeModifier(SPEED_BOOST_ID);
    }

    public static void tryAddSnowSpeed(LivingEntity entity) {
        ItemStack boots = entity.getItemBySlot(EquipmentSlot.FEET);
        if (boots.is(WindsweptItems.SNOW_BOOTS.get())) {
            AttributeInstance speed = entity.getAttribute(Attributes.MOVEMENT_SPEED);

            if (speed != null) {
                if (speed.getModifier(SPEED_BOOST_ID) == null)
                    speed.addTransientModifier(new AttributeModifier(SPEED_BOOST_ID, 0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

                if (entity.level().random.nextFloat() < 0.02f)
                    boots.hurtAndBreak(1, entity, EquipmentSlot.FEET);
            }
        }
    }

    @Override
    public ItemAttributeModifiers getDefaultAttributeModifiers(ItemStack stack) {
        ItemAttributeModifiers modifiers = super.getDefaultAttributeModifiers(stack);
        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.bySlot(this.type.getSlot());

        // В 1.21.1 с использованием DeferredHolder (getHolder() или само поле, если это DeferredHolder)
        modifiers = modifiers.withModifierAdded(WindsweptAttributes.SNOW_SPEED,
                new AttributeModifier(SNOW_SPEED_ID, 0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), slotGroup);

        return modifiers;
    }
}