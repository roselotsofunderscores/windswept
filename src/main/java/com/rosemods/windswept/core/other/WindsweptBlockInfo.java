package com.rosemods.windswept.core.other;

import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.core.util.DataUtil;
import net.minecraft.world.level.block.Blocks;

public final class WindsweptBlockInfo {
    public static void changeLocalisation() {
        DataUtil.changeBlockLocalization(Blocks.SNOW, Windswept.MOD_ID, "snow_carpet");
        DataUtil.changeBlockLocalization(Blocks.SNOW_BLOCK, "minecraft", "snow");
    }

}