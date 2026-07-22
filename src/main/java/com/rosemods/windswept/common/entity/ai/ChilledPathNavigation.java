package com.rosemods.windswept.common.entity.ai;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.PathFinder;

public class ChilledPathNavigation extends GroundPathNavigation {

    public ChilledPathNavigation(Mob mob, Level level) {
        super(mob, level);
    }

    @Override
    protected PathFinder createPathFinder(int maxVisitedNodes) {
        ChilledNodeEvaluator evaluator = new ChilledNodeEvaluator();
        this.nodeEvaluator = evaluator;
        evaluator.setCanPassDoors(true);
        return new PathFinder(evaluator, maxVisitedNodes);
    }
}