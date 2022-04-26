package bvngeeaddons.mixins.bossBarModifications;

import bvngeeaddons.config.BvngeeAddonsFeatures;
import bvngeeaddons.config.listEntries.BossBarRenderMode;
import net.minecraft.client.gui.hud.BossBarHud;
import net.minecraft.client.gui.hud.ClientBossBar;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

@Mixin(BossBarHud.class)
public class BossBarHudMixin {

    @Shadow @Final private Map<UUID, ClientBossBar> bossBars;

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;drawWithShadow(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/text/Text;FFI)I", shift = At.Shift.AFTER))
    private void injection(MatrixStack matrices, CallbackInfo ci) {
        if (BvngeeAddonsFeatures.bossBarRenderMode.getOptionListValue() == BossBarRenderMode.COMPACT) {

        }
    }

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Ljava/util/Map;values()Ljava/util/Collection;", shift = At.Shift.AFTER))
    private Iterator<?> injected(Collection instance) {
        Map<UUID, ClientBossBar> map = (Map<UUID, ClientBossBar>) instance;
    }

}
