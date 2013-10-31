package de.czertbytes.schwarzekatze.core.converter;

import de.czertbytes.schwarzekatze.core.config.CoreConfig;
import de.czertbytes.schwarzekatze.core.domain.State;
import de.czertbytes.schwarzekatze.core.domain.pet.Animal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CoreConfig.class })
public class StringToEnumConverterFactoryTest {

    @Autowired
    ConversionService conversionService;

    @Test
    public void animalStringToEnum() {
        assertThat(Animal.CAT, is(conversionService.convert("cat", Animal.class)));
        assertThat(Animal.CAT, is(conversionService.convert("cAt", Animal.class)));
        assertThat(Animal.CAT, is(conversionService.convert("   cAt   ", Animal.class)));
        assertThat(Animal.DOG, is(conversionService.convert("dog", Animal.class)));
        assertThat(Animal.BIRD, is(conversionService.convert("bird", Animal.class)));

        assertThat("cat", is(conversionService.convert(Animal.CAT, String.class)));
        assertThat("dog", is(conversionService.convert(Animal.DOG, String.class)));
        assertThat("bird", is(conversionService.convert(Animal.BIRD, String.class)));
    }

    @Test
    public void stateStringToEnum() {
        assertThat(State.CLOSED_WITH_SUCCESS, is(conversionService.convert("closed-with-success", State.class)));
    }
}
