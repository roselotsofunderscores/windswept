package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.common.entity.Chilled;
import com.rosemods.windswept.common.entity.FrostArrow;
import com.rosemods.windswept.common.entity.Frostbiter;
import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.core.util.registry.EntitySubRegistryHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;

public final class WindsweptEntityTypes {
    public static final EntitySubRegistryHelper ENTITIES = Windswept.REGISTRY_HELPER.getEntitySubHelper();

    public static final DeferredHolder<EntityType<?>, EntityType<Chilled>> CHILLED = ENTITIES.createEntity("chilled", Chilled::new, MobCategory.MONSTER, builder ->
            builder.sized(.6f, 2f).eyeHeight(1.74f).passengerAttachments(2.0125f).ridingOffset(-.7f).clientTrackingRange(8));
    public static final DeferredHolder<EntityType<?>, EntityType<Frostbiter>> FROSTBITER = ENTITIES.createEntity("frostbiter", Frostbiter::new, MobCategory.CREATURE, builder ->
            builder.sized(1.05f, 1.7f).eyeHeight(1.3f).passengerAttachments(1.25f).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<FrostArrow>> FROST_ARROW = ENTITIES.createEntity("frost_arrow", FrostArrow::new, MobCategory.MISC, .5f, .5f);

}
