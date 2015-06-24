package com.vimeo.networking.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by alfredhanssen on 4/12/15.
 */
public class Video implements Serializable {

    private static final long serialVersionUID = -1282907783845240057L;

    public enum ContentRating // TODO: use this enum [AH] 4/24/2015
    {
        SAFE,
        UNRATED,
        NUDITY,
        LANGUAGE,
        DRUGS,
        VIOLENCE
    }

    public enum LicenseValue // TODO: use this, https://developer.vimeo.com/api/playground/creativecommons [AH] 4/24/2015
    {
        ATTRIBUTION,
        ATTRIBUTION_SHARE_ALIKE,
        ATTRIBUTION_NO_DERIVATIVES,
        ATTRIBUTION_NON_COMMERCIAL,
        ATTRIBUTION_NON_COMMERCIAL_SHARE_ALIKE,
        ATTRIBUTION_NON_COMMERCIAL_NO_DERIVATIVES,
        PUBLIC_DOMAIN_DEDICATION
    }

    public String uri;
    public String name;
    public String description;
    public String link;
    public int duration;
    public ArrayList<VideoFile> files;
    public int width;
    public int height;
    public Embed embed;
    public String language;
    public Date createdTime;
    public Date modifiedTime;
    public ArrayList<String> contentRating;
    public String license;
    public com.vimeo.networking.model.Privacy privacy;
    public PictureCollection pictures;
    public ArrayList<Tag> tags;
    public StatsCollection stats;
    public Metadata metadata;
    public com.vimeo.networking.model.User user;
    public String status;

    public boolean canLike() {
        if (metadata != null && metadata.interactions != null && metadata.interactions.like != null) {
            return true;
        }
        return false;
    }

    public boolean isLiked() {
        if (metadata != null && metadata.interactions != null && metadata.interactions.like != null) {
            return metadata.interactions.like.added;
        }
        return false;
    }

    public boolean canWatchLater() {
        if (metadata != null && metadata.interactions != null && metadata.interactions.watchlater != null) {
            return true;
        }
        return false;
    }

    public boolean isWatchLater() {
        if (metadata != null && metadata.interactions != null && metadata.interactions.watchlater != null) {
            return metadata.interactions.watchlater.added;
        }
        return false;
    }

    public int likeCount() {
        if ((metadata != null) && (metadata.connections != null) && (metadata.connections.likes != null)) {
            return metadata.connections.likes.total;
        }

        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Video that = (Video) o;

        return ((this.uri != null && that.uri != null) ? this.uri.equals(that.uri) : false);
    }

    @Override
    public int hashCode() {
        return this.uri != null ? this.uri.hashCode() : 0;
    }

}
