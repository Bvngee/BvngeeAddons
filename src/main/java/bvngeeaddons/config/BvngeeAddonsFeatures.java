package bvngeeaddons.config;

import bvngeeaddons.config.listEntries.BossBarRenderMode;
import bvngeeaddons.config.options.*;
import bvngeeaddons.gui.BvngeeAddonsConfigGui;

public class BvngeeAddonsFeatures {


    //----------------FIXES-----------------

    @Config(type = Config.Type.GENERIC, category = Config.Category.FIXES)
    public static final BvngeeAddonsConfigBoolean creativeInteractCauldronFix = new BvngeeAddonsConfigBoolean("emptyBottleFix", false);


    //---------------FEATURES---------------

    @Config(type = Config.Type.GENERIC, category = Config.Category.FEATURES)
    public static final BvngeeAddonsConfigBoolean removeAutoJumpButton = new BvngeeAddonsConfigBoolean("removeAutoJumpButtons", false);

    @Config(type = Config.Type.GENERIC, category = Config.Category.FEATURES)
    public static final BvngeeAddonsConfigOptionList bossBarRenderMode_old = new BvngeeAddonsConfigOptionList("bossBarRenderMode", BossBarRenderMode.DEFAULT);
    //@Config(type = Config.Type.GENERIC, category = Config.Category.FEATURES)
    //public static final BvngeeAddonsConfigOptionListHotkeyed bossBarRenderMode = new BossBarRende


    //---------------SETTINGS----------------

    @Config(type = Config.Type.HOTKEY, category = Config.Category.SETTINGS)
    public static final BvngeeAddonsConfigHotkey bvngeeAddonsOpenGui = new BvngeeAddonsConfigHotkey("openBvngeeAddonsGui", "B,C");


    //move to handler? not sure yet
    public static void initCallbacks(){

        //Hotkeys
        bvngeeAddonsOpenGui.getKeybind().setCallback(BvngeeAddonsConfigGui::onOpenGui);


    }

}
