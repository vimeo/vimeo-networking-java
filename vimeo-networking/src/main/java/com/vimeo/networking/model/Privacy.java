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

package com.vimeo.networking.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.vimeo.networking.logging.ClientLogger;
import com.vimeo.networking.utils.EnumTypeAdapter;

import java.io.IOException;
import java.io.Serializable;

/**
 * Privacy for a video
 * <p/>
 * Created by hanssena on 4/23/15.
 */
public class Privacy implements Serializable {

    private static final long serialVersionUID = -1679908652622815871L;
    private static final String PRIVACY_NOBODY = "nobody";
    private static final String PRIVACY_USERS = "users";
    private static final String PRIVACY_ANYBODY = "anybody";
    private static final String PRIVACY_VOD = "ptv";
    private static final String PRIVACY_CONTACTS = "contacts";
    private static final String PRIVACY_PASSWORD = "password";
    private static final String PRIVACY_DISABLE = "disable";
    private static final String PRIVACY_UNLISTED = "unlisted";

    public enum PrivacyValue {
        @SerializedName(PRIVACY_NOBODY)
        NOBODY(PRIVACY_NOBODY),
        @SerializedName(PRIVACY_USERS)
        USERS(PRIVACY_USERS),
        @SerializedName(PRIVACY_ANYBODY)
        ANYBODY(PRIVACY_ANYBODY),
        @SerializedName(PRIVACY_VOD)
        VOD(PRIVACY_VOD), // "ptv"
        @SerializedName(PRIVACY_CONTACTS)
        CONTACTS(PRIVACY_CONTACTS),
        @SerializedName(PRIVACY_PASSWORD)
        PASSWORD(PRIVACY_PASSWORD),
        @SerializedName(PRIVACY_UNLISTED)
        UNLISTED(PRIVACY_UNLISTED),
        @SerializedName(PRIVACY_DISABLE)
        DISABLE(PRIVACY_DISABLE);

        private final String mText;

        PrivacyValue(String text) {
            this.mText = text;
        }

        public String getText() {
            return this.mText;
        }

        public static PrivacyValue privacyValueFromString(String text) {
            if (text != null) {
                for (PrivacyValue privacyValue : PrivacyValue.values()) {
                    if (text.equalsIgnoreCase(privacyValue.mText)) {
                        return privacyValue;
                    }
                }
            }
            return null;
        }
    }

    public PrivacyValue view;
    public String embed;
    public boolean download;
    public boolean add;
    public String comments;

    public static class PrivacyTypeAdapter extends TypeAdapter<Privacy> {

        private final static EnumTypeAdapter<PrivacyValue> sPrivacyValueEnumTypeAdapter =
                new EnumTypeAdapter<>(PrivacyValue.class);

        @Override
        public void write(JsonWriter out, Privacy value) throws IOException {
            out.beginObject();
            if (value == null) {
                ClientLogger.d("Privacy null in write()");
                out.endObject();
                return;
            }
            if (value.view != null) {
                out.name("view");
                sPrivacyValueEnumTypeAdapter.write(out, value.view);
            }
            if (value.embed != null) {
                out.name("embed").value(value.embed);
            }
            out.name("download").value(value.download);
            out.name("add").value(value.add);
            if (value.comments != null) {
                out.name("comments").value(value.comments);
            }

            out.endObject();
        }

        @Override
        public Privacy read(JsonReader in) throws IOException {
            final Privacy privacy = new Privacy();
            in.beginObject();
            while (in.hasNext()) {
                String nextName = in.nextName();
                JsonToken jsonToken = in.peek();
                if (jsonToken == JsonToken.NULL) {
                    in.skipValue();
                    continue;
                }
                switch (nextName) {
                    case "view":
                        privacy.view = sPrivacyValueEnumTypeAdapter.read(in);
                        break;
                    case "embed":
                        privacy.embed = in.nextString();
                        break;
                    case "download":
                        privacy.download = in.nextBoolean();
                        break;
                    case "add":
                        privacy.add = in.nextBoolean();
                        break;
                    case "comments":
                        privacy.comments = in.nextString();
                        break;
                    default:
                        // skip any values that we do not account for, without this, the app will crash if the
                        // api provides more values than we have! [KZ] 6/15/16
                        in.skipValue();
                        break;
                }
            }
            in.endObject();
            return privacy;
        }
    }
}
