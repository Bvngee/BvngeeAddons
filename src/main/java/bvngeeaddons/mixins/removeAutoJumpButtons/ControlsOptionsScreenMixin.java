package bvngeeaddons.mixins.removeAutoJumpButtons;

import bvngeeaddons.config.BvngeeAddonsFeatures;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.option.ControlsOptionsScreen;
import net.minecraft.client.gui.screen.option.MouseOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.Option;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ControlsOptionsScreen.class)
public class ControlsOptionsScreenMixin {

    @Redirect(method = "init()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/option/ControlsOptionsScreen;addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;", ordinal = 0))
    private Element widenMouseControlsButtons(ControlsOptionsScreen instance, Element element) {

        //return element;

        /*if (BvngeeAddonsFeatures.removeAutoJumpButtons.getBooleanValue()){
            System.out.println("feature is on");
            ClickableWidget widerMouseOptionsButton = new ButtonWidget(
                    instance.width / 2 - 75, 18, 150, 20,
                    new TranslatableText("options.mouse_settings"),
                    button -> MinecraftClient.getInstance().setScreen(new MouseOptionsScreen(instance, MinecraftClient.getInstance().options))
            );
            //instance.addSelectableChild(widerMouseOptionsButton);
            return (T) widerMouseOptionsButton;
        } else {
            return element;
        }*/



        return BvngeeAddonsFeatures.removeAutoJumpButtons.getBooleanValue() ?
                new ButtonWidget(
                        instance.width / 2 - 75, 18, 150, 20,
                        new TranslatableText("options.mouse_settings"),
                        button -> MinecraftClient.getInstance().setScreen(new MouseOptionsScreen(instance, MinecraftClient.getInstance().options))
                )
                :
                element;
    }

    @Redirect(method = "init()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/option/ControlsOptionsScreen;addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;", ordinal = 1))
    private Element skipAutoJumpButton(ControlsOptionsScreen instance, Element element) {
        return BvngeeAddonsFeatures.removeAutoJumpButtons.getBooleanValue() ? null : element;
    }

}
