package bvngeeaddons.util.feature.screenshotUtils;

import bvngeeaddons.BvngeeAddons;
import bvngeeaddons.gui.RenameScreenshotGui;
import bvngeeaddons.util.chat.EditableChatHudLine;
import bvngeeaddons.util.chat.FunctionClickEvent;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import org.apache.logging.log4j.Logger;
import org.lwjgl.system.MemoryUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

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
        MinecraftClient.getInstance().setScreen(new RenameScreenshotGui(new TranslatableText("bvngeeaddons.gui.screen.screenshotUtils.renameScreen.title"), text));
    }

    public static void renameFunc(ScreenshotTranslatableText text) {
        MinecraftClient mc = MinecraftClient.getInstance();

        File original = text.getFile();
        File renamed = new File(SCREENSHOTS_DIR + "newName" + ".png");

        if (original.renameTo(renamed)) {
            EditableChatHudLine<ScreenshotTranslatableText> parentChatHudLine = text.getParentChatHudLine();
            Text newFileText = (new LiteralText(renamed.getName()))
                    .formatted(Formatting.UNDERLINE)
                    .styled(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, renamed.getAbsolutePath())));
            ScreenshotTranslatableText renamedText = new ScreenshotTranslatableText(new TranslatableText("screenshot.success", newFileText), renamed.getAbsolutePath(), true);

            renamedText.setParentChatHudLine(parentChatHudLine);
            parentChatHudLine.setText(renamedText);

            mc.player.sendMessage(new TranslatableText(BVNGEEADDONS_NAMESPACE_PREFIX + "screenshotUtils.renamed_string", original.getName(), renamed.getName()).formatted(Formatting.GREEN), true);
        } else {
            mc.player.sendMessage(new TranslatableText(BVNGEEADDONS_NAMESPACE_PREFIX + "screenshotUtils.failed_rename_string").formatted(Formatting.GREEN), true);
        }

        mc.inGameHud.getChatHud().reset();
    }

    private static void deleteFunc(ScreenshotTranslatableText text) {
        MinecraftClient mc = MinecraftClient.getInstance();

        if (text.getFile().delete()) {
            TranslatableText successful = new TranslatableText(BVNGEEADDONS_NAMESPACE_PREFIX + "screenshotUtils.deleted_string", text.getFile().getName());
            EditableChatHudLine<ScreenshotTranslatableText> parentChatHudLine = text.getParentChatHudLine();

            parentChatHudLine.setText(new ScreenshotTranslatableText(successful, text.getFile().getAbsolutePath(), false));

            mc.player.sendMessage(successful.formatted(Formatting.GREEN), true);
        } else {
            mc.player.sendMessage(new TranslatableText(BVNGEEADDONS_NAMESPACE_PREFIX + "screenshotUtils.failed_deleted_string").formatted(Formatting.GREEN), true);
        }

        mc.inGameHud.getChatHud().reset();
    }

    private static void copyFunc(ScreenshotTranslatableText text) {
        MinecraftClient mc = MinecraftClient.getInstance();

        File imageFile = text.getFile();
        try {
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            System.out.println(bufferedImage.getType());
            int size = bufferedImage.getWidth() * bufferedImage.getHeight() * 4;
            long pointer = MemoryUtil.nmemAlloc(size);
            ByteBuffer byteBuffer = MemoryUtil.memByteBufferSafe(pointer, size);

            if (byteBuffer == null) {
                LOGGER.error("Invalid Image");
            } else {
                byte[] imageData;
                if (byteBuffer.hasArray()) {
                    imageData = byteBuffer.array();
                } else {
                    // can't use .array() as the buffer is not array-backed
                    imageData = new byte[size];
                    byteBuffer.get(imageData);
                }

                CopyHelper.doCopy(imageData, bufferedImage.getWidth(), bufferedImage.getHeight(), 4);
            }

            ////ByteBuffer byteBuffer = MemoryUtil.memAlloc(bufferedImage.getWidth() * bufferedImage.getHeight() * 4);
            //ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //ImageIO.write(bufferedImage, "png", baos);
            //byte[] byteArray = baos.toByteArray();

//            byte[] byteArray = new byte[bufferedImage.getWidth() * bufferedImage.getHeight() * 4];
//            baos.write(byteArray);
            //System.out.println(byteArray.length);
            //CopyHelper.doCopy(byteArray, bufferedImage.getWidth(), bufferedImage.getHeight(), 4);

            mc.player.sendMessage(new TranslatableText(BVNGEEADDONS_NAMESPACE_PREFIX + "screenshotUtils.copied_string", imageFile.getName()), true);
        } catch(IOException e) {
            LOGGER.warn(e);
            mc.player.sendMessage(new TranslatableText(BVNGEEADDONS_NAMESPACE_PREFIX + "screenshotUtils.failed_copied_string"), true);
        }

    }

}