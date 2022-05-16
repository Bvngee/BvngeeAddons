package bvngeeaddons.mixins.creativeInteractCauldronMultiplayerFix;

import bvngeeaddons.util.feature.creativeInteractCauldronMultiplayerFix.ItemSwapReverseHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.ScreenHandlerSlotUpdateS2CPacket;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {

    @Shadow @Final private MinecraftClient client;

    @Inject(method = "onScreenHandlerSlotUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/PlayerScreenHandler;setStackInSlot(IILnet/minecraft/item/ItemStack;)V", shift = At.Shift.AFTER))
    private void afterSetStackInSlot1(ScreenHandlerSlotUpdateS2CPacket packet, CallbackInfo ci) {
        ItemSwapReverseHelper.onScreenHandlerSlotUpdate(packet, this.client);
    }

    @Inject(method = "onScreenHandlerSlotUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/ScreenHandler;setStackInSlot(IILnet/minecraft/item/ItemStack;)V", shift = At.Shift.AFTER))
    private void afterSetStackInSlot2(ScreenHandlerSlotUpdateS2CPacket packet, CallbackInfo ci) {
        ItemSwapReverseHelper.onScreenHandlerSlotUpdate(packet, this.client);
    }

}