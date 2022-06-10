package bvngeeaddons.util.chat;

import net.minecraft.client.gui.hud.ChatHudLine;

public class EditableChatHudLine<T> extends ChatHudLine<T> {

    private T text;

    public EditableChatHudLine(int creationTick, T text, int id) {
        super(creationTick, text, id);
        this.text = text;
    }

    public void setText(T text) {
        this.text = text;
    }

    public T getText() {
        return this.text;
    }

}
