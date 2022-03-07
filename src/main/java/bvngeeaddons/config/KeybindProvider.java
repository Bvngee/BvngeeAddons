package bvngeeaddons.config;

import bvngeeaddons.BvngeeAddons;
import fi.dy.masa.malilib.hotkeys.IHotkey;
import fi.dy.masa.malilib.hotkeys.IKeybindManager;
import fi.dy.masa.malilib.hotkeys.IKeybindProvider;

import java.util.List;

public class KeybindProvider implements IKeybindProvider {

    //do stuff with the hotkeys from rules that have hotkeys
    List<IHotkey> HOTKEYS = BvngeeAddonsFeatures.getOptions().stream().filter(k -> k instanceof IHotkey).map(k -> (IHotkey)k.getConfig()).toList();

    @Override
    public void addKeysToMap(IKeybindManager manager) {
        HOTKEYS.forEach(iHotkey -> manager.addKeybindToMap(iHotkey.getKeybind()));
    }

    @Override
    public void addHotkeys(IKeybindManager manager) {
        manager.addHotkeysForCategory(BvngeeAddons.MOD_NAME, "BvngeeAddons", HOTKEYS);
    }
}
