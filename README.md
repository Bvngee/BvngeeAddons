# BvngeeAddons

My personal set of client-side Minecraft modifications - random things that I've thought of over time that would be helpful for my (fairly technical) gameplay. Unfortunately hasn't been touched or updated since 1.17.1 because that's the version my server is still in.

Uses Masa's MaLiLib for config gui; usage is shamelessly stolen from Fallen_Breath.

### ---------------FIXES-----------------


- **creativeInteractCauldronFix:** Fixes the annoying behavior where in creative mode, right clicking on a full cauldron adds water bottles to your inventory. Idea from schmoe_mc. *NOTE: only works in singleplayer.*

- **creativeInteractCauldronMultiplayerFix:** Ridiculous client-side hack to fix the above behavior on servers as well, by watching for new water bottles after cauldron interactions. yeah, I know. Use at your own risk

### ---------------FEATURES---------------

- **screenshotUtils:** Adds interactive buttons to the "screenshot taken!" chat message when you take screenshots for rename, copy to clipboard, and delete. Actual fucking lifesaver, could not live without this
![1](https://github.com/user-attachments/assets/3f3b5d4f-9bfd-450b-8e5f-3f8bd6affdc0)
![2](https://github.com/user-attachments/assets/cd1717a7-00bb-49e1-8da6-c96cb698c162)

- **removeAutoJumpButton:** Port of my [AntiAutoJump](https://www.curseforge.com/minecraft/mc-mods/anti-auto-jump) mod, cuz why not

- **bossBarRenderMode:** Compact mode renders only 1 boss bar for each type + the total boss count; None disables boss bars entirely

- **shownBossBarTypes:** Whether to show the boss bars of only withers, only dragons, or both

- **separateBossBarsWithNames:** Separates all nametagged bosses when using the Compact or None bossBarRenderMode

- **bossBarScale:** Scales all boss bar rendering by a decimal value
![4](https://github.com/user-attachments/assets/35963ce1-0a1d-41c9-859e-8ab411d776f1)
![3](https://github.com/user-attachments/assets/ca06dff7-69d6-43c4-b923-f621842f757a)
