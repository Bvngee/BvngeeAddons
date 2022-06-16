package bvngeeaddons.gui;

import bvngeeaddons.util.feature.screenshotUtils.ScreenshotTranslatableText;
import bvngeeaddons.util.feature.screenshotUtils.ScreenshotUtils;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

import java.io.File;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.function.Consumer;

public class RenameScreenshotGui extends Screen {

    private ButtonWidget doneButton;
    private TextFieldWidget textField;

    private final ScreenshotTranslatableText originalText;
    private File newFile;

    public RenameScreenshotGui(ScreenshotTranslatableText text) {
        super(new TranslatableText("bvngeeaddons.gui.screen.screenshotUtils.renameScreen.title"));
        this.originalText = text;
        this.newFile = null;
    }

    @Override
    public void init() {
        textField = new TextFieldWidget(
            this.textRenderer,
            this.width/2 - 150,
            this.height/2,
            272  ,
            20,
            new LiteralText("")
        );
        String name = originalText.getFile().getName();
        textField.setSuggestion(name.substring(0, name.length()-4)); //remove ".png"; is there a better way??
        textField.setChangedListener(this::textFieldUpdate);
        this.addDrawableChild(textField);

        doneButton = new ButtonWidget(
            this.width/2 - 155,
            this.height*2/3,
            150,
            20,
            ScreenTexts.DONE,
            (button) -> {
                this.client.setScreen(null);
                ScreenshotUtils.renameFunc(originalText, newFile);
            }
        );
        doneButton.active = false;
        this.addDrawableChild(doneButton);

        this.addDrawableChild(
            new ButtonWidget(
                this.width/2 + 5,
                this.height*2/3,
                150,
                20,
                ScreenTexts.CANCEL,
                button -> this.client.setScreen(null)
            )
        );
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        drawCenteredText(matrices, this.textRenderer, this.title, this.width/2, 8, 16777215);
        drawCenteredText(matrices, this.textRenderer, ".png", this.width/2 + 135, this.height/2 + 5, 16777215);
        super.render(matrices, mouseX, mouseY, delta);

        if (!doneButton.active && newFile != null) {
            drawCenteredText(matrices, this.textRenderer, new TranslatableText("bvngeeaddons.config.screenshotUtils.invalid_rename", newFile.getName()), this.width/2, this.height/2 - 30, 16733525);
        }

    }

    private void textFieldUpdate(String string) {
        System.out.println("textFieldUpdate: " + string);
        if (string != null && !string.equals("")) {

            try {
                System.out.println("Original file's parent: " + originalText.getFile().getParent());
                this.newFile = Paths.get(originalText.getFile().getParent(), string + ".png").toFile();
                doneButton.active = true;
                textField.setSuggestion("");
            } catch (InvalidPathException e) {
                this.newFile = new File(originalText.getFile().getParent(), string + ".png");
                doneButton.active = false;
            }

        } else {
            String originalFileName = originalText.getFile().getName();
            textField.setSuggestion(originalFileName.substring(0, originalFileName.length()-4));
        }

    }

}