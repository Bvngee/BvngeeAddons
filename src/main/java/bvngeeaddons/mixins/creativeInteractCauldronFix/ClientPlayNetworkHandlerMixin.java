package bvngeeaddons.mixins.creativeInteractCauldronFix;

import bvngeeaddons.util.feature.creativeInteractCauldronFix.ItemSwapReverseHelper;
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

//    @Inject(method = "onInventory", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/PlayerScreenHandler;updateSlotStacks(ILjava/util/List;Lnet/minecraft/item/ItemStack;)V"))
//    private void injected(InventoryS2CPacket packet, CallbackInfo ci) {
//        System.out.println(packet.getContents());
//    }
//
//    @Inject(method = "onInventory", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/ScreenHandler;updateSlotStacks(ILjava/util/List;Lnet/minecraft/item/ItemStack;)V"))
//    private void injected2(InventoryS2CPacket packet, CallbackInfo ci) {
//        System.out.println(packet.getContents());
//    }

    @Inject(method = "onScreenHandlerSlotUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/ScreenHandler;setStackInSlot(IILnet/minecraft/item/ItemStack;)V", shift = At.Shift.AFTER))
    private void injected3(ScreenHandlerSlotUpdateS2CPacket packet, CallbackInfo ci) {
        System.out.println("alternate packet method ig");
        ItemSwapReverseHelper.onScreenHandlerSlotUpdate(packet, this.client.player.currentScreenHandler , this.client.world, this.client.player);
    }

    @Inject(method = "onScreenHandlerSlotUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/PlayerScreenHandler;setStackInSlot(IILnet/minecraft/item/ItemStack;)V", shift = At.Shift.AFTER))
    private void screenHandlerSlotUpdate(ScreenHandlerSlotUpdateS2CPacket packet, CallbackInfo ci) {
        System.out.println("not alternate packet method ig hehe");
        ItemSwapReverseHelper.onScreenHandlerSlotUpdate(packet, this.client.player.playerScreenHandler , this.client.world, this.client.player);
    }

}