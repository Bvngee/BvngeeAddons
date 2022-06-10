package bvngeeaddons.mixins.screenshotUtils;

import bvngeeaddons.util.chat.EditableChatHudLine;
import bvngeeaddons.util.feature.screenshotUtils.ScreenshotTranslatableText;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.ChatHudLine;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ChatHud.class)
public class ChatHudMixin {

    @Shadow @Final private MinecraftClient client;

    @SuppressWarnings("unchecked")
    @ModifyArg(method = "addMessage(Lnet/minecraft/text/Text;IIZ)V", at = @At(value = "INVOKE", target = "Ljava/util/List;add(ILjava/lang/Object;)V", ordinal = 1), index = 1)
    private <E> E onAddMessage(E e) {
        ChatHudLine<TranslatableText> original = (ChatHudLine<TranslatableText>) e;
        if (original.getText() instanceof ScreenshotTranslatableText text) {
            EditableChatHudLine<ScreenshotTranslatableText> newChatHudLine = new EditableChatHudLine<>(client.inGameHud.getTicks(), text, 0);
            text.setParentChatHudLine(newChatHudLine);
            return (E) newChatHudLine;
        }
        return e;
    }

}