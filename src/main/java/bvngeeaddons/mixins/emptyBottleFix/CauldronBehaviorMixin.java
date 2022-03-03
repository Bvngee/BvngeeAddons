package bvngeeaddons.mixins.emptyBottleFix;

import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(CauldronBehavior.class)
public class CauldronBehaviorMixin {

//maybe inject to registerBehavior() instead?
//method_32222
    @ModifyArg(method = "method_32222", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;setStackInHand(Lnet/minecraft/util/Hand;Lnet/minecraft/item/ItemStack;)V", ordinal = 1))
    private static ItemStack injected(ItemStack itemStack){

    }
}
