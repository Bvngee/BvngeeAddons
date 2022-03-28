package bvngeeaddons.mixins.gui;

import bvngeeaddons.config.options.BvngeeAddonsConfigOptionListHotkeyed;
import bvngeeaddons.gui.BvngeeAddonsConfigGui;
import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.config.IConfigOptionList;
import fi.dy.masa.malilib.config.IConfigResettable;
import fi.dy.masa.malilib.gui.GuiConfigsBase;
import fi.dy.masa.malilib.gui.button.ButtonBase;
import fi.dy.masa.malilib.gui.button.ConfigButtonKeybind;
import fi.dy.masa.malilib.gui.button.ConfigButtonOptionList;
import fi.dy.masa.malilib.gui.interfaces.IKeybindConfigGui;
import fi.dy.masa.malilib.gui.widgets.*;
import fi.dy.masa.malilib.hotkeys.IKeybind;
import fi.dy.masa.malilib.hotkeys.KeybindSettings;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(WidgetConfigOption.class)
public abstract class WidgetConfigOptionMixin extends WidgetConfigOptionBase<GuiConfigsBase.ConfigOptionWrapper> {

    @Shadow(remap = false) @Final protected IKeybindConfigGui host;

    @Shadow(remap = false) @Final protected GuiConfigsBase.ConfigOptionWrapper wrapper;

    @Mutable
    @Shadow(remap = false) @Final protected KeybindSettings initialKeybindSettings;

    @Shadow(remap = false) protected abstract void addConfigButtonEntry(int xReset, int yReset, IConfigResettable config, ButtonBase optionButton);

    @Unique
    private String defaultValue;

    public WidgetConfigOptionMixin(int x, int y, int width, int height, WidgetListConfigOptionsBase<?, ?> parent, GuiConfigsBase.ConfigOptionWrapper entry, int listIndex) {
        super(x, y, width, height, parent, entry, listIndex);
    }

    @Inject(method = "<init>", at = @At("TAIL"), remap = false)
    private void setInitialState(CallbackInfo ci) {
        if (isBvngeeAddonsConfigGui() && this.wrapper.getType() == GuiConfigsBase.ConfigOptionWrapper.Type.CONFIG) {
            IConfigBase config = wrapper.getConfig();
            if (config instanceof BvngeeAddonsConfigOptionListHotkeyed){
                this.initialStringValue = (((BvngeeAddonsConfigOptionListHotkeyed) config).getHotkey()).getStringValue();
                this.initialKeybindSettings = ((BvngeeAddonsConfigOptionListHotkeyed) config).getHotkey().getKeybind().getSettings();
                this.defaultValue = ((BvngeeAddonsConfigOptionListHotkeyed) config).getOptionListValue().getStringValue();
            }
        }
    }

    @Inject(method = "addConfigOption", at = @At(value = "FIELD", target = "Lfi/dy/masa/malilib/config/ConfigType;OPTION_LIST:Lfi/dy/masa/malilib/config/ConfigType;", remap = false), remap = false, cancellable = true)
    private void addOptionListHotkeyed(int x, int y, float zLevel, int labelWidth, int configWidth, IConfigBase config, CallbackInfo ci) {
        if (isBvngeeAddonsConfigGui() && config instanceof BvngeeAddonsConfigOptionListHotkeyed) {
            this.addConfigButtonEntry(x + configWidth + 4, y, (IConfigResettable) config, new ConfigButtonOptionList(x, y, (configWidth/2) - 8, 20, (IConfigOptionList) config));

            configWidth -= 25;
            x += configWidth + 4;
            IKeybind keybind = ((BvngeeAddonsConfigOptionListHotkeyed) config).getHotkey().getKeybind();
            this.addWidget(new WidgetKeybindSettings(x, y, 20, 20, keybind, config.getName(), this.parent, this.host.getDialogHandler()));
            x += 2 - (configWidth/2);
            ConfigButtonKeybind keybindButton = new ConfigButtonKeybind(x, y, (configWidth/2) - 4, 20, keybind, this.host);
            this.addButton(keybindButton, this.host.getButtonPressListener());

            ci.cancel();
        }

    }

    @Inject(method = "wasConfigModified", at = @At(value = "INVOKE", target = "Lfi/dy/masa/malilib/gui/GuiConfigsBase$ConfigOptionWrapper;getConfig()Lfi/dy/masa/malilib/config/IConfigBase;", ordinal = 0, remap = false), cancellable = true, remap = false)
    private void customResetListener(CallbackInfoReturnable<Boolean> cir){
        IConfigBase config = this.wrapper.getConfig();
        if (config instanceof BvngeeAddonsConfigOptionListHotkeyed){
            BvngeeAddonsConfigOptionListHotkeyed optionList = (BvngeeAddonsConfigOptionListHotkeyed) config;
            IKeybind keybind = ((BvngeeAddonsConfigOptionListHotkeyed) config).getHotkey().getKeybind();
            cir.setReturnValue(
                    !Objects.equals(this.defaultValue, optionList.getStringValue())
                    || !Objects.equals(this.initialStringValue, keybind.getStringValue())
                    || !Objects.equals(this.initialKeybindSettings, keybind.getSettings())
            );
            System.out.println(this.defaultValue + " " + optionList.getStringValue());
            System.out.println(this.initialStringValue + " " + this.initialKeybindSettings);
            System.out.println(cir.getReturnValue());
        }
    }

    private boolean isBvngeeAddonsConfigGui() {
        return this.parent instanceof WidgetListConfigOptions && ((WidgetConfigOptionsAccessor)this.parent).getParent() instanceof BvngeeAddonsConfigGui;
    }

}
