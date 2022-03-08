package bvngeeaddons.mixins.creativeInteractCauldronFix;

import bvngeeaddons.config.BvngeeAddonsFeatures;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(CauldronBehavior.class)
public interface CauldronBehaviorMixin {

    @ModifyArgs(method = {"method_32222", "method_32220", "method_32219", "emptyCauldron", "fillCauldron"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemUsage;exchangeStack(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/ItemStack;"))
    private static void potionEmptyCauldron(Args args){
        ItemStack original = args.get(0);
        PlayerEntity player = args.get(1);
        if (BvngeeAddonsFeatures.creativeInteractCauldronFix.getBooleanValue() && player.getAbilities().creativeMode) args.set(2, original);
    }

}