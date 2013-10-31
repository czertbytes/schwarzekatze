package de.czertbytes.schwarzekatze.core.domain.pet;

import org.springframework.util.StringUtils;

public enum Gender {

    MALE,
    FEMALE,
    UNKNOWN;

    public static Gender getFromString(final String gender) {
        if (StringUtils.hasText(gender)) {
            String gender2 = gender.replaceAll("\\-", "_");

            for (Gender tmp : Gender.values()) {
                if (tmp.name().equalsIgnoreCase(gender2)) {
                    return tmp;
                }
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return name().replaceAll("\\_", "-").toLowerCase();
    }
}
