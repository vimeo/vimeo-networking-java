package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;
import com.vimeo.networking.Vimeo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zetterstromk on 7/31/15.
 */
public class Comment implements Serializable {

    private static final long serialVersionUID = -7716027694845877155L;

    public enum CommentType {
        @SerializedName("video")
        VIDEO
        //TODO get the other comment types and put them here [KZ]
    }

    public String uri;
    public CommentType type;
    public Date createdOn;
    public String text;
    public User user;
    public Metadata metadata;

    public int replyCount() {
        if ((metadata != null) && (metadata.connections != null) && (metadata.connections.replies != null)) {
            return metadata.connections.replies.total;
        }
        return 0;
    }

    public boolean canReply() {
        if ((metadata != null) && (metadata.connections != null) &&
            (metadata.connections.replies != null) &&
            (metadata.connections.replies.options != null)) {
            return metadata.connections.replies.options.contains(Vimeo.OPTIONS_POST);
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Comment that = (Comment) o;

        return ((this.uri != null && that.uri != null) ? this.uri.equals(that.uri) : false);
    }

    @Override
    public int hashCode() {
        return this.uri != null ? this.uri.hashCode() : 0;
    }

}
