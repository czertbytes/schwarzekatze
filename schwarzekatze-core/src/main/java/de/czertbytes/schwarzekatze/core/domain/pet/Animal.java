package de.czertbytes.schwarzekatze.core.domain.pet;

import de.czertbytes.schwarzekatze.core.util.EnumUtils;

public enum Animal {

    CAT(1),
    DOG(2),
    RODENT(3),
    REPTILE(4),
    BIRD(5),

    OTHER(0);

    private transient Integer breedPrefix;

    private Animal(Integer breedPrefix) {
        this.breedPrefix = breedPrefix;
    }

    public Integer getBreedPrefix() {
        return breedPrefix;
    }

    public static Animal fromString(final String animal) {
        return EnumUtils.fromString(animal, Animal.class);
    }

    @Override
    public String toString() {
        return EnumUtils.toString(this);
    }
}
