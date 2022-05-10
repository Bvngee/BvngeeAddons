package bvngeeaddons.util.creativeInteractCauldronFix;

import bvngeeaddons.config.BvngeeAddonsFeatures;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ItemSwapReverser {

    public static int slotNumber = -1;

    public static void reverseItemSwap(World world, PlayerEntity player) {
        if (shouldSwap(world, player)) {
            System.out.println("hi " + slotNumber);
            player.getInventory().getStack(slotNumber).setCount(0);
        }
    }

    public static boolean shouldSwap(World world, PlayerEntity player) {
        return world.isClient && player.getAbilities().creativeMode && BvngeeAddonsFeatures.creativeInteractCauldronFix.getBooleanValue();
    }

}
