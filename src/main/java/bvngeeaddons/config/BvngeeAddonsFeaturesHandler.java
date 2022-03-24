package bvngeeaddons.config;

import bvngeeaddons.config.options.BvngeeAddonsIConfigBase;
import fi.dy.masa.malilib.config.IConfigBase;

import java.lang.reflect.Field;
import java.util.*;

public class BvngeeAddonsFeaturesHandler {

    private static final List<BvngeeAddonsOption> OPTIONS = new ArrayList<>();
    private static final List<BvngeeAddonsIConfigBase> CONFIGS = new ArrayList<>();
    private static final Map<Config.Type, List<BvngeeAddonsOption>> TYPE_TO_OPTION = new LinkedHashMap<>();
    private static final Map<Config.Category, List<BvngeeAddonsOption>> CATEGORY_TO_OPTION = new LinkedHashMap<>();

    public static void initOptions(){
        for (Field field : BvngeeAddonsFeatures.class.getDeclaredFields()){
            Config annotation = field.getAnnotation(Config.class);
            if (annotation != null){
                try{
                    Object config = field.get(null);
                    if (config instanceof BvngeeAddonsIConfigBase){
                        BvngeeAddonsOption option = new BvngeeAddonsOption(annotation, (BvngeeAddonsIConfigBase) config);
                        //annotation is the Config.Type, config is the actual config
                        OPTIONS.add(option);
                        CONFIGS.add(option.getConfig());
                        TYPE_TO_OPTION.computeIfAbsent(annotation.type(), k -> new ArrayList<>()).add(option);
                        CATEGORY_TO_OPTION.computeIfAbsent(annotation.category(), k -> new ArrayList<>()).add(option);

                    }
                }catch (IllegalAccessException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static List<BvngeeAddonsOption> getOptionsOfType(Config.Type type){
        final List<BvngeeAddonsOption> options = TYPE_TO_OPTION.get(type);
        return options == null ? new ArrayList<>() : options;
    }

    @SuppressWarnings("unchecked")
    public static <T extends IConfigBase> List<T> getFeaturesOfType(Config.Type type){
        final List<T> features = (List<T>) getOptionsOfType(type).stream().map(BvngeeAddonsOption::getConfig).toList();
        return features == null ? new ArrayList<>() : features;
    }

    public static List<BvngeeAddonsOption> getOptionsOfCategory(Config.Category category){
        final List<BvngeeAddonsOption> options = CATEGORY_TO_OPTION.get(category);
        return options == null ? new ArrayList<>() : options;
    }

    @SuppressWarnings("unchecked")
    public static <T extends IConfigBase> List<T> getFeaturesOfCategory(Config.Category category){
        final List<T> features = (List<T>) getOptionsOfCategory(category).stream().map(BvngeeAddonsOption::getConfig).toList();
        return features == null ? new ArrayList<>() : features;
    }

    public static List<BvngeeAddonsIConfigBase> getConfigs(){
        return CONFIGS;
    }

}