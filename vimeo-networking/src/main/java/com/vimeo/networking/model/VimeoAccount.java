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

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.vimeo.networking.logging.ClientLogger;
import com.vimeo.networking.model.User.UserTypeAdapter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.Serializable;

/**
 * This class represents an authenticated account with Vimeo. It can be through client credentials or a
 * truly authenticated {@link User}
 * <p/>
 * Created by alfredhanssen on 4/12/15.
 */
public class VimeoAccount implements Serializable {

    private static final long serialVersionUID = -8341071767843490585L;
    //    private static final String TOKEN_TYPE_BEARER = "bearer";

    private String accessToken;
    private String tokenType;
    private String scope;
    private User user;
    private String userJSON;

    private VimeoAccount() {
        // Needed for TypeAdapter read
    }

    public VimeoAccount(@Nullable String accessToken) {
        this.accessToken = accessToken;
    }

    public VimeoAccount(String accessToken, String tokenType, String scope, String userJSON) {
        if (accessToken == null || accessToken.isEmpty() || tokenType == null ||
            tokenType.isEmpty() || scope == null || scope.isEmpty()) {
            throw new AssertionError("Account can only be created with token, tokenType, scope");
        }

        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.scope = scope;

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        this.user = gson.fromJson(userJSON, User.class);
    }

    public boolean isAuthenticated() {
        return (this.accessToken != null && !this.accessToken.isEmpty());
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getTokenType() {
        return this.tokenType;
    }

    public String getScope() {
        return this.scope;
    }

    @Nullable
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Nullable
    public String getUserJSON() { // For AccountManager.userData [AH]
        if (this.user == null) {
            return null;
        }

        if (this.userJSON != null) {
            return this.userJSON;
        }

        Gson gson = new GsonBuilder().registerTypeAdapterFactory(new User.Factory()).create();

        this.userJSON = gson.toJson(this.user);

        return this.userJSON;
    }

    public static class VimeoAccountTypeAdapter extends TypeAdapter<VimeoAccount> {

        @NotNull
        private final UserTypeAdapter mUserTypeAdapter;

        public VimeoAccountTypeAdapter(@NotNull UserTypeAdapter userTypeAdapter) {
            mUserTypeAdapter = userTypeAdapter;
        }

        @Override
        public void write(JsonWriter out, VimeoAccount value) throws IOException {
            out.beginObject();
            if (value == null) {
                ClientLogger.d("VimeoAccount null in write()");
                out.endObject();
                return;
            }
            if (value.accessToken != null) {
                out.name("access_token").value(value.accessToken);
            }
            if (value.tokenType != null) {
                out.name("token_type").value(value.tokenType);
            }
            if (value.scope != null) {
                out.name("scope").value(value.scope);
            }
            if (value.user != null) {
                out.name("user");
                mUserTypeAdapter.write(out, value.user);
            }
            out.endObject();
        }

        @Override
        public VimeoAccount read(JsonReader in) throws IOException {
            final VimeoAccount vimeoAccount = new VimeoAccount();

            in.beginObject();
            while (in.hasNext()) {
                String nextName = in.nextName();
                JsonToken jsonToken = in.peek();
                if (jsonToken == JsonToken.NULL) {
                    in.skipValue();
                    continue;
                }
                switch (nextName) {
                    case "access_token":
                        vimeoAccount.accessToken = in.nextString();
                        break;
                    case "token_type":
                        vimeoAccount.tokenType = in.nextString();
                        break;
                    case "scope":
                        vimeoAccount.scope = in.nextString();
                        break;
                    case "user":
                        vimeoAccount.user = mUserTypeAdapter.read(in);
                        break;
                    default:
                        // skip any values that we do not account for, without this, the app will crash if the
                        // api provides more values than we have! [KZ] 6/15/16
                        in.skipValue();
                        break;
                }
            }
            in.endObject();

            return vimeoAccount;
        }
    }

    public static class Factory implements TypeAdapterFactory {

        @SuppressWarnings("unchecked")
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            if (VimeoAccount.class.isAssignableFrom(type.getRawType())) {
                TypeAdapter typeAdapter = gson.getAdapter(User.class);
                if (typeAdapter instanceof UserTypeAdapter) {
                    return (TypeAdapter<T>) new VimeoAccountTypeAdapter((UserTypeAdapter) typeAdapter);
                }
            }

            // by returning null, Gson will never check this factory if it can handle this TypeToken again [KZ] 6/15/16
            return null;
        }
    }
}
