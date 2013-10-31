package de.czertbytes.schwarzekatze.api.domain;

import de.czertbytes.schwarzekatze.core.domain.action.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

public class Action {

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:ssZ")
    private DateTime dateTime;
    private Type type;
    private String location;
    private String description;

    public Action() {
    }

    public Action(DateTime dateTime, Type type, String location, String description) {
        this.dateTime = dateTime;
        this.type = type;
        this.location = location;
        this.description = description;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
