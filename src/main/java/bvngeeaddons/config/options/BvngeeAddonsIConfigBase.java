package bvngeeaddons.config.options;

import bvngeeaddons.BvngeeAddons;
import fi.dy.masa.malilib.config.IConfigBase;

public interface BvngeeAddonsIConfigBase extends IConfigBase {

    String BVNGEEADDONS_NAMESPACE_PREFIX = BvngeeAddons.MOD_ID + ".config.";
    String COMMENT_SUFFIX = ".comment";

}
