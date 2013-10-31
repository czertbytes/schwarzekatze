package de.czertbytes.schwarzekatze.api.domain;

import java.util.List;

public class Pets {

    private final List<Pet> pets;

    public Pets(List<Pet> pets) {
        this.pets = pets;
    }

    public List<Pet> getPets() {
        return pets;
    }
}
