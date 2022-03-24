package bvngeeaddons.config.options;

import com.google.gson.JsonElement;
import fi.dy.masa.malilib.config.ConfigType;
import fi.dy.masa.malilib.config.IConfigOptionListEntry;
import fi.dy.masa.malilib.config.options.ConfigOptionList;
import net.minecraft.client.util.math.MatrixStack;
import org.jetbrains.annotations.Nullable;

public class BvngeeAddonsConfigOptionListHotkeyed implements BvngeeAddonsIConfigBase, IConfigOptionListEntry {

    private final BvngeeAddonsConfigHotkey hotkey;
    private final String[] types;

    public BvngeeAddonsConfigOptionListHotkeyed(String name, String defaultStorageString, String[] values, String defaultValue){
        this.hotkey = new BvngeeAddonsConfigHotkey(name, defaultStorageString);
        this.types = values;
    }

    /*public BvngeeAddonsConfigOptionListHotkeyed(BvngeeAddonsConfigHotkey hotkey, Enum<? extends IConfigOptionListEntry> types){
            this.hotkey = hotkey;
            this.types = types;
        }*/

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

    @Override
    public String getStringValue() {
        return null;
    }

    @Override
    public String getDisplayName() {
        return null;
    }

    @Override
    public IConfigOptionListEntry cycle(boolean forward) {
        return null;
    }

    @Override
    public IConfigOptionListEntry fromString(String value) {
        return null;
    }
}
