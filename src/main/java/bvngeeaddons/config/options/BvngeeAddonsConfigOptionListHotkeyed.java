package bvngeeaddons.config.options;

import com.google.gson.JsonElement;
import fi.dy.masa.malilib.config.IConfigOptionListEntry;
import fi.dy.masa.malilib.config.options.ConfigOptionList;

public class BvngeeAddonsConfigOptionListHotkeyed extends ConfigOptionList implements BvngeeAddonsIConfigBase {

    private final BvngeeAddonsConfigHotkey hotkey;

    public BvngeeAddonsConfigOptionListHotkeyed(String name, IConfigOptionListEntry defaultValue, BvngeeAddonsConfigHotkey hotkey) {
        super(name, defaultValue, BVNGEEADDONS_NAMESPACE_PREFIX + name + COMMENT_SUFFIX);
        this.hotkey = hotkey;
    }

    public BvngeeAddonsConfigHotkey getHotkey() {
        return this.hotkey;
    }

    @Override
    public void setValueFromJsonElement(JsonElement element){
        IConfigOptionListEntry original = this.getOptionListValue();
        super.setValueFromJsonElement(element);
        if (this.getOptionListValue() != original) super.onValueChanged();
    }
}
