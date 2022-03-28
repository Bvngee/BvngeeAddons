package bvngeeaddons.config.options;

import fi.dy.masa.malilib.config.options.ConfigHotkey;

public class BvngeeAddonsConfigHotkey extends ConfigHotkey implements BvngeeAddonsIConfigBase {

    public BvngeeAddonsConfigHotkey(String name, String defaultStorageString){
        super(name, defaultStorageString, BVNGEEADDONS_NAMESPACE_PREFIX + name + COMMENT_SUFFIX);
    }

}
