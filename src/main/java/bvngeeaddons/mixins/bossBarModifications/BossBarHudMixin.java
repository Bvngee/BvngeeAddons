package bvngeeaddons.mixins.bossBarModifications;

import bvngeeaddons.BvngeeAddons;
import bvngeeaddons.config.BvngeeAddonsFeatures;
import bvngeeaddons.config.listEntries.BossBarRenderMode;
import bvngeeaddons.config.listEntries.ShownBossBarTypes;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.BossBarHud;
import net.minecraft.client.gui.hud.ClientBossBar;
import net.minecraft.client.util.Window;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.*;

@Mixin(BossBarHud.class)
public abstract class BossBarHudMixin {

    @Shadow @Final private MinecraftClient client;
    @Shadow @Final private static int WIDTH;

    private List<ClientBossBar> allBossBars = new ArrayList<>();
    private Map<BossBar.Color, List<ClientBossBar>> colorToBossBars = new LinkedHashMap<>();
    private List<ClientBossBar> renderedBossBars = new ArrayList<>();

    private BossBarRenderMode renderMode = (BossBarRenderMode) BvngeeAddonsFeatures.bossBarRenderMode.getOptionListValue();
    private ShownBossBarTypes shownTypes = (ShownBossBarTypes) BvngeeAddonsFeatures.shownBossBarTypes.getOptionListValue();
    private double renderScale = BvngeeAddonsFeatures.bossBarScale.getDoubleValue();
    private boolean separateNamed = BvngeeAddonsFeatures.separateBossBarsWithNames.getBooleanValue();

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Ljava/util/Map;values()Ljava/util/Collection;", shift = At.Shift.AFTER))
    private Iterator<ClientBossBar> compactModeList(Collection<ClientBossBar> values) {

        List<ClientBossBar> newList = values.stream().toList();
        //check if any of the config settings changed OR the list of bossbars changed.
        //If so, run updateBossBarList
        if (!newList.equals(allBossBars) || isConfigChanged()) {
            allBossBars = newList;
            updateBossBarList();
        }

        return filterBossBarTypes(renderedBossBars).iterator();

        /*//moved into Update stuff
        if (BvngeeAddonsFeatures.bossBarRenderMode.getOptionListValue() == BossBarRenderMode.COMPACT) {
            colorToBossBars = new LinkedHashMap<>();
            for (ClientBossBar bossBar : values) {
                colorToBossBars.computeIfAbsent(bossBar.getColor(), k -> new ArrayList<>()).add(bossBar);
            }
            return colorToBossBars.values().stream().map(n -> n.get(0)).iterator();

        } else if (BvngeeAddonsFeatures.bossBarRenderMode.getOptionListValue() == BossBarRenderMode.NONE) {

            return BvngeeAddonsFeatures.separateBossBarsWithNames.getBooleanValue() ?
                    values.stream().filter(this::isNamed).iterator()
                    : Collections.emptyIterator();

        } else { //Default

            return values.iterator();

        }*/
    }

    //this becomes unnecessary, because I will set the name to render based on the
    //corresponding element of the Label list straight into the bossbars
    /*@Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/ClientBossBar;getName()Lnet/minecraft/text/Text;"))
    private Text compactModeLabelUnneeded(ClientBossBar bossBar) {

        //moved into Update stuff
        final boolean separate = BvngeeAddonsFeatures.separateBossBarsWithNames.getBooleanValue();
        final BossBarRenderMode mode = (BossBarRenderMode) BvngeeAddonsFeatures.bossBarRenderMode.getOptionListValue();

        if (mode == BossBarRenderMode.COMPACT) {

            List<ClientBossBar> bossBars = filterBossBarTypes(colorToBossBars.get(bossBar.getColor()));
            List<ClientBossBar> unnamedBossBars = new ArrayList<>(), namedBossBars = new ArrayList<>();
            for (ClientBossBar clientBossBar : bossBars) {
                if (isNamed(clientBossBar)) {
                    namedBossBars.add(clientBossBar);
                } else {
                    unnamedBossBars.add(clientBossBar);
                }
            }
            System.out.println("unnamed: " + unnamedBossBars.size() + "    named: " + namedBossBars.size());
            String unnamedType = unnamedBossBars.get(0).getName().getString();
            if (separate && namedBossBars.size() > 0) {
                final String separator = "... , ";
                final int extraLength = client.textRenderer.getWidth(separator.length() + unnamedType);
                StringBuilder stringBuilder = new StringBuilder();
                int width = 0;
                while (width + extraLength <= WIDTH && namedBossBars.size() > 0) {
                    stringBuilder.append(namedBossBars.get(0).getName().getString()).append(", ");
                    namedBossBars.remove(0);
                    width = client.textRenderer.getWidth(stringBuilder.toString());
                }
                final boolean cutOff = namedBossBars.size() > 0;
                final String unnamedLabel = unnamedBossBars.size() > 0 ?
                        (cutOff ? separator : "") +
                        unnamedType +
                        (unnamedBossBars.size() > 1 ? " - x" + unnamedBossBars.size() : "")
                        : "";
                return new LiteralText(stringBuilder.append(unnamedLabel).toString());
            } else {
                return new LiteralText(unnamedType + (unnamedBossBars.size() > 1 ? " - x" + bossBars.size() : ""));
            }

        } else if (mode == BossBarRenderMode.NONE) {

            return separate ? bossBar.getName() : new LiteralText("");

        } else { //Default

            return bossBar.getName();

        }
    }*/

