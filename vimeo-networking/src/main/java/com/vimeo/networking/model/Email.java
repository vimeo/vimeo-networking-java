package com.vimeo.networking.model;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.vimeo.networking.logging.ClientLogger;

import java.io.IOException;
import java.io.Serializable;

/**
 * An email that the user has verified.
 * <p/>
 * Created by anthonyrestaino on 6/16/16.
 */
public class Email implements Serializable {

    private static final long serialVersionUID = -4112910222188194649L;
    private String mEmail;

    public String getEmail() {
        return mEmail;
    }

    public static class EmailTypeAdapter extends TypeAdapter<Email> {

        @Override
        public void write(JsonWriter out, Email value) throws IOException {
            out.beginObject();
            if (value == null) {
                ClientLogger.d("Email null in write()");
                out.endObject();
                return;
            }
            if (value.mEmail != null) {
                out.name("email").value(value.mEmail);
            }

            out.endObject();
        }

        @Override
        public Email read(JsonReader in) throws IOException {
            final Email email = new Email();
            in.beginObject();
            while (in.hasNext()) {
                String nextName = in.nextName();
                JsonToken jsonToken = in.peek();
                if (jsonToken == JsonToken.NULL) {
                    in.skipValue();
                    continue;
                }
                switch (nextName) {
                    case "email":
                        email.mEmail = in.nextString();
                        break;
                    default:
                        // skip any values that we do not account for, without this, the app will crash if the
                        // api provides more values than we have! [KZ] 6/15/16
                        in.skipValue();
                        break;
                }
            }
            in.endObject();
            return email;
        }
    }
}
