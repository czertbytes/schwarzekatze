package de.czertbytes.schwarzekatze.core.domain;

import de.czertbytes.schwarzekatze.core.util.EnumUtils;

public enum State {

    INITIATED,
    ACTIVE,
    DISABLED,
    BLOCKED,

    //  pet
    VERIFIED,

    //  actions
    CLOSED_WITH_SUCCESS,
    CLOSED_WITHOUT_SUCCESS,

    //  pictures
    WAITING_FOR_REVIEW,
    APPROVED,
    REJECTED,

    TEST,
    DELETED;

    @Override
    public String toString() {
        return EnumUtils.toString(this);
    }
}
