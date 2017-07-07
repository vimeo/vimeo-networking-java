package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Mohit Sarveiya on 6/29/17.
 */
@UseStag
@SuppressWarnings({"unused", "WeakerAccess"})
public class TextTrack implements Serializable {

    private static final long serialVersionUID = 3019422318347933808L;

    @Nullable
    @SerializedName("uri")
    private String mUri;

    @SerializedName("active")
    private boolean mActive;

    @Nullable
    @SerializedName("type")
    private String mType;

    @Nullable
    @SerializedName("language")
    private String mLanguage;

    @Nullable
    @SerializedName("link")
    private String mLink;

    @Nullable
    @SerializedName("link_expires_time")
    private Date linkExpiresTime;

    @Nullable
    @SerializedName("hls_link")
    private String hlsLink;

    @Nullable
    @SerializedName("hls_link_expires_time")
    private Date hlsLinkExpiresTime;

    @Nullable
    @SerializedName("name")
    private String name;

    @Nullable
    public String getUri() {
        return mUri;
    }

    public void setUri(@Nullable String uri) {
        mUri = uri;
    }

    public boolean getActive() {
        return mActive;
    }

    public void setActive(boolean active) {
        mActive = active;
    }

    @Nullable
    public String getType() {
        return mType;
    }

    public void setType(@Nullable String type) {
        mType = type;
    }

    @Nullable
    public String getLanguage() {
        return mLanguage;
    }

    public void setLanguage(@Nullable String language) {
        mLanguage = language;
    }

    @Nullable
    public String getLink() {
        return mLink;
    }

    public void setLink(@Nullable String link) {
        mLink = link;
    }

    @Nullable
    public Date getLinkExpiresTime() {
        return linkExpiresTime;
    }

    public void setLinkExpiresTime(@Nullable Date linkExpiresTime) {
        this.linkExpiresTime = linkExpiresTime;
    }

    @Nullable
    public String getHlsLink() {
        return hlsLink;
    }

    public void setHlsLink(@Nullable String hlsLink) {
        this.hlsLink = hlsLink;
    }

    @Nullable
    public Date getHlsLinkExpiresTime() {
        return hlsLinkExpiresTime;
    }

    public void setHlsLinkExpiresTime(@Nullable Date hlsLinkExpiresTime) {
        this.hlsLinkExpiresTime = hlsLinkExpiresTime;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }
}
