package bvngeeaddons.mixins.creativeInteractCauldronFix;

import net.minecraft.block.BlockState;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

@Mixin(CauldronBehavior.class)
public interface CauldronBehaviorMixin {

    //since the stuff it's changing only runs if(!world.isClient()) , this doesn't work on non-singleplayer.
//    @ModifyArgs(method = {"method_32222", "method_32220", "method_32219", "emptyCauldron", "fillCauldron"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemUsage;exchangeStack(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/ItemStack;"))
//    private static void potionEmptyCauldron(Args args){
//        ItemStack original = args.get(0);
//        PlayerEntity player = args.get(1);
//        if (BvngeeAddonsFeatures.creativeInteractCauldronFix.getBooleanValue() && player.getAbilities().creativeMode) args.set(2, original);
//        System.out.println(FabricLoader.getInstance().getEnvironmentType().toString());
//    }


    //none of this works to remove the generated item - I think it's because the function has to actually return,
    //send a packet to the server to give you a new item, then send a packet back confirming it, before the item actually spawns,
    //at which point you can finally remove it on the client. Not sure tho, needs testing... also ItemSwapReverser is done very shittily so stop lazi redo that pls haha

    @Inject(method = {"method_32222", "method_32220", "method_32219"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/util/ActionResult;success(Z)Lnet/minecraft/util/ActionResult;"))
    private static void inject1(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack, CallbackInfoReturnable<ActionResult> cir) {

    }

    @Inject(method = "fillCauldron", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/ActionResult;success(Z)Lnet/minecraft/util/ActionResult;"))
    private static void inject2(World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack, BlockState state, SoundEvent soundEvent, CallbackInfoReturnable<ActionResult> cir) {

    }

    @Inject(method = "emptyCauldron", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/ActionResult;success(Z)Lnet/minecraft/util/ActionResult;"))
    private static void inject3(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack, ItemStack output, Predicate<BlockState> predicate, SoundEvent soundEvent, CallbackInfoReturnable<ActionResult> cir) {

    }

}