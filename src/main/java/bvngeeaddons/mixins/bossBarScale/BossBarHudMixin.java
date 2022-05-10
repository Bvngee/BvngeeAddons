package bvngeeaddons.mixins.bossBarScale;

import bvngeeaddons.config.BvngeeAddonsFeatures;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.BossBarHud;
import net.minecraft.client.util.Window;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BossBarHud.class)
public class BossBarHudMixin {

    //todo: move to another class for rendering related utils?

    @Shadow @Final private MinecraftClient client;

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;getScaledWidth()I", shift = At.Shift.BEFORE))
    private void bossBarScalePush(MatrixStack matrices, CallbackInfo ci) {
        final double factor = BvngeeAddonsFeatures.bossBarScale.getDoubleValue();
        matrices.push();
        matrices.translate(-client.getWindow().getScaledWidth() / 2d * factor, 0, 0);
        matrices.scale((float) factor, (float) factor, 1);
        matrices.translate(client.getWindow().getScaledWidth() / 2d / factor, 0, 0);
    }

    @Inject(method = "render", at = @At(value = "TAIL"))
    private void bossBarScalePop(MatrixStack matrices, CallbackInfo ci) {
        matrices.pop();
    }

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;getScaledHeight()I"))
    private int fixBossBarLimit(Window window) {
        return (int) (window.getScaledHeight() / BvngeeAddonsFeatures.bossBarScale.getDoubleValue());
    }

}
