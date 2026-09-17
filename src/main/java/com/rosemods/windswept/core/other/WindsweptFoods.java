package com.rosemods.windswept.core.other;

import com.rosemods.windswept.core.registry.WindsweptEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;

public final class WindsweptFoods {
    public static final FoodProperties WILD_BERRIES = new FoodProperties.Builder().nutrition(2).saturationModifier(.1f).build();
    public static final FoodProperties HOLLY_BERRIES = new FoodProperties.Builder().nutrition(2).alwaysEdible().saturationModifier(.4f).effect(() -> new MobEffectInstance(MobEffects.POISON, 100, 0), 1f).build();
    public static final FoodProperties MUTTON_PIE = new FoodProperties.Builder().nutrition(8).saturationModifier(.3f).build();
    public static final FoodProperties GOAT = new FoodProperties.Builder().nutrition(2).saturationModifier(.3f).build();
    public static final FoodProperties COOKED_GOAT = new FoodProperties.Builder().nutrition(6).saturationModifier(.8f).build();
    public static final FoodProperties GOAT_STEW = new FoodProperties.Builder().nutrition(9).saturationModifier(.9f).usingConvertsTo(Items.BOWL).build();
    public static final FoodProperties CHESTNUT_SOUP = new FoodProperties.Builder().nutrition(7).saturationModifier(.5f).usingConvertsTo(Items.BOWL).build();
    public static final FoodProperties FROZEN_FLESH = new FoodProperties.Builder().nutrition(4).saturationModifier(.1f).effect(() -> new MobEffectInstance(MobEffects.HUNGER, 600, 0), .8f).build();
    public static final FoodProperties CHESTNUTS = new FoodProperties.Builder().nutrition(2).saturationModifier(.1f).build();
    public static final FoodProperties ROASTED_CHESTNUTS = new FoodProperties.Builder().nutrition(5).saturationModifier(.4f).build();
    public static final FoodProperties GINGER_ROOT = new FoodProperties.Builder().nutrition(1).saturationModifier(.1f).build();
    public static final FoodProperties GINGERBREAD_COOKIE = new FoodProperties.Builder().nutrition(2).fast().saturationModifier(.1f).build();
    public static final FoodProperties SPICY_SNOW_CONE = new FoodProperties.Builder().nutrition(15).alwaysEdible().saturationModifier(.6f).effect(() -> new MobEffectInstance(WindsweptEffects.FROST_RESISTANCE, 300, 0), 1f).build();
    public static final FoodProperties SWEET_SNOW_CONE = new FoodProperties.Builder().nutrition(15).alwaysEdible().saturationModifier(.6f).effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 300, 0), 1f).build();
    public static final FoodProperties MINTY_SNOW_CONE = new FoodProperties.Builder().nutrition(15).alwaysEdible().saturationModifier(.6f).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 300, 0), 1f).build();
    public static final FoodProperties CANDY_CANE = new FoodProperties.Builder().nutrition(3).fast().saturationModifier(.6f).build();
    public static final FoodProperties GINGER_TEA = new FoodProperties.Builder().nutrition(4).alwaysEdible().saturationModifier(.1f).effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300, 1), 1f).build();
    public static final FoodProperties LAVENDER_TEA = new FoodProperties.Builder().nutrition(3).alwaysEdible().saturationModifier(.1f).effect(() -> new MobEffectInstance(MobEffects.INVISIBILITY, 300, 0), 1f).build();
    public static final FoodProperties PINECONE_JAM = new FoodProperties.Builder().nutrition(4).saturationModifier(.2f).alwaysEdible().build();
}