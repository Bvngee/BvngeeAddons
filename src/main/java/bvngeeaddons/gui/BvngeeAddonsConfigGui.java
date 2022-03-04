package bvngeeaddons.gui;

import bvngeeaddons.BvngeeAddons;
import fi.dy.masa.malilib.gui.GuiConfigsBase;

public class BvngeeAddonsConfigGui extends GuiConfigsBase {

    public BvngeeAddonsConfigGui(){
        super(10, 50, BvngeeAddons.MOD_ID, null, "TITELLL", BvngeeAddons.MOD_VERSION);
    }

    @Override
    public void init(){
        super.initGui();
    }

}
