package bvngeeaddons.utils;

import bvngeeaddons.BvngeeAddons;
import bvngeeaddons.config.BvngeeAddonsFeatures;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.IOException;

public class JsonUtils {

    private static final String CONFIG_FILE_NAME = BvngeeAddons.MOD_ID + ".json";

    public static void getConfigFile(){
        File pathToConfig = FabricLoader.getInstance().getConfigDir().resolve(CONFIG_FILE_NAME).toFile();

//        try{
//            pathToConfig.createNewFile();
//            BvngeeAddonsFeatures.getOptions()
//
//        } catch (IOException e){
//            e.printStackTrace();
//        }
    }

    public static void createConfigFileIfAbsent(){

    }
}
