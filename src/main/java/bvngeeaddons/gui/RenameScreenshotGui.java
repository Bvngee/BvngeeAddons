package bvngeeaddons.gui;

import bvngeeaddons.util.feature.screenshotUtils.ScreenshotTranslatableText;
import bvngeeaddons.util.feature.screenshotUtils.ScreenshotUtils;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

public class RenameScreenshotGui extends Screen {

    private final ScreenshotTranslatableText text;
    private String newName;
    private ButtonWidget doneButton;
    private TextFieldWidget textField;

    public RenameScreenshotGui(Text title, ScreenshotTranslatableText text) {
        super(title);
        this.text = text;
    }

    @Override
    public void init() {
        textField = new TextFieldWidget(
            this.textRenderer,
            this.width/2 - 150,
            this.height/2,
            270,
            20,
            new LiteralText("")
        );
        String name = text.getFile().getName();
        textField.setSuggestion(name.substring(0, name.length()-4)); //is the ".png" included in .getName()?
        textField.setChangedListener((string) -> this.newName = string);
        this.addDrawableChild(textField);

        doneButton = this.addDrawableChild(
            new ButtonWidget(
                this.width/2 - 155,
                this.height - 50,
                150,
                20,
                ScreenTexts.DONE,
                (button) -> {
                    ScreenshotUtils.renameFunc(this.text);
                    this.client.setScreen(null);
                }

            )
        );

        this.addDrawableChild(
            new ButtonWidget(
                this.width/2 + 5,
                this.height - 50,
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
        drawCenteredText(matrices, this.textRenderer, ".png", this.width/2 + 134, this.height/2 + 5, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }

}