package bvngeeaddons.config;

import bvngeeaddons.BvngeeAddons;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fi.dy.masa.malilib.config.ConfigUtils;
import fi.dy.masa.malilib.config.IConfigHandler;
import fi.dy.masa.malilib.util.JsonUtils;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;

public class BvngeeAddonsConfigStorage implements IConfigHandler {

    //private static JsonObject ROOT_JSON_OBJ = new JsonObject();

    @Override
    public void load() {
        File configFile = getConfigFile();
        if (configFile.exists() && configFile.isFile() && configFile.canRead()){
            JsonElement element = JsonUtils.parseJsonFile(configFile);
            if (element != null && element.isJsonObject()){
                JsonObject root = element.getAsJsonObject();

                ConfigUtils.readConfigBase(root, "Generic", BvngeeAddonsFeaturesHandler.getFeaturesOfType(Config.Type.GENERIC));
                ConfigUtils.readConfigBase(root, "GenericHotkeys", BvngeeAddonsFeaturesHandler.getFeaturesOfType(Config.Type.HOTKEY));
                ConfigUtils.readConfigBase(root, "List", BvngeeAddonsFeaturesHandler.getFeaturesOfType(Config.Type.LIST));
                ConfigUtils.readHotkeyToggleOptions(root, "TweakHotkey", "TweakToggles", BvngeeAddonsFeaturesHandler.getFeaturesOfType(Config.Type.TWEAK));
                ConfigUtils.readHotkeyToggleOptions(root, "TweakHotkey", "TweakToggles", BvngeeAddonsFeaturesHandler.getFeaturesOfType(Config.Type.TWEAK));
            }
        }
    }

    @Override
    public void save() {
        JsonObject root = new JsonObject();

        ConfigUtils.writeConfigBase(root, "Generic", BvngeeAddonsFeaturesHandler.getFeaturesOfType(Config.Type.GENERIC));
        ConfigUtils.writeConfigBase(root, "GenericHotkeys", BvngeeAddonsFeaturesHandler.getFeaturesOfType(Config.Type.HOTKEY));
        ConfigUtils.writeConfigBase(root, "List", BvngeeAddonsFeaturesHandler.getFeaturesOfType(Config.Type.LIST));
        ConfigUtils.writeHotkeyToggleOptions(root, "TweakHotkey", "TweakToggles", BvngeeAddonsFeaturesHandler.getFeaturesOfType(Config.Type.TWEAK));
        ConfigUtils.writeHotkeyToggleOptions(root, "DisableHotkey", "TweakToggles", BvngeeAddonsFeaturesHandler.getFeaturesOfType(Config.Type.DISABLE));

        JsonUtils.writeJsonToFile(root, getConfigFile());
    }

    //possibly move to separate util class if needed later on
    public static File getConfigFile() {
        return FabricLoader.getInstance().getConfigDir().resolve(BvngeeAddons.MOD_ID + ".json").toFile();
    }


}