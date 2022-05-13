package bvngeeaddons.util.feature.creativeInteractCauldronFix;

import bvngeeaddons.config.BvngeeAddonsFeatures;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.ScreenHandlerSlotUpdateS2CPacket;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.world.World;

public class ItemSwapReverseHelper {

    private static int slotNumber = -1;
    
    public static void onScreenHandlerSlotUpdate(ScreenHandlerSlotUpdateS2CPacket packet, ScreenHandler handler, World world, PlayerEntity player) {

        System.out.println("empty slot number: " + slotNumber);
        System.out.println("packet slot number: " + packet.getSlot());
        if (packet.getSlot() == slotNumber && shouldSwap(world, player)) {
            System.out.println("reverse attempt at " + slotNumber);
            //todo: NONE OF THIS IS GOING TO THE SERVER??? VERY BUGGY
            player.getInventory().getStack(slotNumber).setCount(0);
//            handler.getSlot(slotNumber).getStack().setCount(0);
        }
        slotNumber = -1;
    }

    public static void onCauldronUse(World world, PlayerEntity player) {
        if (shouldSwap(world, player)) {
            final int emptySlot = player.getInventory().getEmptySlot();
            slotNumber = (emptySlot < 9 ? 36 + emptySlot : emptySlot);
            //slotNumber = emptySlot;
        }
    }

    public static boolean shouldSwap(World world, PlayerEntity player) {
        return world.isClient && player.getAbilities().creativeMode && BvngeeAddonsFeatures.creativeInteractCauldronFix.getBooleanValue();
    }

}