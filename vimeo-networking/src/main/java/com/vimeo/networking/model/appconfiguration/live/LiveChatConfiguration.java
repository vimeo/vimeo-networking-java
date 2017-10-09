/*
 * Copyright (c) 2017 Vimeo (https://vimeo.com)
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
package com.vimeo.networking.model.appconfiguration.live;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;
import com.vimeo.stag.UseStag.FieldOption;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * This object stores information related to the chat client feature that
 * is available during live video playback.
 * <p>
 * Created by rigbergh on 10/2/17.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
@UseStag(FieldOption.SERIALIZED_NAME)
public class LiveChatConfiguration implements Serializable {

    private static final long serialVersionUID = 8481311601401804651L;

    @Nullable
    @SerializedName("api_key")
    private String mApiKey;

    @Nullable
    @SerializedName("app_id")
    private String mAppId;

    @Nullable
    @SerializedName("auth_domain")
    private String mAuthDomain;

    @Nullable
    @SerializedName("database_url")
    private String mDatabaseUrl;

    @Nullable
    @SerializedName("messaging_sender_id")
    private String mMessagingSenderId;

    @Nullable
    @SerializedName("project_id")
    private String mProjectId;

    @Nullable
    @SerializedName("storage_bucket")
    private String mStorageBucket;

    /**
     * @return the api key for configuring live chat (if available)
     */
    @Nullable
    public String getApiKey() {
        return mApiKey;
    }

    /**
     * @return the domain for authorization of live chat (if available)
     */
    @Nullable
    public String getAuthDomain() {
        return mAuthDomain;
    }

    /**
     * @return the database uri for live chat (if available)
     */
    @Nullable
    public String getDatabaseUrl() {
        return mDatabaseUrl;
    }

    /**
     * @return the sender id for messaging in live chat (if available)
     */
    @Nullable
    public String getMessagingSenderId() {
        return mMessagingSenderId;
    }

    /**
     * @return the project id for live chat (if available)
     */
    @Nullable
    public String getProjectId() {
        return mProjectId;
    }

    /**
     * @return the storage bucket for live chat (if available)
     */
    @Nullable
    public String getStorageBucket() {
        return mStorageBucket;
    }

    /**
     * @return the app id for live chat (if available)
     */
    @Nullable
    public String getAppId() {
        return mAppId;
    }

    void setAppId(@Nullable String appId) {
        mAppId = appId;
    }

    void setAuthDomain(@Nullable String authDomain) {
        mAuthDomain = authDomain;
    }

    void setDatabaseUrl(@Nullable String databaseUrl) {
        mDatabaseUrl = databaseUrl;
    }

    void setApiKey(@Nullable String apiKey) {
        mApiKey = apiKey;
    }

    void setMessagingSenderId(@Nullable String messagingSenderId) {
        mMessagingSenderId = messagingSenderId;
    }

    void setProjectId(@Nullable String projectId) {
        mProjectId = projectId;
    }

    void setStorageBucket(@Nullable String storageBucket) {
        mStorageBucket = storageBucket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        final LiveChatConfiguration that = (LiveChatConfiguration) o;

        if (mApiKey != null ? !mApiKey.equals(that.mApiKey) : that.mApiKey != null) { return false; }
        if (mAppId != null ? !mAppId.equals(that.mAppId) : that.mAppId != null) { return false; }
        if (mAuthDomain != null ? !mAuthDomain.equals(that.mAuthDomain) : that.mAuthDomain != null) { return false; }
        if (mDatabaseUrl != null ? !mDatabaseUrl.equals(that.mDatabaseUrl) : that.mDatabaseUrl != null) {
            return false;
        }
        if (mMessagingSenderId != null ?
                !mMessagingSenderId.equals(that.mMessagingSenderId) : that.mMessagingSenderId != null) {
            return false;
        }
        //noinspection SimplifiableIfStatement
        if (mProjectId != null ? !mProjectId.equals(that.mProjectId) : that.mProjectId != null) { return false; }
        return mStorageBucket != null ? mStorageBucket.equals(that.mStorageBucket) : that.mStorageBucket == null;

    }

    @Override
    public int hashCode() {
        int result = mApiKey != null ? mApiKey.hashCode() : 0;
        result = 31 * result + (mAppId != null ? mAppId.hashCode() : 0);
        result = 31 * result + (mAuthDomain != null ? mAuthDomain.hashCode() : 0);
        result = 31 * result + (mDatabaseUrl != null ? mDatabaseUrl.hashCode() : 0);
        result = 31 * result + (mMessagingSenderId != null ? mMessagingSenderId.hashCode() : 0);
        result = 31 * result + (mProjectId != null ? mProjectId.hashCode() : 0);
        result = 31 * result + (mStorageBucket != null ? mStorageBucket.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LiveChatConfiguration{" +
               "mApiKey='" + mApiKey + '\'' +
               ", mAppId='" + mAppId + '\'' +
               ", mAuthDomain='" + mAuthDomain + '\'' +
               ", mDatabaseUrl='" + mDatabaseUrl + '\'' +
               ", mMessagingSenderId='" + mMessagingSenderId + '\'' +
               ", mProjectId='" + mProjectId + '\'' +
               ", mStorageBucket='" + mStorageBucket + '\'' +
               '}';
    }
}
