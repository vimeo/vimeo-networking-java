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

import org.jetbrains.annotations.Nullable;

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

    public VimeoAccount(@Nullable String accessToken) {
        this.accessToken = accessToken;
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
    public String getUserJSON() // For AccountManager.userData [AH]
    {
        if (this.user == null) {
            return null;
        }

        if (this.userJSON != null) {
            return this.userJSON;
        }

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        this.userJSON = gson.toJson(this.user);

        return this.userJSON;
    }
}
