package bvngeeaddons.mixins.creativeInteractCauldronFix;

import bvngeeaddons.config.BvngeeAddonsFeatures;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(CauldronBehavior.class)
public interface CauldronBehaviorMixin {

    //client side only fix
    @ModifyArgs(remap = false, method = {"method_32222", "method_32220", "method_32219"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemUsage;exchangeStack(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/ItemStack;"))
    private static void potionEmptyCauldron1(Args args){
        PlayerEntity player = args.get(1);
        if (
                MinecraftClient.getInstance().isInSingleplayer()
                && player.getAbilities().creativeMode
                && BvngeeAddonsFeatures.creativeInteractCauldronFix.getBooleanValue()
        ) {
            args.set(2, args.get(0));
        }
    }

    @ModifyArgs(method = {"emptyCauldron", "fillCauldron"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemUsage;exchangeStack(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/ItemStack;"))
    private static void potionEmptyCauldron0(Args args){
        PlayerEntity player = args.get(1);
        if (
                MinecraftClient.getInstance().isInSingleplayer()
                && player.getAbilities().creativeMode
                && BvngeeAddonsFeatures.creativeInteractCauldronFix.getBooleanValue()
        ) {
            args.set(2, args.get(0));
        }
    }

}