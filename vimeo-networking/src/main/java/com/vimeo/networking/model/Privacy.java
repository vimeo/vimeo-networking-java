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
import com.vimeo.stag.UseStag;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * Created by hanssena on 4/23/15.
 */
@SuppressWarnings("unused")
// TODO: Figure out how to enable UseStag on this class without breaking deserialization due to the API giving us back integers for add and download 2/1/17 [AR]
// @UseStag(FieldOption.SERIALIZED_NAME)
public class Privacy implements Serializable {

    private static final long serialVersionUID = -1679908652622815871L;
    private static final String PRIVACY_NOBODY = "nobody";
    private static final String PRIVACY_USERS = "users";
    private static final String PRIVACY_ANYBODY = "anybody";
    private static final String PRIVACY_PRIVATE = "private";
    private static final String PRIVACY_PUBLIC = "public";
    private static final String PRIVACY_TVOD = "ptv";
    private static final String PRIVACY_CONTACTS = "contacts";
    private static final String PRIVACY_PASSWORD = "password";
    private static final String PRIVACY_DISABLE = "disable";
    private static final String PRIVACY_UNLISTED = "unlisted";
    private static final String PRIVACY_WHITE_LIST = "white_list";
    private static final String PRIVACY_STOCK = "stock";

    @UseStag
    public enum CommentValue {

        @SerializedName(PRIVACY_ANYBODY)
        ANYBODY(PRIVACY_ANYBODY),

        @SerializedName(PRIVACY_CONTACTS)
        CONTACTS(PRIVACY_CONTACTS),

        @SerializedName(PRIVACY_NOBODY)
        NOBODY(PRIVACY_NOBODY);

        @NotNull
        private final String text;

        CommentValue(@NotNull String text) {
            this.text = text;
        }

        @Nullable
        public static CommentValue privacyValueFromString(@Nullable final String string) {
            if (string != null) {
                for (final CommentValue privacyValue : CommentValue.values()) {
                    if (string.equalsIgnoreCase(privacyValue.text)) {
                        return privacyValue;
                    }
                }
            }
            return null;
        }
    }

    @UseStag
    public enum EmbedValue {

        @SerializedName(PRIVACY_PRIVATE)
        PRIVATE(PRIVACY_PRIVATE),

        @SerializedName(PRIVACY_PUBLIC)
        PUBLIC(PRIVACY_PUBLIC),

        @SerializedName(PRIVACY_WHITE_LIST)
        WHITE_LIST(PRIVACY_WHITE_LIST);

        @NotNull
        private final String text;

        EmbedValue(@NotNull String text) {
            this.text = text;
        }

        @Nullable
        public static EmbedValue privacyValueFromString(@Nullable final String string) {
            if (string != null) {
                for (final EmbedValue privacyValue : EmbedValue.values()) {
                    if (string.equalsIgnoreCase(privacyValue.text)) {
                        return privacyValue;
                    }
                }
            }
            return null;
        }
    }

    @UseStag
    public enum ViewValue {

        @SerializedName(PRIVACY_STOCK)
        STOCK(PRIVACY_STOCK),

        @SerializedName(PRIVACY_ANYBODY)
        ANYBODY(PRIVACY_ANYBODY),

        @SerializedName(PRIVACY_CONTACTS)
        CONTACTS(PRIVACY_CONTACTS),

        @SerializedName(PRIVACY_DISABLE)
        DISABLE(PRIVACY_DISABLE),

        @SerializedName(PRIVACY_NOBODY)
        NOBODY(PRIVACY_NOBODY),

        @SerializedName(PRIVACY_PASSWORD)
        PASSWORD(PRIVACY_PASSWORD),

        @SerializedName(PRIVACY_UNLISTED)
        UNLISTED(PRIVACY_UNLISTED),

        @SerializedName(PRIVACY_USERS)
        USERS(PRIVACY_USERS),

        @SerializedName(PRIVACY_TVOD)
        TVOD(PRIVACY_TVOD);

        @NotNull
        private final String text;

        ViewValue(@NotNull String text) {
            this.text = text;
        }

        @NotNull
        public String getText() {
            return this.text;
        }

        @Nullable
        public static ViewValue privacyValueFromString(@Nullable final String string) {
            if (string != null) {
                for (final ViewValue privacyValue : ViewValue.values()) {
                    if (string.equalsIgnoreCase(privacyValue.text)) {
                        return privacyValue;
                    }
                }
            }
            return null;
        }
    }

    @Nullable
    @SerializedName("view")
    public ViewValue mView;

    @Nullable
    @SerializedName("embed")
    public EmbedValue mEmbed;

    @SerializedName("download")
    public boolean mDownload;

    @SerializedName("add")
    public boolean mAdd;

    @Nullable
    @SerializedName("comments")
    public CommentValue mComments;

    @Nullable
    @SerializedName("_bypass_token")
    public String mBypassToken;

    @Nullable
    public ViewValue getView() {
        return mView;
    }

    @Nullable
    public EmbedValue getEmbed() {
        return mEmbed;
    }

    public boolean isDownload() {
        return mDownload;
    }

    public boolean isAdd() {
        return mAdd;
    }

    @Nullable
    public CommentValue getComments() {
        return mComments;
    }

    public String getBypassToken() {
        return mBypassToken;
    }
}
