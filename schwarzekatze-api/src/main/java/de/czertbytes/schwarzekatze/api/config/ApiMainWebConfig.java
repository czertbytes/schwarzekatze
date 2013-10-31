package de.czertbytes.schwarzekatze.api.config;

import de.czertbytes.schwarzekatze.api.converter.GsonHttpMessageConverter;
import de.czertbytes.schwarzekatze.api.interceptor.LoggingInterceptor;
import de.czertbytes.schwarzekatze.core.domain.State;
import de.czertbytes.schwarzekatze.core.domain.action.Type;
import de.czertbytes.schwarzekatze.core.domain.pet.Animal;
import de.czertbytes.schwarzekatze.core.domain.pet.Breed;
import de.czertbytes.schwarzekatze.core.domain.pet.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableArgumentResolver;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ServletWebArgumentResolverAdapter;

import java.util.List;

@Configuration
@EnableWebMvc
public class ApiMainWebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    GsonHttpMessageConverter gsonHttpMessageConverter;

    @Autowired
    Converter enumToStringConverter;

    @Autowired
    ConverterFactory stringToEnumConverterFactory;

    @Autowired
    LoggingInterceptor loggingInterceptor;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);

        argumentResolvers.add(pageableHandlerMethodArgumentResolver());
    }

    private HandlerMethodArgumentResolver pageableHandlerMethodArgumentResolver() {
        PageableArgumentResolver resolver = new PageableArgumentResolver();
        resolver.setFallbackPagable(new PageRequest(1, 10));
        resolver.setPrefix("results");

        return new ServletWebArgumentResolverAdapter(resolver);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);

        converters.add(gsonHttpMessageConverter);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        super.addFormatters(registry);

        registry.removeConvertible(Enum.class, String.class);
        registry.removeConvertible(String.class, Enum.class);

        registry.addConverter(enumToStringConverter);
        registry.addConverterFactory(stringToEnumConverterFactory);
        registry.addConverter(String.class, Type.class, stringToEnumConverterFactory.getConverter(Type.class));
        registry.addConverter(String.class, Animal.class, stringToEnumConverterFactory.getConverter(Animal.class));
        registry.addConverter(String.class, Breed.class, stringToEnumConverterFactory.getConverter(Breed.class));
        registry.addConverter(String.class, Gender.class, stringToEnumConverterFactory.getConverter(Gender.class));
        registry.addConverter(String.class, State.class, stringToEnumConverterFactory.getConverter(State.class));
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor);
    }
}
