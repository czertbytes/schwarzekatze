package de.czertbytes.schwarzekatze.api.converter.gson;

import com.google.gson.*;
import org.joda.time.DateTime;

import java.lang.reflect.Type;

public class DateTimeTypeConverter implements JsonSerializer<DateTime>, JsonDeserializer<DateTime> {

    @Override
    public JsonElement serialize(DateTime src, Type srcType, JsonSerializationContext context) {
        return new JsonPrimitive(src.toString());
    }

    @Override
    public DateTime deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        return new DateTime(json.getAsString());
    }
}