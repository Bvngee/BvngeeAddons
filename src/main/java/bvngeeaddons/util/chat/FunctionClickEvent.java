package bvngeeaddons.util.chat;

import net.minecraft.text.ClickEvent;

import java.util.function.Consumer;

public class FunctionClickEvent<T> extends ClickEvent {

    final Consumer<T> function;
    final T type;

    public FunctionClickEvent(Consumer<T> func, T t) {
        super(Action.CHANGE_PAGE, "-1");
        this.function = func;
        this.type = t;
    }

    public void runFunc() {
        function.accept(type);
    }

}