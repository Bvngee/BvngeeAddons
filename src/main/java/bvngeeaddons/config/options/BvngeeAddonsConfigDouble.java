package bvngeeaddons.config.options;

import com.google.gson.JsonElement;
import fi.dy.masa.malilib.config.options.ConfigDouble;

public class BvngeeAddonsConfigDouble extends ConfigDouble implements BvngeeAddonsIConfigBase {

    public BvngeeAddonsConfigDouble(String name, double defaultValue) {
        super(name, defaultValue, BVNGEEADDONS_NAMESPACE_PREFIX + name + COMMENT_SUFFIX);
    }

    public BvngeeAddonsConfigDouble(String name, double defaultValue, double minValue, double maxValue) {
        super(name, defaultValue, minValue, maxValue, BVNGEEADDONS_NAMESPACE_PREFIX + name + COMMENT_SUFFIX);
    }

    @Override
    public void setValueFromJsonElement(JsonElement element) {
        double original = this.getDoubleValue();
        super.setValueFromJsonElement(element);
        if (original != this.getDoubleValue()) this.onValueChanged();
    }

}
