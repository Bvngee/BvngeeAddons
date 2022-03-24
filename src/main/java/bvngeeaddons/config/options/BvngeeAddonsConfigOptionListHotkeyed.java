package bvngeeaddons.config.options;

import com.google.gson.JsonElement;
import fi.dy.masa.malilib.config.ConfigType;
import fi.dy.masa.malilib.config.IConfigOptionListEntry;
import net.minecraft.client.util.math.MatrixStack;
import org.jetbrains.annotations.Nullable;

public class BvngeeAddonsConfigOptionListHotkeyed implements BvngeeAddonsIConfigBase {

    private final BvngeeAddonsConfigHotkey hotkey;
    private final String[] types;

    public BvngeeAddonsConfigOptionListHotkeyed(BvngeeAddonsConfigHotkey hotkey, Enum<? extends IConfigOptionListEntry> types){
        this.hotkey = hotkey;
        this.types = ;
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
}
