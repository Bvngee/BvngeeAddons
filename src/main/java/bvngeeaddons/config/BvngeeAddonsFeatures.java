package bvngeeaddons.config;

import bvngeeaddons.config.options.BvngeeAddonsConfigBoolean;
import bvngeeaddons.config.options.BvngeeAddonsConfigHotkey;
import bvngeeaddons.config.options.BvngeeAddonsConfigInteger;
import bvngeeaddons.config.options.BvngeeAddonsIConfigBase;
import bvngeeaddons.gui.BvngeeAddonsConfigGui;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class BvngeeAddonsFeatures {

    /*public static final FIXES
        BvngeeAddonsConfigInteger TEST_INTEGER = new BvngeeAddonsConfigInteger("test", 0, 0, 100, true);*/


    //----------------FIXES-----------------

    @Config(value = Config.Type.DISABLE, category = Config.Category.FIXES)
    public static final BvngeeAddonsConfigBoolean emptyBottleFix = new BvngeeAddonsConfigBoolean("emptyBottleFix", false);

    //---------------FEATURES---------------

    @Config(value = Config.Type.GENERIC, category = Config.Category.FEATURES)
    public static final BvngeeAddonsConfigInteger TEST_INTEGER = new BvngeeAddonsConfigInteger("test", 0, 0, 100, true);

    //---------------HOTKEYS----------------

    @Config(value = Config.Type.HOTKEY, category = Config.Category.SETTING)
    public static final BvngeeAddonsConfigHotkey BVNGEE_ADDONS_OPEN_GUI = new BvngeeAddonsConfigHotkey("openBvngeeAddonsGui", "B,C");

    private static final List<BvngeeAddonsOption> bvngeeAddonsOptions = initOptions();

    public static void initCallbacks(){

        //Hotkeys
        BVNGEE_ADDONS_OPEN_GUI.getKeybind().setCallback(BvngeeAddonsConfigGui::onOpenGui);

    }

    private static List<BvngeeAddonsOption> initOptions(){
        List<BvngeeAddonsOption> options = new ArrayList<>();
        for (Field field : BvngeeAddonsFeatures.class.getDeclaredFields()){
             Config annotation = field.getAnnotation(Config.class);
             if(annotation != null){
                 try{
                     Object config = field.get(null);
                     if (config instanceof BvngeeAddonsIConfigBase){
                         options.add(new BvngeeAddonsOption(annotation, (BvngeeAddonsIConfigBase) config));
                     }
                 }catch (IllegalAccessException e){
                     e.printStackTrace();
                 }

             }
        }
        return options;
    }

    public static List<BvngeeAddonsOption> getOptions(){
        return bvngeeAddonsOptions;
    }
}
