package it.hurts.shatterbyte.immersiveui.fabric.client;

import it.hurts.shatterbyte.immersiveui.ImmersiveUI;
import it.hurts.shatterbyte.shatterlib.module.config.ConfigManager;
import net.fabricmc.api.ClientModInitializer;

public final class ImmersiveUIFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Client entrypoints run after every main entrypoint, so ShatterLib's platform service is initialized by now.
        ConfigManager.register(ImmersiveUI.MOD_ID, ImmersiveUI.CONFIG);

        // ShatterLib loads every registered config once, during its own initialization, which has already happened.
        // Load explicitly (this also creates the config file).
        ConfigManager.reload(ImmersiveUI.CONFIG.getPath(), null);
    }
}
