package bvngeeaddons.util.feature.screenshotUtils;

import bvngeeaddons.util.chat.EditableChatHudLine;
import net.minecraft.text.TranslatableText;

import java.io.File;

public class ScreenshotTranslatableText extends TranslatableText {

    private final File file;
    private EditableChatHudLine<ScreenshotTranslatableText> parentChatHudLine;

    public ScreenshotTranslatableText(TranslatableText text, String filePath, boolean withScreenshotUtilButtons) {
        super(text.getKey(), text.getArgs());

        this.file = new File(filePath);
        if (withScreenshotUtilButtons) ScreenshotUtils.addScreenshotUtilButtons(this);
    }

    public void setParentChatHudLine(EditableChatHudLine<ScreenshotTranslatableText> chatHudLine) {
        this.parentChatHudLine = chatHudLine;
    }

    public File getFile() {
        return this.file;
    }

    public EditableChatHudLine<ScreenshotTranslatableText> getParentChatHudLine() {
        return this.parentChatHudLine;
    }

}