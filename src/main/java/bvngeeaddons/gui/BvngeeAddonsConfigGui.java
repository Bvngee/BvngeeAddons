package bvngeeaddons.gui;

import bvngeeaddons.BvngeeAddons;
import bvngeeaddons.config.BvngeeAddonsFeaturesHandler;
import bvngeeaddons.config.Config;
import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.gui.GuiBase;
import fi.dy.masa.malilib.gui.GuiConfigsBase;
import fi.dy.masa.malilib.gui.button.ButtonBase;
import fi.dy.masa.malilib.gui.button.ButtonGeneric;
import fi.dy.masa.malilib.hotkeys.IKeybind;
import fi.dy.masa.malilib.hotkeys.KeyAction;

import java.util.List;

public class BvngeeAddonsConfigGui extends GuiConfigsBase {

    private static Config.Category currentCategory = Config.Category.FEATURES;

    public BvngeeAddonsConfigGui(){
        super(10, 50, BvngeeAddons.MOD_ID, null, "BvngeeAddons Configs - v" + BvngeeAddons.MOD_VERSION, BvngeeAddons.MOD_VERSION);
    }

    @Override
    public void initGui(){
        super.initGui();
        this.clearOptions();

        int x = 10;
        for (Config.Category category : Config.Category.values()){
            ButtonBase button = new ButtonGeneric(x, 26, -1, 20, category.getDisplayName());
            button.setEnabled(currentCategory != category);
            this.addButton(button, (b, mouseButton) -> {
                currentCategory = category;
                this.initGui();
            });

            x += button.getWidth() + 2;
        }

    }

    @Override
    public List<ConfigOptionWrapper> getConfigs() {
        List<? extends IConfigBase> configs = BvngeeAddonsFeaturesHandler.getFeaturesOfCategory(currentCategory);
        return ConfigOptionWrapper.createFor(configs);
    }

    public static boolean onOpenGui(KeyAction keyAction, IKeybind iKeybind){
        GuiBase.openGui(new BvngeeAddonsConfigGui());
        return true;
    }
}
