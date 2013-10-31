package de.czertbytes.schwarzekatze.api.config;

import de.czertbytes.schwarzekatze.core.config.persistence.CorePersistencePostgresConfig;
import de.czertbytes.schwarzekatze.core.config.repository.CoreRepositoryPostgresConfig;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
@Import({
        CorePersistencePostgresConfig.class,
        CoreRepositoryPostgresConfig.class
})
@ComponentScan({
        "de.czertbytes.schwarzekatze.core",
        "de.czertbytes.schwarzekatze.api"
})
public class ApiMainConfig {

    @Bean
    public MessageSource messageSource() {
        final ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();

        resourceBundleMessageSource.setBasename("messages");

        return resourceBundleMessageSource;
    }


}
