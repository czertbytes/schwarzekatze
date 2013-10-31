package de.czertbytes.schwarzekatze.api.domain;

import java.util.List;

public class Pictures {

    private final List<Picture> pictures;

    public Pictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public List<Picture> getPictures() {
        return pictures;
    }
}
