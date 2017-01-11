package com.vimeo.networking.model;

import com.vimeo.stag.GsonAdapterKey;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * A model representing a credit.
 * Created by zetterstromk on 1/11/17.
 */
public class Credit implements Serializable {

    private static final long serialVersionUID = 6037404487282167384L;

    @Nullable
    @GsonAdapterKey("uri")
    String mUri;

    @Nullable
    @GsonAdapterKey("role")
    String mRole;

    @Nullable
    @GsonAdapterKey("name")
    String mName;

    @Nullable
    @GsonAdapterKey("video")
    Video mVideo;

    @Nullable
    @GsonAdapterKey("user")
    User mUser;

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

    @Nullable
    public User getUser() {
        return mUser;
    }
}
