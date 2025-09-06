package com.example.translation.util;

import java.util.Locale;

public class LocaleUtils {

    public static Locale parseLocale(String localeStr) {
        if (localeStr == null || localeStr.isBlank()) {
            return Locale.ENGLISH;
        }

        String[] parts = localeStr.split("[-_]");
        if (parts.length == 1) {
            return new Locale(parts[0]);
        } else if (parts.length == 2) {
            return new Locale(parts[0], parts[1]);
        } else {
            return Locale.ENGLISH;
        }
    }
}