    //todo: move to another class for rendering related utils?
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;getScaledWidth()I", shift = At.Shift.BEFORE))
    private void bossBarScalePush(MatrixStack matrices, CallbackInfo ci) {
        final double factor = BvngeeAddonsFeatures.bossBarScale.getDoubleValue();
        matrices.push();
        matrices.translate(-client.getWindow().getScaledWidth() / 2d * factor, 0, 0);
        matrices.scale((float) factor, (float) factor, 1);
        matrices.translate(client.getWindow().getScaledWidth() / 2d / factor, 0, 0);
    }

    @Inject(method = "render", at = @At(value = "TAIL"))
    private void bossBarScalePop(MatrixStack matrices, CallbackInfo ci) {
        matrices.pop();
    }

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;getScaledHeight()I"))
    private int fixBossBarLimit(Window window) {
        return (int) (window.getScaledHeight() / BvngeeAddonsFeatures.bossBarScale.getDoubleValue());
    }

    private void updateBossBarList() {
        System.out.println("update");
        if (renderMode == BossBarRenderMode.COMPACT) {

            colorToBossBars = new LinkedHashMap<>();
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

        StringBuilder namedString = new StringBuilder();
        String unnamedType = unnamedBossBars.get(0).getName().getString();
        String separator = "... , ";
        int extraWidth = 50;
        int width = 0;
        while (width <= WIDTH - extraWidth) {
            namedString.append(namedBossBars.get(0).getName().getString()).append(", ");
            namedBossBars.remove(0);
            width = client.textRenderer.getWidth(namedString.toString());
        }

        final boolean cutOff = namedBossBars.size() > 0;
        final String unnamedString = unnamedBossBars.size() > 0 ?
                (cutOff ? separator : "") +
                unnamedType +
                (unnamedBossBars.size() > 1 ? " - x" + unnamedBossBars.size() : "")
                : "";

        ClientBossBar output = new ClientBossBar(bossBar.getUuid(), bossBar.getName(), bossBar.getPercent(), bossBar.getColor(), bossBar.getStyle(), bossBar.shouldDarkenSky(), bossBar.hasDragonMusic(), bossBar.shouldThickenFog());
        output.setName(new LiteralText(namedString.append(unnamedString).toString()));
        return output;

    }

    private boolean isConfigChanged() {
        if (
                BvngeeAddonsFeatures.bossBarRenderMode.getOptionListValue() != renderMode
                || BvngeeAddonsFeatures.shownBossBarTypes.getOptionListValue() != shownTypes
                || BvngeeAddonsFeatures.bossBarScale.getDoubleValue() != renderScale
                || BvngeeAddonsFeatures.separateBossBarsWithNames.getBooleanValue() != separateNamed
        ) {
            renderMode = (BossBarRenderMode) BvngeeAddonsFeatures.bossBarRenderMode.getOptionListValue();
            shownTypes = (ShownBossBarTypes) BvngeeAddonsFeatures.shownBossBarTypes.getOptionListValue();
            renderScale = BvngeeAddonsFeatures.bossBarScale.getDoubleValue();
            separateNamed = BvngeeAddonsFeatures.separateBossBarsWithNames.getBooleanValue();
            return true;
        } else {
            return false;
        }
    }

    private List<ClientBossBar> filterBossBarTypes(List<ClientBossBar> bossBars) {
        if (shownTypes == ShownBossBarTypes.WITHER) {
            return bossBars.stream().filter(k -> k.getColor() != BossBar.Color.PURPLE).toList();
        } else if (shownTypes == ShownBossBarTypes.DRAGON) {
            return bossBars.stream().filter(k -> k.getColor() != BossBar.Color.PINK).toList();
        } else { //Both
            return bossBars;
        }
    }

    private boolean isNamed(ClientBossBar bossBar) {
        return !(bossBar.getName().getString().equals("Wither") || bossBar.getName().getString().equals("Ender Dragon"));
    }

}