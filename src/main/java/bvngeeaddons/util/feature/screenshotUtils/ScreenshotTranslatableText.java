package bvngeeaddons.util.feature.screenshotUtils;

import bvngeeaddons.util.chat.EditableChatHudLine;
import net.minecraft.text.TranslatableText;

import java.io.File;

public class ScreenshotTranslatableText extends TranslatableText {

    private File file;
    private EditableChatHudLine<ScreenshotTranslatableText> parentChatHudLine;

    public ScreenshotTranslatableText(TranslatableText text, File file, boolean withScreenshotUtilButtons) {
        super(text.getKey(), text.getArgs());

        this.file = file;
        if (withScreenshotUtilButtons) ScreenshotUtils.addScreenshotUtilButtons(this);
    }

    public void setParentChatHudLine(EditableChatHudLine<ScreenshotTranslatableText> chatHudLine) {
        this.parentChatHudLine = chatHudLine;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setText(TranslatableText text, boolean withScreenshotUtilButtons) {
        EditableChatHudLine<ScreenshotTranslatableText> parentChatHudLine = this.parentChatHudLine;
        ScreenshotTranslatableText newText = new ScreenshotTranslatableText(text, this.getFile(), withScreenshotUtilButtons);
        newText.setParentChatHudLine(parentChatHudLine);
        parentChatHudLine.setText(newText);
    }

    public File getFile() {
        return this.file;
    }

}