package bvngeeaddons.config.options;

import com.google.gson.JsonElement;
import fi.dy.masa.malilib.config.IConfigOptionListEntry;
import fi.dy.masa.malilib.config.options.ConfigOptionList;

public class BvngeeAddonsConfigOptionList extends ConfigOptionList implements BvngeeAddonsIConfigBase {

    public BvngeeAddonsConfigOptionList(String name, IConfigOptionListEntry defaultValue) {
        super(name, defaultValue, BVNGEEADDONS_NAMESPACE_PREFIX + name + COMMENT_SUFFIX);
    }

    @Override
    public void setValueFromJsonElement(JsonElement element){
        IConfigOptionListEntry original = this.getOptionListValue();
        super.setValueFromJsonElement(element);
        if (this.getOptionListValue() != original) super.onValueChanged();
    }

}
