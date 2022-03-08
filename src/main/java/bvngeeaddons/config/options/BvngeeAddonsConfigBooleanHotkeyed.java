package bvngeeaddons.config.options;

import com.google.gson.JsonElement;
import fi.dy.masa.malilib.config.options.ConfigBooleanHotkeyed;

public class BvngeeAddonsConfigBooleanHotkeyed extends ConfigBooleanHotkeyed implements BvngeeAddonsIConfigBase {

    public BvngeeAddonsConfigBooleanHotkeyed(String name, boolean defaultValue, String defaultHotkey) {
        super(name, defaultValue, defaultHotkey, BVNGEEADDONS_NAMESPACE_PREFIX + name + COMMENT_SUFFIX, BVNGEEADDONS_NAMESPACE_PREFIX + name + PRETTY_NAME_SUFFIX);
    }

    @Override
    public void setValueFromJsonElement(JsonElement element){
        boolean oldValue = this.getBooleanValue();
        super.setValueFromJsonElement(element);
        if (this.getBooleanValue() != oldValue) super.onValueChanged();
    }

}
