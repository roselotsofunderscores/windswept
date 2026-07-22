package com.rosemods.windswept.common.entity;

public interface VillagerHostileData {
    double DEFAULT_ACCEPTABLE_DISTANCE = 8.0D;

    default double windswept$getVillagerHostileDistance() {
        return DEFAULT_ACCEPTABLE_DISTANCE;
    }
}