package bvngeeaddons.mixins.screenshotUtils;

import bvngeeaddons.config.BvngeeAddonsFeatures;
import bvngeeaddons.util.feature.screenshotUtils.ScreenshotTranslatableText;
import bvngeeaddons.util.feature.screenshotUtils.ScreenshotUtils;
import net.minecraft.client.util.ScreenshotRecorder;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Debug(export = true)
@Mixin(ScreenshotRecorder.class)
public class ScreenshotRecorderMixin {

    @SuppressWarnings("unchecked")
    @ModifyArg(remap = false, method = "method_1661", at = @At(value = "INVOKE", target = "Ljava/util/function/Consumer;accept(Ljava/lang/Object;)V", ordinal = 0), index = 0)
    private static <T> T test(T t) {
        TranslatableText original = (TranslatableText) t;
        LiteralText styledFileText = (LiteralText) original.getArgs()[0];
        return (T) (BvngeeAddonsFeatures.screenshotUtils.getBooleanValue() ? new ScreenshotTranslatableText(original, ScreenshotUtils.SCREENSHOTS_DIR + styledFileText.getString(), true) : original);
    }

}