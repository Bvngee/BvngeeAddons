package bvngeeaddons.mixins.creativeInteractCauldronFix;

import bvngeeaddons.config.BvngeeAddonsFeatures;
import net.minecraft.block.AbstractCauldronBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractCauldronBlock.class)
public class AbstractCauldronBlockMixin {

    int slotNumber = -1;

    @Inject(method = "onUse", at = @At(value = "HEAD"))
    private void head(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<CauldronBehavior> cir) {
        if (world.isClient) slotNumber = player.getInventory().getEmptySlot();
    }

    @Inject(method = "onUse", at = @At(value = "RETURN"))
    private void tail(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<CauldronBehavior> cir) {
        if (world.isClient && player.getAbilities().creativeMode && BvngeeAddonsFeatures.creativeInteractCauldronFix.getBooleanValue()) {
            // ??? \/
            player.getInventory().getStack(slotNumber).setCount(0);
            System.out.println("hi " + slotNumber);
        }
    }

}