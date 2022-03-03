package bvngeeaddons.mixins.emptyBottleFix;

import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {

    /*@Inject(method = "interactItem", at = @At("RETURN"), cancellable = true)
    private void injected(CallbackInfoReturnable<ActionResult> cir){
        System.out.println(cir.getReturnValue().toString());
        cir.setReturnValue(ActionResult.PASS);
    }*/

    @ModifyArg(method = "interactItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;setStackInHand(Lnet/minecraft/util/Hand;Lnet/minecraft/item/ItemStack;)V"), index = 1)
    private ItemStack injected(ItemStack itemStack){
        return
    }

}
