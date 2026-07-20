package com.rosemods.windswept.core.data.client;

import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.client.screen.splash.SplashProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class WindsweptSplashProvider extends SplashProvider {
    public WindsweptSplashProvider(GatherDataEvent event) {
        super(Windswept.MOD_ID, event.getGenerator().getPackOutput());
    }

    @Override
    protected void registerSplashes() {
        this.add("#WINDSWEEP");
        this.add("cold water fell from the top of the sky");
    }

}
