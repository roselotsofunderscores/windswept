package com.rosemods.windswept.core.registry;

import com.rosemods.windswept.common.levelgen.tree.decorator.BranchDecorator;
import com.rosemods.windswept.common.levelgen.tree.decorator.MimosaDecorator;
import com.rosemods.windswept.core.Windswept;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public final class WindsweptTreeDecorators {
    public static final DeferredRegister<TreeDecoratorType<?>> DECORATORS = DeferredRegister.create(Registries.TREE_DECORATOR_TYPE, Windswept.MOD_ID);

    public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<BranchDecorator>> BRANCH_DECORATOR = DECORATORS.register("branch", () -> new TreeDecoratorType<>(BranchDecorator.CODEC));
    public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<MimosaDecorator>> MIMOSA_DECORATOR = DECORATORS.register("mimosa", () -> new TreeDecoratorType<>(MimosaDecorator.CODEC));
}
