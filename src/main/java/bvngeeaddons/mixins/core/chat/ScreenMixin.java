package bvngeeaddons.mixins.core.chat;

import bvngeeaddons.util.chat.FunctionClickEvent;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.Style;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Screen.class)
public class ScreenMixin {

    @Inject(method = "handleTextClick", at = @At("HEAD"), cancellable = true)
    private void customOnClickHook(Style style, CallbackInfoReturnable<Boolean> cir){
        ClickEvent clickEvent = style.getClickEvent();
        if (clickEvent instanceof FunctionClickEvent) {
            ((FunctionClickEvent<?>) clickEvent).runFunc();
            cir.setReturnValue(true);
        }
    }

}
