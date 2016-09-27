package com.vimeo.networking.utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.vimeo.networking.logging.ClientLogger;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;

/**
 * A wrapper class that serializes and deserializes dates
 * using the {@link ISO8601Utils} class that catches errors
 * and reports them to the client application, allowing
 * them to decide if they should crash or not. If we just
 * rely on the default adapter used by Gson, we are unable
 * to absorb date parsing errors or log them correctly.
 * Additionally, the default adapter was not serializing the
 * dates correctly.
 */
@SuppressWarnings("WeakerAccess")
public final class ISO8601Wrapper {

    private ISO8601Wrapper() {
    }

    public static JsonSerializer<Date> getDateSerializer() {
        return new JsonSerializer<Date>() {
            @Override
            public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
                return src == null ? null : new JsonPrimitive(ISO8601Utils.format(src));
            }
        };
    }

    public static JsonDeserializer<Date> getDateDeserializer() {
        return new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                    throws JsonParseException {
                try {
                    return json == null ? null : ISO8601Utils.parse(json.getAsString(), new ParsePosition(0));
                } catch (ParseException e) {
                    ClientLogger.e("Incorrectly formatted date sent from server: " + json.getAsString(), e);
                    return null;
                }
            }
        };
    }
}