package bvngeeaddons.config.options;

import com.google.gson.JsonElement;
import fi.dy.masa.malilib.config.options.ConfigInteger;

public class BvngeeAddonsConfigInteger extends ConfigInteger implements BvngeeAddonsIConfigBase {

    public BvngeeAddonsConfigInteger(String name, int defaultValue, int minValue, int maxValue, boolean useSlider) {
        super(name, defaultValue, minValue, maxValue, useSlider, BVNGEEADDONS_NAMESPACE_PREFIX + name + COMMENT_SUFFIX);
    }

    public BvngeeAddonsConfigInteger(String name, int defaultValue){
        super(name, defaultValue, BVNGEEADDONS_NAMESPACE_PREFIX + name + COMMENT_SUFFIX);
    }

    @Override
    public void setValueFromJsonElement(JsonElement element){
        int oldValue = this.getIntegerValue();
        super.setValueFromJsonElement(element);
        if (oldValue != this.getIntegerValue()) this.onValueChanged();
    }
}
