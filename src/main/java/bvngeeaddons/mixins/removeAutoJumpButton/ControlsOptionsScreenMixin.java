package bvngeeaddons.mixins.removeAutoJumpButton;

import bvngeeaddons.config.BvngeeAddonsFeatures;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.ControlsOptionsScreen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(ControlsOptionsScreen.class)
public class ControlsOptionsScreenMixin extends GameOptionsScreen{
    public ControlsOptionsScreenMixin(Screen parent, GameOptions gameOptions, Text title) {
        super(parent, gameOptions, title);
    }

    /*@Redirect(method = "init()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/option/ControlsOptionsScreen;addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;", ordinal = 0))
    private Element widenMouseSettingsButton(ControlsOptionsScreen instance, Element element){
        instance.add
        return element;
    }*/

    @ModifyArgs(method = "init()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/option/ControlsOptionsScreen;addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;", ordinal = 0))
    private void widenMouseSettingsButton(Args args){
        ButtonWidget mouseSettingsButton = args.get(0);
        if (BvngeeAddonsFeatures.removeAutoJumpButton.getBooleanValue()) {
            mouseSettingsButton.x = this.width / 2 - 105;
            mouseSettingsButton.setWidth(200);
            args.set(0, mouseSettingsButton);
        }
    }

    @Redirect(method = "init()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/option/ControlsOptionsScreen;addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;", ordinal = 1))
    private Element skipAutoJumpButton(ControlsOptionsScreen instance, Element element) {
        if (!BvngeeAddonsFeatures.removeAutoJumpButton.getBooleanValue()){
            ClickableWidget autoJumpButton = (ClickableWidget) element;
            this.addDrawableChild(autoJumpButton);
        }
        return element;
    }

}
