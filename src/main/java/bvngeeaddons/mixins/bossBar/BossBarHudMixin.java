package bvngeeaddons.mixins.bossBar;

import bvngeeaddons.config.BvngeeAddonsFeatures;
import bvngeeaddons.config.listEntries.BossBarRenderMode;
import bvngeeaddons.config.listEntries.ShownBossBarTypes;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.BossBarHud;
import net.minecraft.client.gui.hud.ClientBossBar;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.text.LiteralText;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.*;

@Mixin(BossBarHud.class)
public class BossBarHudMixin {

    @Shadow @Final private MinecraftClient client;
    @Shadow @Final private static int WIDTH;

    private List<ClientBossBar> allBossBars = new ArrayList<>();
    private final Map<BossBar.Color, List<ClientBossBar>> colorToBossBars = new LinkedHashMap<>();
    private List<ClientBossBar> renderedBossBars = new ArrayList<>();

    private BossBarRenderMode renderMode = (BossBarRenderMode) BvngeeAddonsFeatures.bossBarRenderMode.getOptionListValue();
    private ShownBossBarTypes shownTypes = (ShownBossBarTypes) BvngeeAddonsFeatures.shownBossBarTypes.getOptionListValue();
    private boolean separateNamed = BvngeeAddonsFeatures.separateBossBarsWithNames.getBooleanValue();

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Ljava/util/Map;values()Ljava/util/Collection;", shift = At.Shift.AFTER))
    private Iterator<ClientBossBar> bossBarListRedirect(Collection<ClientBossBar> values) {

        final List<ClientBossBar> newList = values.stream().toList();
        if (isConfigChanged() || !allBossBars.equals(newList)) {
            allBossBars = new ArrayList<>(newList.stream().map(this::bossBarCopy).toList());
            updateBossBarList();
        }

        return filterBossBarTypes(renderedBossBars).iterator();

    }

    private void updateBossBarList() {

        if (renderMode == BossBarRenderMode.COMPACT) {

            colorToBossBars.clear();
            for (ClientBossBar bossBar : allBossBars) {
                colorToBossBars.computeIfAbsent(bossBar.getColor(), k -> new ArrayList<>()).add(bossBar);
            }
            renderedBossBars = colorToBossBars.values().stream().map(n -> compactModeLabel(n.get(0))).toList();

        } else if (renderMode == BossBarRenderMode.NONE) {

            renderedBossBars = separateNamed ? allBossBars.stream().filter(this::isNamed).toList() : new ArrayList<>();

        } else { //DEFAULT

            renderedBossBars = allBossBars;

        }

    }


    private ClientBossBar compactModeLabel(ClientBossBar bossBar) {

        List<ClientBossBar> bossBars = colorToBossBars.get(bossBar.getColor());
        List<ClientBossBar> namedBossBars = new ArrayList<>(), unnamedBossBars = new ArrayList<>();
        bossBars.forEach(k -> {
            if (isNamed(k)) { namedBossBars.add(k); } else { unnamedBossBars.add(k); }
        });
        LiteralText label;
        String unnamedType = unnamedBossBars.size() > 0 ? unnamedBossBars.get(0).getName().getString() : bossBars.get(0).getName().getString();

        if (separateNamed) {
            StringBuilder namedString = new StringBuilder();
            final int extraWidth = 50;
            int width = 0;

            while (width <= WIDTH - extraWidth && namedBossBars.size() > 0) {
                namedString.append(namedBossBars.get(0).getName().getString());
                namedBossBars.remove(0);
                if (namedBossBars.size() > 0) namedString.append(", ");
                width = client.textRenderer.getWidth(namedString.toString());
            }
            if (namedBossBars.size() > 0) namedString.append("... ");

            final String unnamedString = unnamedBossBars.size() > 0 ? (
                    (namedString.length() > 0 ? ", " : "") +
                    (unnamedType) +
                    (unnamedBossBars.size() > 1 ? " - x" + unnamedBossBars.size() : "")
                    ) : "";
            label = new LiteralText(namedString.append(unnamedString).toString());
        } else {
            label = new LiteralText(
                    bossBars.size() > 1 ?
                            unnamedType + " - x" + bossBars.size()
                            : unnamedType
                    );
        }
        bossBar.setName(label);
        return bossBar;

    }

    private boolean isConfigChanged() {
        if (
                BvngeeAddonsFeatures.bossBarRenderMode.getOptionListValue() != renderMode
                || BvngeeAddonsFeatures.shownBossBarTypes.getOptionListValue() != shownTypes
                || BvngeeAddonsFeatures.separateBossBarsWithNames.getBooleanValue() != separateNamed
        ) {
            renderMode = (BossBarRenderMode) BvngeeAddonsFeatures.bossBarRenderMode.getOptionListValue();
            shownTypes = (ShownBossBarTypes) BvngeeAddonsFeatures.shownBossBarTypes.getOptionListValue();
            separateNamed = BvngeeAddonsFeatures.separateBossBarsWithNames.getBooleanValue();
            return true;
        } else {
            return false;
        }
    }

    private List<ClientBossBar> filterBossBarTypes(List<ClientBossBar> bossBars) {
        if (shownTypes == ShownBossBarTypes.WITHER) {
            return bossBars.stream().filter(k -> k.getColor() != BossBar.Color.PINK).toList();
        } else if (shownTypes == ShownBossBarTypes.DRAGON) {
            return bossBars.stream().filter(k -> k.getColor() != BossBar.Color.PURPLE).toList();
        } else { //Both
            return bossBars;
        }
    }

    private boolean isNamed(ClientBossBar bossBar) {
        return !(bossBar.getName().getString().equals("Wither") || bossBar.getName().getString().equals("Ender Dragon"));
    }

    private ClientBossBar bossBarCopy(ClientBossBar original) {
        return new ClientBossBar(original.getUuid(), original.getName(), original.getPercent(), original.getColor(), original.getStyle(), original.shouldDarkenSky(), original.hasDragonMusic(), original.shouldThickenFog());
    }

}