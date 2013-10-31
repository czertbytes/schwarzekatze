package de.czertbytes.schwarzekatze.core.domain.pet;

import de.czertbytes.schwarzekatze.core.util.EnumUtils;

public enum Breed {

    //  CATS
    DOMESTIC(1000),
    DEVON_REX(1001),

    //  DOGS
    DALMATIAN(2000),
    SHEPARD_DOG(2001),

    //  RODENTS
    HAMSTER(3001),
    RABBIT(3002),

    //  REPTILES
    SNAKE(4001),

    //  BIRDS
    TUKAN(5001);

    private transient Integer key;

    private Breed(Integer key) {
        this.key = key;
    }

    public Integer getKey() {
        return key;
    }

    public static Breed fromString(final String breed) {
        return EnumUtils.fromString(breed, Breed.class);
    }

    @Override
    public String toString() {
        return EnumUtils.toString(this);
    }
}
