package bvngeeaddons.config.listEntries;

import fi.dy.masa.malilib.config.IConfigOptionListEntry;
import fi.dy.masa.malilib.util.StringUtils;

public enum ShownBossBarType implements IConfigOptionListEntry {

    BOTH ("both", "bvngeeaddons.config.shown_boss_bar_type.both"),
    WITHER ("wither", "bvngeeaddons.config.shown_boss_bar_type.wither"),
    DRAGON ("dragon", "bvngeeaddons.config.shown_boss_bar_type.dragon");

    private final String configString;
    private final String translationKey;

    ShownBossBarType(String configString, String translationKey) {
        this.configString = configString;
        this.translationKey = translationKey;
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
        if (forward) {
            if (++id > values().length-1) id = 0;
        } else {
            if (--id < 0) id = values().length-1;
        }
        return values()[id % values().length];
    }

    @Override
    public IConfigOptionListEntry fromString(String value) {
        for (ShownBossBarType type : ShownBossBarType.values()) {
            if (value.equalsIgnoreCase(type.configString)) return type;
        }
        return ShownBossBarType.BOTH;
    }
}
