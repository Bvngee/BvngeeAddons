package bvngeeaddons.config;

import fi.dy.masa.malilib.interfaces.IStringValue;
import fi.dy.masa.malilib.util.StringUtils;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Config {

    Type type();

    Category category() /* default Category.FEATURES*/;

    enum Type implements IStringValue{
        GENERIC, HOTKEY, LIST, DISABLE, TWEAK;
        @Override
        public String getStringValue() {
            return StringUtils.translate("bvngeeaddons.gui.config_type." + this.name().toLowerCase());
        }

    }

    enum Category{
        FIXES, FEATURES, SETTINGS;
        public String getDisplayName(){
            return StringUtils.translate("bvngeeaddons.gui.config_category." + this.name().toLowerCase());
        }
    }

}
