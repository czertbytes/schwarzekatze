package de.czertbytes.schwarzekatze.api.domain;

import java.util.List;

public class Shelters {

    private final List<Shelter> shelters;

    public Shelters(List<Shelter> shelters) {
        this.shelters = shelters;
    }

    public List<Shelter> getShelters() {
        return shelters;
    }
}
