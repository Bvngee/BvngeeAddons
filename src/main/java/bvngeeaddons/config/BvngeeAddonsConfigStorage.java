package bvngeeaddons.config;

import bvngeeaddons.config.options.BvngeeAddonsIConfigBase;
import com.google.gson.JsonObject;
import fi.dy.masa.malilib.config.ConfigUtils;
import fi.dy.masa.malilib.config.IConfigHandler;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BvngeeAddonsConfigStorage implements IConfigHandler {

    //private static JsonObject ROOT_JSON_OBJ = new JsonObject();

    public static void saveToFile(){

        JsonObject root = new JsonObject();
        //final List<BvngeeAddonsOption> options = BvngeeAddonsFeatures.getOptions();

        //final List<BvngeeAddonsOption> featureOptions = options.stream().filter(k -> k.getType() == Config.Type.GENERIC).toList();
        //final List<BvngeeAddonsOption> fixOptions = options.stream().filter(k -> k.getType() == Config.Type.DISABLE).toList();
        //final List<BvngeeAddonsOption> settingOptions = options.stream().filter(k -> k.getType() == Config.Type.LIST).toList();
        //final List<BvngeeAddonsOption> hotkeyOptions = options.stream().filter(k -> k.getType() == Config.Type.HOTKEY).toList();

        //ConfigUtils.writeConfigBase(root, "Features", featureOptions);

    }

    @Override
    public void load() {

    }

    @Override
    public void save() {

    }


}
