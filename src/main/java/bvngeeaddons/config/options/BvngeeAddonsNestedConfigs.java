package bvngeeaddons.config.options;

import bvngeeaddons.config.Config;
import com.google.gson.JsonElement;
import fi.dy.masa.malilib.config.ConfigType;
import org.jetbrains.annotations.Nullable;

public class BvngeeAddonsNestedConfigs implements BvngeeAddonsIConfigBase {

    public final BvngeeAddonsIConfigBase config1;
    public final BvngeeAddonsIConfigBase config2;
//    private final String name;

    public BvngeeAddonsNestedConfigs(BvngeeAddonsIConfigBase config1, BvngeeAddonsIConfigBase config2) {
        this.config1 = config1;
        this.config2 = config2;
//        this.name = name;
    }

    @Override
    public ConfigType getType() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Nullable
    @Override
    public String getComment() {
        return null;
    }

    @Override
    public void setValueFromJsonElement(JsonElement element) {

    }

    @Override
    public JsonElement getAsJsonElement() {
        return null;
    }

//    public String getName() {
//        return this.name;
//    }

}
