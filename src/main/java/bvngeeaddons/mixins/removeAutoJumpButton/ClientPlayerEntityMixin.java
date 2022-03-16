package bvngeeaddons.mixins.removeAutoJumpButton;

import bvngeeaddons.config.BvngeeAddonsFeatures;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {

    @Inject(method = "autoJump", at = @At("HEAD"), cancellable = true)
    private void injected(float dx, float dz, CallbackInfo ci){
        if (BvngeeAddonsFeatures.removeAutoJumpButton.getBooleanValue()) ci.cancel();
    }

}
