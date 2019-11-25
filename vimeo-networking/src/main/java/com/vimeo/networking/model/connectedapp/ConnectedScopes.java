package com.vimeo.networking.model.connectedapp;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Provides the lists of scopes that are required for third-party connected app features.
 */
public class ConnectedScopes implements Serializable {

    private static final long serialVersionUID = -4253331609435421704L;

    @Nullable
    @SerializedName("publish_to_social")
    private List<String> mPublishToSocial;

    @Nullable
    @SerializedName("simulcast")
    private List<String> mSimulcast;

    /**
     * @return All scopes required for publishing to a specific social media platform.
     */
    @Nullable
    public List<String> getPublishToSocial() {
        return mPublishToSocial == null ? null : Collections.unmodifiableList(mPublishToSocial);
    }

    public void setPublishToSocial(@Nullable List<String> publishToSocial) {
        mPublishToSocial = publishToSocial == null ? null : Collections.unmodifiableList(publishToSocial);
    }

    /**
     * @return All scopes required for simulcasting to a specific social media platform.
     */
    @Nullable
    public List<String> getSimulcast() {
        return mSimulcast == null ? null : Collections.unmodifiableList(mSimulcast);
    }

    /**
     * Set all scopes required for publishing to a specific social media platform.
     * @param simulcast A list of scope permissions
     */
    public void setSimulcast(@Nullable List<String> simulcast) {
        mSimulcast = simulcast == null ? null : Collections.unmodifiableList(simulcast);
    }
}
