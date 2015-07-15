package com.vimeo.networking.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zetterstromk on 6/24/15.
 */
public class FeedItem implements Serializable {

    private static final long serialVersionUID = -8744477085158366576L;

    //TODO I have only seen like/channel types, we need more types [KZ]
    public enum AttributionType {
        UPLOAD,
        LIKE,
        CHANNEL,
        GROUP,
        TAG,
        CREDIT,
        SHARE,
        NONE
    }

    public String uri;
    public Video clip;
    public String type;
    public Date time;
    public User user;   // from like type
    public Channel channel; // from channel type
    public Tag tag;
    public Group group;

    public AttributionType getType() {
        if (type.equalsIgnoreCase("channel")) {
            return AttributionType.CHANNEL;
        } else if (type.equalsIgnoreCase("like")) {
            return AttributionType.LIKE;
        } else if (type.equalsIgnoreCase("upload")) {
            return AttributionType.UPLOAD;
        } else if (type.equalsIgnoreCase("tag")) {
            return AttributionType.TAG;
        } else if (type.equalsIgnoreCase("group")) {
            return AttributionType.GROUP;
        } else if (type.equalsIgnoreCase("appearance")) {
            return AttributionType.CREDIT;
        } else if (type.equalsIgnoreCase("share")) {
            return AttributionType.SHARE;
        }

        return AttributionType.NONE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FeedItem that = (FeedItem) o;

        return ((this.clip.uri != null && that.clip.uri != null) && this.clip.uri.equals(that.clip.uri));
    }

    @Override
    public int hashCode() {
        return this.clip.uri != null ? this.clip.uri.hashCode() : 0;
    }

}

