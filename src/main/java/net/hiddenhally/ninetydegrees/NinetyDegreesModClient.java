package net.hiddenhally.ninetydegrees;

import net.fabricmc.api.ClientModInitializer;
import net.hiddenhally.ninetydegrees.config.ModConfig;

public class NinetyDegreesModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModConfig.load();
    }
}