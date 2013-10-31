package de.czertbytes.schwarzekatze.core.config;

import de.czertbytes.schwarzekatze.core.converter.EnumToStringConverter;
import de.czertbytes.schwarzekatze.core.converter.StringToEnumConverterFactory;
import de.czertbytes.schwarzekatze.core.domain.State;
import de.czertbytes.schwarzekatze.core.domain.action.Type;
import de.czertbytes.schwarzekatze.core.domain.pet.Animal;
import de.czertbytes.schwarzekatze.core.domain.pet.Breed;
import de.czertbytes.schwarzekatze.core.domain.pet.Gender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.support.DefaultConversionService;

@Configuration
public class CoreConfig {

    @Bean
    public Converter enumToStringConverter() {
        return new EnumToStringConverter();
    }

    @Bean
    public ConverterFactory stringToEnumConverterFactory() {
        return new StringToEnumConverterFactory();
    }

    @Bean
    public ConversionService conversionService() throws Exception {
        final DefaultConversionService conversionService = new DefaultConversionService();

        conversionService.removeConvertible(Enum.class, String.class);
        conversionService.removeConvertible(String.class, Enum.class);

        conversionService.addConverter(enumToStringConverter());
        conversionService.addConverterFactory(stringToEnumConverterFactory());
        conversionService.addConverter(String.class, Type.class, stringToEnumConverterFactory().getConverter(Type.class));
        conversionService.addConverter(String.class, Animal.class, stringToEnumConverterFactory().getConverter(Animal.class));
        conversionService.addConverter(String.class, Breed.class, stringToEnumConverterFactory().getConverter(Breed.class));
        conversionService.addConverter(String.class, Gender.class, stringToEnumConverterFactory().getConverter(Gender.class));
        conversionService.addConverter(String.class, State.class, stringToEnumConverterFactory().getConverter(State.class));

        return conversionService;
    }
}
