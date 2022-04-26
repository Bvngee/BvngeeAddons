package bvngeeaddons.gui;

import bvngeeaddons.BvngeeAddons;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.TranslatableText;

public class BlockSoundsOptionsScreen extends GameOptionsScreen {

    public BlockSoundsOptionsScreen(Screen parent, GameOptions gameOptions){
        super(parent, gameOptions, new TranslatableText(BvngeeAddons.MOD_ID + ".gui.screen.block_sounds_options_screen"));
    }



}
