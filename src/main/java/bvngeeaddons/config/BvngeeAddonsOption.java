package bvngeeaddons.config;

import bvngeeaddons.config.options.BvngeeAddonsIConfigBase;

public class BvngeeAddonsOption {

    private final Config type;
    private final BvngeeAddonsIConfigBase config;

    public BvngeeAddonsOption(Config type, BvngeeAddonsIConfigBase config) {
        this.type = type;
        this.config = config;
    }

    public Config.Type getType(){
        return type.value();
    }

    public Config.Category getCategory(){
        return type.category();
    }

    public BvngeeAddonsIConfigBase getConfig(){
        return config;
    }

}
