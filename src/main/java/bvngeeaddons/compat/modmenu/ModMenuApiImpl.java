package bvngeeaddons.compat.modmenu;

import com.terraformersmc.modmenu.api.ModMenuApi;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import bvngeeaddons.gui.BvngeeAddonsConfigGui;

public class ModMenuApiImpl implements ModMenuApi{
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory()
    {
        return (screen) -> {
            BvngeeAddonsConfigGui gui = new BvngeeAddonsConfigGui();
            gui.setParent(screen);
            return gui;
        };
    }
}