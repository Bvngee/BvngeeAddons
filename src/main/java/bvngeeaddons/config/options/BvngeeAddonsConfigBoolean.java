package bvngeeaddons.config.options;

import com.google.gson.JsonElement;
import fi.dy.masa.malilib.config.IConfigBoolean;
import fi.dy.masa.malilib.config.options.ConfigBoolean;

public class BvngeeAddonsConfigBoolean extends ConfigBoolean implements BvngeeAddonsIConfigBase {

    public BvngeeAddonsConfigBoolean(String name, boolean defaultValue) {
        super(name, defaultValue, BVNGEEADDONS_NAMESPACE_PREFIX + name + COMMENT_SUFFIX);
    }

    @Override
    public void setValueFromJsonElement(JsonElement element){
        boolean original = this.getBooleanValue();
        super.setValueFromJsonElement(element);
        if (original != this.getBooleanValue()) this.onValueChanged();
    }
}
