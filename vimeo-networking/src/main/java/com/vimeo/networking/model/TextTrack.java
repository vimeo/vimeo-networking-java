package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Mohit Sarveiya on 6/29/17.
 */
@UseStag
@SuppressWarnings({"unused", "WeakerAccess"})
public class TextTrack implements Serializable {

    private static final long serialVersionUID = 3019422318347933808L;

    @SerializedName("uri")
    protected String mUri;

    @SerializedName("active")
    protected Boolean mActive;

    @SerializedName("type")
    protected String mType;

    @SerializedName("language")
    protected String mLanguage;

    @SerializedName("link")
    protected String mLink;

    @SerializedName("link_expires_time")
    protected Date linkExpiresTime;

    @SerializedName("hls_link")
    protected String hlsLink;

    @SerializedName("hls_link_expires_time")
    protected Date hlsLinkExpiresTime;

    @SerializedName("name")
    protected String name;

    public String getUri() {
        return mUri;
    }

    public void setUri(String uri) {
        mUri = uri;
    }

    public Boolean getActive() {
        return mActive;
    }

    public void setActive(Boolean active) {
        mActive = active;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getLanguage() {
        return mLanguage;
    }

    public void setLanguage(String language) {
        mLanguage = language;
    }

    public String getLink() {
        return mLink;
    }

    public void setLink(String link) {
        mLink = link;
    }

    public Date getLinkExpiresTime() {
        return linkExpiresTime;
    }

    public void setLinkExpiresTime(Date linkExpiresTime) {
        this.linkExpiresTime = linkExpiresTime;
    }

    public String getHlsLink() {
        return hlsLink;
    }

    public void setHlsLink(String hlsLink) {
        this.hlsLink = hlsLink;
    }

    public Date getHlsLinkExpiresTime() {
        return hlsLinkExpiresTime;
    }

    public void setHlsLinkExpiresTime(Date hlsLinkExpiresTime) {
        this.hlsLinkExpiresTime = hlsLinkExpiresTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
