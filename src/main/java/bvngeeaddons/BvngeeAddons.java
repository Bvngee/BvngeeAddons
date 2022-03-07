package bvngeeaddons;

import bvngeeaddons.config.BvngeeAddonsConfigStorage;
import bvngeeaddons.config.BvngeeAddonsFeatures;
import bvngeeaddons.config.KeybindProvider;
import bvngeeaddons.utils.JsonUtils;
import fi.dy.masa.malilib.config.ConfigManager;
import fi.dy.masa.malilib.event.InitializationHandler;
import fi.dy.masa.malilib.event.InputEventHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class BvngeeAddons implements ModInitializer {

    public static final String MOD_ID = "bvngeeaddons";
    public static final String MOD_NAME = "BvngeeAddons";
    public static String MOD_VERSION = "unknown";

    @Override
    public void onInitialize() {

        JsonUtils.getConfigFile();

        MOD_VERSION = FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow(RuntimeException::new).getMetadata().getVersion().getFriendlyString();

        InitializationHandler.getInstance().registerInitializationHandler(() -> {
            ConfigManager.getInstance().registerConfigHandler(MOD_ID, new BvngeeAddonsConfigStorage());
            InputEventHandler.getKeybindManager().registerKeybindProvider(new KeybindProvider());

            BvngeeAddonsFeatures.initCallbacks();
        });

    }
}
