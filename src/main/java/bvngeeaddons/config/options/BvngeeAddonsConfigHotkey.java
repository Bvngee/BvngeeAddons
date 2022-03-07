package bvngeeaddons.config.options;

import fi.dy.masa.malilib.config.options.ConfigHotkey;
import fi.dy.masa.malilib.hotkeys.KeybindSettings;

public class BvngeeAddonsConfigHotkey extends ConfigHotkey implements BvngeeAddonsIConfigBase {

    public BvngeeAddonsConfigHotkey(String name, String defaultStorageString){
        super(name, defaultStorageString, BVNGEEADDONS_NAMESPACE_PREFIX + name + COMMENT_SUFFIX);
    }

    public BvngeeAddonsConfigHotkey(String name, String defaultStorageString, KeybindSettings settings){
        super(name, defaultStorageString, KeybindSettings.DEFAULT, BVNGEEADDONS_NAMESPACE_PREFIX + name + COMMENT_SUFFIX);
    }

}
