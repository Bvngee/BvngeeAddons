package bvngeeaddons;

import bvngeeaddons.config.BvngeeAddonsConfigStorage;
import bvngeeaddons.config.BvngeeAddonsFeatures;
import bvngeeaddons.config.BvngeeAddonsFeaturesHandler;
import bvngeeaddons.config.KeybindProvider;
import fi.dy.masa.malilib.config.ConfigManager;
import fi.dy.masa.malilib.event.InitializationHandler;
import fi.dy.masa.malilib.event.InputEventHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BvngeeAddons implements ClientModInitializer {

    public static final String MOD_ID = "bvngeeaddons";
    public static final String MOD_NAME = "BvngeeAddons";
    public static String MOD_VERSION = "unknown";

    private static final Logger LOGGER = LogManager.getLogger("BvngeeAddons");

    @Override
    public void onInitializeClient() {

        LOGGER.info("BvngeeAddons Initialized");

        MOD_VERSION = FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow(RuntimeException::new).getMetadata().getVersion().getFriendlyString();

        InitializationHandler.getInstance().registerInitializationHandler(() -> {
            BvngeeAddonsFeaturesHandler.initOptions();

            ConfigManager.getInstance().registerConfigHandler(MOD_ID, new BvngeeAddonsConfigStorage());
            InputEventHandler.getKeybindManager().registerKeybindProvider(new KeybindProvider());

            BvngeeAddonsFeatures.initCallbacks();
        });

    }

    public static Logger getLogger() {
        return LOGGER;
    }
}
