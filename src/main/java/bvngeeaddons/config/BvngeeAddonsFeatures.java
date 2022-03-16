package bvngeeaddons.config;

import bvngeeaddons.config.options.*;
import bvngeeaddons.gui.BvngeeAddonsConfigGui;

public class BvngeeAddonsFeatures {


    //----------------FIXES-----------------

    @Config(type = Config.Type.GENERIC, category = Config.Category.FIXES)
    public static final BvngeeAddonsConfigBoolean creativeInteractCauldronFix = new BvngeeAddonsConfigBoolean("emptyBottleFix", false);

    //---------------FEATURES---------------

    @Config(type = Config.Type.GENERIC, category = Config.Category.FEATURES)
    public static final BvngeeAddonsConfigBoolean removeAutoJumpButton = new BvngeeAddonsConfigBoolean("removeAutoJumpButtons", false);

    //@Config(type = Config.Type.GENERIC, category = Config.Category.FEATURES)
    //public static final BvngeeAddonsConfigInteger TEST_INTEGER = new BvngeeAddonsConfigInteger("testInt", 0, 0, 100, false);

    //@Config(type = Config.Type.LIST, category = Config.Category.FEATURES)
    //public static final BvngeeAddonsConfigOptionList TEST_LIST = new BvngeeAddonsConfigOptionList("testList", UsageRestriction.ListType.NONE);

    //@Config(type = Config.Type.TWEAK, category = Config.Category.FEATURES)
    //public static final BvngeeAddonsConfigBooleanHotkeyed TEST_BOOL_HOTKEYED = new BvngeeAddonsConfigBooleanHotkeyed("testBoolHotkeyed_BY", false, "B,Y");

    //@Config(type = Config.Type.DISABLE, category = Config.Category.FEATURES)
    //public static final BvngeeAddonsConfigBooleanHotkeyed TEST_BOOL_HOTKEYED2 = new BvngeeAddonsConfigBooleanHotkeyed("testBoolHotkeyed2_BN", false, "B,N");

    //---------------SETTINGS----------------

    @Config(type = Config.Type.HOTKEY, category = Config.Category.SETTINGS)
    public static final BvngeeAddonsConfigHotkey BVNGEE_ADDONS_OPEN_GUI = new BvngeeAddonsConfigHotkey("openBvngeeAddonsGui", "B,C");

//    static {
//        BvngeeAddonsFeaturesHandler.initOptions();
//    }

    //move to handler? not sure yet
    public static void initCallbacks(){

        //Hotkeys
        BVNGEE_ADDONS_OPEN_GUI.getKeybind().setCallback(BvngeeAddonsConfigGui::onOpenGui);

    }

}
