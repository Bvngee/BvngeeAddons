package bvngeeaddons.gui;

import bvngeeaddons.BvngeeAddons;
import bvngeeaddons.config.BvngeeAddonsFeaturesHandler;
import bvngeeaddons.config.Config;
import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.gui.GuiBase;
import fi.dy.masa.malilib.gui.GuiConfigsBase;
import fi.dy.masa.malilib.hotkeys.IKeybind;
import fi.dy.masa.malilib.hotkeys.KeyAction;

import java.util.List;

public class BvngeeAddonsConfigGui extends GuiConfigsBase {

    private static Config.Category category = Config.Category.FEATURES;

    public BvngeeAddonsConfigGui(){
        super(10, 50, BvngeeAddons.MOD_ID, null, "Title here", BvngeeAddons.MOD_VERSION);
    }

    @Override
    public void init(){
        super.initGui();
    }

    @Override
    public List<ConfigOptionWrapper> getConfigs() {
        List<? extends IConfigBase> configs = BvngeeAddonsFeaturesHandler.getConfigs();
        return ConfigOptionWrapper.createFor(configs);
    }

    public static boolean onOpenGui(KeyAction keyAction, IKeybind iKeybind){
        GuiBase.openGui(new BvngeeAddonsConfigGui());
        return true;
    }
}
