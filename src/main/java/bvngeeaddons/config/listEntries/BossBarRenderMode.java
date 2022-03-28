package bvngeeaddons.config.listEntries;

import bvngeeaddons.config.options.BvngeeAddonsConfigHotkey;
import fi.dy.masa.malilib.config.IConfigOptionListEntry;
import fi.dy.masa.malilib.util.StringUtils;

import java.util.Arrays;

public enum BossBarRenderMode implements IConfigOptionListEntry {
    DEFAULT ("default", "bvngeeaddons.config.boss_bar_render_mode.default"),
    COMPACT ("compact", "bvngeeaddons.config.boss_bar_render_mode.compact"),
    NONE ("none", "bvngeeaddons.config.boss_bar_render_mode.none");

    private final String configString;
    private final String translationKey;
    public final BvngeeAddonsConfigHotkey hotkey;

    BossBarRenderMode(String configString, String translationKey) {
        this.configString = configString;
        this.translationKey = translationKey;
        this.hotkey = new BvngeeAddonsConfigHotkey(getDisplayName(), "");
    }

    public BvngeeAddonsConfigHotkey getHotkey() {
        return hotkey;
    }

    public static String[] stringValues() {
        return (String[]) Arrays.stream(values()).map(BossBarRenderMode::getStringValue).toArray();
    }

    @Override
    public String getStringValue() {
        return configString;
    }

    @Override
    public String getDisplayName() {
        return StringUtils.translate(translationKey);
    }

    @Override
    public IConfigOptionListEntry cycle(boolean forward) {
        int id = this.ordinal();
        if (forward){
            if (++id > values().length-1) id = 0;
        }else {
            if (--id < 0) id = values().length-1;
        }
        return values()[id % values().length];
    }

    @Override
    public IConfigOptionListEntry fromString(String value) {
        for (BossBarRenderMode mode : BossBarRenderMode.values()){
            if (value.equalsIgnoreCase(mode.configString)) return mode;
        }
        return BossBarRenderMode.DEFAULT;
    }

}
