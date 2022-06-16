package bvngeeaddons.util.feature.screenshotUtils;

import bvngeeaddons.BvngeeAddons;
import bvngeeaddons.gui.RenameScreenshotGui;
import bvngeeaddons.util.chat.FunctionClickEvent;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import static bvngeeaddons.config.options.BvngeeAddonsIConfigBase.BVNGEEADDONS_NAMESPACE_PREFIX;

public class ScreenshotUtils {

    private static final Logger LOGGER = BvngeeAddons.getLogger();
    public static final String SCREENSHOTS_DIR = FabricLoader.getInstance().getGameDir() + "\\screenshots\\";
    private static final String CHAT_BUTTON_PREFIX = "screenshotUtils.chat_button.";
    private static final LiteralText SPACE = new LiteralText(" ");
    private static final TranslatableText DELETE = new TranslatableText(BVNGEEADDONS_NAMESPACE_PREFIX + CHAT_BUTTON_PREFIX + "delete");
    private static final TranslatableText RENAME = new TranslatableText(BVNGEEADDONS_NAMESPACE_PREFIX + CHAT_BUTTON_PREFIX + "rename");
    private static final TranslatableText COPY = new TranslatableText(BVNGEEADDONS_NAMESPACE_PREFIX + CHAT_BUTTON_PREFIX + "copy");

    public static void addScreenshotUtilButtons(ScreenshotTranslatableText text) {
        text.append(SPACE)
                .append(DELETE.styled(style -> style.withClickEvent(new FunctionClickEvent<>(ScreenshotUtils::deleteFunc, text)).withColor(Formatting.RED).withUnderline(true))).append(SPACE)
                .append(RENAME.styled(style -> style.withClickEvent(new FunctionClickEvent<>(ScreenshotUtils::openRenameGui, text)).withColor(Formatting.GREEN).withUnderline(true))).append(SPACE)
                .append(COPY.styled(style -> style.withClickEvent(new FunctionClickEvent<>(ScreenshotUtils::copyFunc, text)).withColor(Formatting.AQUA).withUnderline(true))).append(SPACE);
    }

    private static void openRenameGui(ScreenshotTranslatableText text) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (text.getFile().exists()) {
            mc.setScreen(new RenameScreenshotGui(text));
        } else {
            sendMessage(mc, new TranslatableText(BVNGEEADDONS_NAMESPACE_PREFIX + "screenshotUtils.invalid_file", text.getFile().getName()).formatted(Formatting.RED));
        }
    }

    public static void renameFunc(ScreenshotTranslatableText text, File renamed) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (text.getFile().exists()) {

            File original = text.getFile();

            boolean successful = original.renameTo(renamed);
            if (successful) {
                Text newFileText = (new LiteralText(renamed.getName()))
                        .formatted(Formatting.UNDERLINE)
                        .styled(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, renamed.getAbsolutePath())));

                text.setFile(renamed);
                text.setText(new TranslatableText("screenshot.success", newFileText), true);

                sendMessage(mc, new TranslatableText(BVNGEEADDONS_NAMESPACE_PREFIX + "screenshotUtils.renamed_string", original.getName(), renamed.getName()).formatted(Formatting.GREEN));
            } else {
                sendMessage(mc, new TranslatableText(BVNGEEADDONS_NAMESPACE_PREFIX + "screenshotUtils.failed_rename_string").formatted(Formatting.RED));
            }

            mc.inGameHud.getChatHud().reset();

        } else {
            sendMessage(mc, new TranslatableText(BVNGEEADDONS_NAMESPACE_PREFIX + "screenshotUtils.invalid_file", text.getFile().getName()).formatted(Formatting.RED));
        }
    }

    private static void deleteFunc(ScreenshotTranslatableText text) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (text.getFile().exists()) {

            boolean successful = text.getFile().delete();
            if (successful) {
                TranslatableText successfulText = new TranslatableText(BVNGEEADDONS_NAMESPACE_PREFIX + "screenshotUtils.deleted_string", text.getFile().getName());
                text.setText(successfulText, false);

                sendMessage(mc, successfulText.formatted(Formatting.GREEN));
            } else {
                sendMessage(mc, new TranslatableText(BVNGEEADDONS_NAMESPACE_PREFIX + "screenshotUtils.failed_deleted_string", text.getFile().getName()).formatted(Formatting.RED));
            }

            mc.inGameHud.getChatHud().reset();

        } else {
            sendMessage(mc, new TranslatableText(BVNGEEADDONS_NAMESPACE_PREFIX + "screenshotUtils.invalid_file", text.getFile().getName()).formatted(Formatting.RED));
        }

    }

    private static void copyFunc(ScreenshotTranslatableText text) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (text.getFile().exists()) {

            new Thread(() -> {

                try {
                    BufferedImage bufferedImage = ImageIO.read(text.getFile());

                    //remove alpha channel, due to JDK-8204187
                    BufferedImage withoutAlpha = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
                    Graphics2D g2d = withoutAlpha.createGraphics();
                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(0, 0, withoutAlpha.getWidth(), withoutAlpha.getHeight());
                    g2d.drawImage(bufferedImage, 0, 0, null);
                    g2d.dispose();

                    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new ImageTransferable(withoutAlpha), null);

                    sendMessage(mc, new TranslatableText(BVNGEEADDONS_NAMESPACE_PREFIX + "screenshotUtils.copied_string", text.getFile().getName()).formatted(Formatting.GREEN));
                } catch(IOException e) {
                    LOGGER.warn(e);
                    sendMessage(mc, new TranslatableText(BVNGEEADDONS_NAMESPACE_PREFIX + "screenshotUtils.failed_copied_string").formatted(Formatting.RED));
                }

            }, "BvngeeAddons - Copy Screenshot to Clipboard").start();

        } else {
            sendMessage(mc, new TranslatableText(BVNGEEADDONS_NAMESPACE_PREFIX + "screenshotUtils.invalid_file", text.getFile().getName()).formatted(Formatting.RED));
        }

    }

    private static void sendMessage(MinecraftClient mc, Text text) {
        if (mc.player != null) {
            mc.player.sendMessage(text, true);
        }
    }

}