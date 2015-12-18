package com.dummyc0m.forgemod.amethyst.client.util;

import net.minecraft.util.StatCollector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dummyc0m on 11/29/15.
 */
public class LanguageUtil {
    private static Map<String, String> localizedBuffer = new HashMap<>();

    public static String getLocalization(String key) {
        return getLocalization(key, true);
    }

    private static String getLocalization(String key, boolean fallback) {
        String localization = localizedBuffer.get(key);
        if (localization != null) {
            return localization;
        }
        localization = StatCollector.translateToLocal(key);
        if (localization.equals(key) && fallback) {
            localization = StatCollector.translateToFallback(key);
        }
        localizedBuffer.put(key, localization);
        return localization;
    }

    @SuppressWarnings("unchecked")
    public static void formatTooltip(String langName, List list) {
        String langTooltip = getLocalization(langName);
        if (langTooltip == null || langTooltip.equals(langName))
            return;

        formatLocalizedTooltip(langTooltip, list);
    }

    @SuppressWarnings("unchecked")
    public static void formatLocalizedTooltip(String langTooltip, List list) {
        for (String descriptionLine : langTooltip.split(";")) {
            if (descriptionLine != null && descriptionLine.length() > 0)
                list.add(descriptionLine);
        }
    }
}
