/*
 * Copyright (c) 2015 Vimeo (https://vimeo.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.vimeo.networking.utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.vimeo.networking.logging.ClientLogger;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * We have to write our own serializer and deserializer because Android doesn't currently
 * support ISO 8601. The SimpleDateFormat link below shows 'ZZZZZ' as what we want for timezone
 * but this is only available in Android 4.3 and up. Java 7 has the 'X' character to represent
 * ISO 8601 but Android treats this differently. So in the mean time the ISO8601 class will convert
 * the strings to RFC 822 which is something the DateFormatter can handle regardless of version.
 * <p/>
 * {@link <a href="http://developer.android.com/reference/java/text/SimpleDateFormat.html">Android SimpleDateFormat</a>}
 * {@link <a href="https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html">Oracle SimpleDateFormat</a>}
 * {@link <a href="http://stackoverflow.com/questions/2201925/converting-iso-8601-compliant-string-to-java-util-date">Stack Overflow</a>}
 * <p/>
 * Created by kylevenn on 8/26/15.
 */
public final class ISO8601 {

    private ISO8601() {
    }

    // There was a bug in Android 2.1 that looks like it would arbitrary GC a static SDF
    // If supporting Android 2.1, be aware of this [KV]
    // Using RFC 822 since we can't use ISO 8601
    private static final SimpleDateFormat dateFormat =
            new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH);
    private static final int RFC_ISO_COLON_CHAR_POSITION = 22;

    public static JsonSerializer<Date> getDateSerializer() {
        return new JsonSerializer<Date>() {
            @Override
            public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
                return src == null ? null : new JsonPrimitive(fromDate(src));
            }
        };
    }

    public static JsonDeserializer<Date> getDateDeserializer() {
        return new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                    throws JsonParseException {
                try {
                    return json == null ? null : toDate(json.getAsString());
                } catch (ParseException e) {
                    // Return an null date if it is formatted incorrectly
                    ClientLogger.e("Incorrectly formatted date sent from server", e);
                    return null;
                }
            }
        };
    }

    /** Transform Date to ISO 8601 string. */
    public static String fromDate(final Date date) {
        String formatted = dateFormat.format(date);
        return formatted.substring(0, RFC_ISO_COLON_CHAR_POSITION) + ":" +
               formatted.substring(RFC_ISO_COLON_CHAR_POSITION);
    }

    /** Get current date and time formatted as ISO 8601 string. */
    public static String now() {
        return fromDate(new Date());
    }

    /** Transform ISO 8601 string to Date. */
    public static Date toDate(final String iso8601string) throws ParseException {
        // Account for ISO 8601 standard of using the character 'Z' to represent UTC
        String s = iso8601string.replace("Z", "+00:00");
        try {
            s = s.substring(0, RFC_ISO_COLON_CHAR_POSITION) +
                s.substring(RFC_ISO_COLON_CHAR_POSITION);  // To get rid of the ":" to conform to RFC 822
        } catch (IndexOutOfBoundsException e) {
            // Throw a ParseException because all ISO 8601 strings should be of the same length
            throw new ParseException("Invalid length", 0);
        }

        return dateFormat.parse(s);
    }
}
