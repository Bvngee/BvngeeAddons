package bvngeeaddons.config.options;

import com.google.gson.JsonElement;
import fi.dy.masa.malilib.config.ConfigType;
import org.jetbrains.annotations.Nullable;

public class BvngeeAddonsNestedConfigs {

    private final BvngeeAddonsIConfigBase config1;
    private final BvngeeAddonsIConfigBase config2;
    private final String name;

    public BvngeeAddonsNestedConfigs(String name, BvngeeAddonsIConfigBase config1, BvngeeAddonsIConfigBase config2){
        this.config1 = config1;
        this.config2 = config2;
        this.name = name;
    }

    public BvngeeAddonsIConfigBase getConfig(int config) {
        switch(config) {
            case 1 -> { return config1; }
            case 2 -> { return config2; }
            default -> { return null; }
        }
    }

    public String getName() {
        return this.name;
    }

}
