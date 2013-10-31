package de.czertbytes.schwarzekatze.api.converter.gson;

import com.google.gson.*;
import de.czertbytes.schwarzekatze.core.util.EnumUtils;

import java.lang.reflect.Type;

public class EnumTypeConverter implements JsonSerializer<Enum>, JsonDeserializer<Enum> {

    @Override
    public Enum deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return EnumUtils.fromString(json.getAsString(), typeOfT);
    }

    @Override
    public JsonElement serialize(Enum src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.toString());
    }
}
