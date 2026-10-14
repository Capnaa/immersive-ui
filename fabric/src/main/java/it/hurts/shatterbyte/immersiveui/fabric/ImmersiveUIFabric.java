package it.hurts.shatterbyte.immersiveui.fabric;

import net.fabricmc.api.ModInitializer;

public final class ImmersiveUIFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // Config registration lives in the client entrypoint: ShatterLib initializes its platform service in its own
        // main entrypoint, and main entrypoints of dependent mods are not guaranteed to run after it.
    }
}
