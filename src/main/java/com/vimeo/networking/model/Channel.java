package com.vimeo.networking.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zetterstromk on 6/11/15.
 */
public class Channel implements Serializable {

    private static final long serialVersionUID = 3190410523525111858L;

    public String uri;
    public String name;
    public String description;
    public String link;
    public Date createdTime;
    public Date modifiedTime;
    public User user;
    public PictureCollection pictures;
    public PictureCollection header;
    public Privacy privacy;
    public Metadata metadata;

    public boolean canFollow() {
        if (metadata != null && metadata.interactions != null && metadata.interactions.follow != null) {
            return true;
        }
        return false;
    }

    public boolean isFollowing() {
        if (metadata != null && metadata.interactions != null && metadata.interactions.follow != null) {
            return metadata.interactions.follow.added;
        }
        return false;
    }

    public int userCount() {
        if (metadata != null && metadata.connections != null && metadata.connections.users != null) {
            return metadata.connections.users.total;
        }
        return 0;
    }

    public int videoCount() {
        if (metadata != null && metadata.connections != null && metadata.connections.videos != null) {
            return metadata.connections.videos.total;
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

        Channel that = (Channel) o;

        return ((this.uri != null && that.uri != null) ? this.uri.equals(that.uri) : false);
    }

    @Override
    public int hashCode() {
        return this.uri != null ? this.uri.hashCode() : 0;
    }
}
