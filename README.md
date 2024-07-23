# BvngeeAddons

Uses Masa's MaLiLib for config gui; usage is shamelessly stolen from Fallen_Breath.

### ---------------FIXES-----------------


- **creativeInteractCauldronFix:** Fixes the annoying behavior where in creative mode, right clicking on a full cauldron adds water bottles to your inventory. Idea from schmoe_mc. *NOTE: only works in singleplayer* (serverside fix).

- **creativeInteractCauldronMultiplayerFix:** Ridiculous client-side hack to fix the above behavior on servers as well, by watching for new water bottles after cauldron interactions. yeah, I know. Use at your own risk

### ---------------FEATURES---------------

- **screenshotUtils:** Adds interactive buttons to the "screenshot taken!" chat message when you take screenshots for rename, copy to clipboard, and delete. Actual fucking lifesaver, could not live without this
 
- **removeAutoJumpButton:** Port of my [AntiAutoJump](https://www.curseforge.com/minecraft/mc-mods/anti-auto-jump) mod, cuz why not

- **bossBarRenderMode:** Compact mode renders only 1 boss bar for each type + the total boss count; None disables boss bars entirely

- **shownBossBarTypes:** Whether to show the boss bars of only withers, only dragons, or both

- **separateBossBarsWithNames:** Separates all nametagged bosses when using the Compact or None bossBarRenderMode

- **bossBarScale:** Scales all boss bar rendering by a decimal value
