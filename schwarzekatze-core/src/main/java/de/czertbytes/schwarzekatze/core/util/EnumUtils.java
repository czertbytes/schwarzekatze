package de.czertbytes.schwarzekatze.core.util;

import org.springframework.util.StringUtils;

import java.lang.reflect.Type;

public class EnumUtils {

    public static <T extends Enum<T>> T fromString(final String value, final Type type) {
        if (StringUtils.hasText(value)) {
            String normalizedValue = value.replaceAll("\\-", "_").trim();

            for (T tmp : (T[]) ((Class) type).getEnumConstants()) {
                if (tmp.name().equalsIgnoreCase(normalizedValue)) {
                    return tmp;
                }
            }
        }

        return null;
    }

    public static String toString(final Enum enumClazz) {
        if (enumClazz != null) {
            return enumClazz.name().replaceAll("\\_", "-").toLowerCase();
        }

        return null;
    }
}
