package de.czertbytes.schwarzekatze.core.converter;

import de.czertbytes.schwarzekatze.core.util.EnumUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

public final class StringToEnumConverterFactory implements ConverterFactory<String, Enum> {

    @Override
    public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToEnum(targetType);
    }

    private class StringToEnum<T> implements Converter<String, Enum> {

        private final Class<T> enumType;

        public StringToEnum(Class<T> enumType) {
            this.enumType = enumType;
        }

        public Enum convert(String source) {
            return EnumUtils.fromString(source, enumType);
        }
    }
}
