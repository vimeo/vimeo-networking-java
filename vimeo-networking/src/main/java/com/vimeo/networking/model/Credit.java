package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * A model representing a credit.
 * Created by zetterstromk on 1/11/17.
 */
@SuppressWarnings("unused")
@UseStag
public class Credit implements Serializable {

    private static final long serialVersionUID = 6037404487282167384L;

    @Nullable
    @SerializedName("uri")
    protected String mUri;

    @Nullable
    @SerializedName("role")
    protected String mRole;

    @Nullable
    @SerializedName("name")
    protected String mName;

    @Nullable
    @SerializedName("video")
    protected Video mVideo;

    @Nullable
    @SerializedName("user")
    protected User mUser;

    @Nullable
    public String getUri() {
        return mUri;
    }

    @Nullable
    public String getRole() {
        return mRole;
    }

    @Nullable
    public String getName() {
        return mName;
    }

    @Nullable
    public Video getVideo() {
        return mVideo;
    }

    public void setVideo(@Nullable Video video) {
        this.mVideo = video;
    }

    @Nullable
    public User getUser() {
        return mUser;
    }

    public void setUser(@Nullable User user) {
        this.mUser = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Credit credit = (Credit) o;

        if (mUri != null ? !mUri.equals(credit.mUri) : credit.mUri != null) {
            return false;
        }
        if (mRole != null ? !mRole.equals(credit.mRole) : credit.mRole != null) {
            return false;
        }
        if (mName != null ? !mName.equals(credit.mName) : credit.mName != null) {
            return false;
        }
        if (mVideo != null ? !mVideo.equals(credit.mVideo) : credit.mVideo != null) {
            return false;
        }
        return mUser != null ? mUser.equals(credit.mUser) : credit.mUser == null;

    }

    @Override
    public int hashCode() {
        int result = mUri != null ? mUri.hashCode() : 0;
        result = 31 * result + (mRole != null ? mRole.hashCode() : 0);
        result = 31 * result + (mName != null ? mName.hashCode() : 0);
        result = 31 * result + (mVideo != null ? mVideo.hashCode() : 0);
        result = 31 * result + (mUser != null ? mUser.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Credit{" +
               "uri='" + mUri + '\'' +
               ", role='" + mRole + '\'' +
               ", name='" + mName + '\'' +
               ", video=" + mVideo +
               ", user=" + mUser +
               '}';
    }
}
