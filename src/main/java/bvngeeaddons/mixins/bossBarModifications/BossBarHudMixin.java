package bvngeeaddons.mixins.bossBarModifications;

import bvngeeaddons.config.BvngeeAddonsFeatures;
import bvngeeaddons.config.listEntries.BossBarRenderMode;
import net.minecraft.client.gui.hud.BossBarHud;
import net.minecraft.client.gui.hud.ClientBossBar;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.*;

@Mixin(BossBarHud.class)
public class BossBarHudMixin {

    @Shadow @Final private Map<UUID, ClientBossBar> bossBars;

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;getScaledWidth()I", shift = At.Shift.AFTER))
    private void injection(MatrixStack matrices, CallbackInfo ci) {
        System.out.println("hi");
        //do stuff with bossBars here kinda like below but better
    }

    /*@Redirect(method = "render", at = @At(value = "INVOKE", target = "Ljava/util/Map;values()Ljava/util/Collection;", shift = At.Shift.AFTER))
    private Iterator<?> injected(Collection values) {
        Map<UUID, ClientBossBar> map = (Map<UUID, ClientBossBar>) values;
        Map<UUID, ClientBossBar> compactMap = new LinkedHashMap<>();
        if (BvngeeAddonsFeatures.bossBarRenderMode.getOptionListValue() == BossBarRenderMode.COMPACT) {
            Iterator<UUID> keys = map.keySet().iterator();
            for (int i = 0; i < 3; i++) {
                UUID uuid = keys.next();
                compactMap.put(uuid, map.get(uuid));
            }
        }
        return compactMap.values().iterator();
    }*/

}
