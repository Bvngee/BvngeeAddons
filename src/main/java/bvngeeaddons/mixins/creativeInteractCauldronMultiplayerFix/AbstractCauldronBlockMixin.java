package bvngeeaddons.mixins.creativeInteractCauldronMultiplayerFix;

import bvngeeaddons.util.feature.creativeInteractCauldronMultiplayerFix.ItemSwapReverseHelper;
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

    @Inject(method = "onUse", at = @At(value = "HEAD"))
    private void updateSlotNumber(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<CauldronBehavior> cir) {
        if (world.isClient) ItemSwapReverseHelper.onCauldronUse(player);
    }

}