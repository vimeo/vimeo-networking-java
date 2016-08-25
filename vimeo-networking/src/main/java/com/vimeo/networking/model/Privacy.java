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

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.GsonAdapterKey;

import java.io.Serializable;

/**
 * Created by hanssena on 4/23/15.
 */
public class Privacy implements Serializable {

    private static final long serialVersionUID = -1679908652622815871L;
    private static final String PRIVACY_NOBODY = "nobody";
    private static final String PRIVACY_USERS = "users";
    public static final String PRIVACY_ANYBODY = "anybody";
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

        private final String text;

        PrivacyValue(String text) {
            this.text = text;
        }

        public String getText() {
            return this.text;
        }

        public static PrivacyValue privacyValueFromString(String string) {
            if (string != null) {
                for (PrivacyValue privacyValue : PrivacyValue.values()) {
                    if (string.equalsIgnoreCase(privacyValue.text)) {
                        return privacyValue;
                    }
                }
            }
            return null;
        }
    }

    @GsonAdapterKey("view")
    public PrivacyValue view;
    @GsonAdapterKey("embed")
    public String embed;
    @GsonAdapterKey("download")
    public boolean download;
    @GsonAdapterKey("add")
    public boolean add;
    @GsonAdapterKey("comments")
    public PrivacyValue comments;
}
