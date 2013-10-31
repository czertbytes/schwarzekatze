package de.czertbytes.schwarzekatze.core.converter;

import de.czertbytes.schwarzekatze.core.util.EnumUtils;
import org.springframework.core.convert.converter.Converter;

public final class EnumToStringConverter implements Converter<Enum<?>, String> {

    public String convert(Enum<?> source) {
        return EnumUtils.toString(source);
    }
}
