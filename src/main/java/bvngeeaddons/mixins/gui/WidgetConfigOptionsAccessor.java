package bvngeeaddons.mixins.gui;

import fi.dy.masa.malilib.gui.GuiConfigsBase;
import fi.dy.masa.malilib.gui.widgets.WidgetListConfigOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(WidgetListConfigOptions.class)
public interface WidgetConfigOptionsAccessor {
    @Accessor(remap = false)
    GuiConfigsBase getParent();
}
