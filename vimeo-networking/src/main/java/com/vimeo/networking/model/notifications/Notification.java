package com.vimeo.networking.model.notifications;

import com.google.gson.annotations.SerializedName;
import com.vimeo.networking.model.Comment;
import com.vimeo.networking.model.Credit;
import com.vimeo.networking.model.User;
import com.vimeo.networking.model.Video;
import com.vimeo.stag.UseStag;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.Date;

/**
 * A model representing activity that a user may be notified about.
 * Created by zetterstromk on 1/11/17.
 */
@SuppressWarnings("unused")
@UseStag
public class Notification implements Serializable {

    private static final long serialVersionUID = -68262442832775695L;

    @Nullable
    protected String uri;

    @Nullable
    @SerializedName("created_time")
    protected Date createdDate;

    @NotNull
    protected String type;

    @Nullable
    protected User user;

    @Nullable
    protected Comment comment;

    @Nullable
    @SerializedName("clip")
    protected Video video;

    @Nullable
    protected Credit credit;

    @SerializedName("new")
    protected boolean isNew;

    @SerializedName("seen")
    protected boolean isSeen;

    @Nullable
    public String getUri() {
        return uri;
    }

    @Nullable
    public Date getCreatedDate() {
        return createdDate;
    }

    @NotNull
    public NotificationType getNotificationType() {
        return NotificationType.notificationTypeFromString(type);
    }

    @Nullable
    public User getUser() {
        return user;
    }

    public void setUser(@Nullable User user) {
        this.user = user;
    }

    @Nullable
    public Comment getComment() {
        return comment;
    }

    public void setComment(@Nullable Comment comment) {
        this.comment = comment;
    }

    @Nullable
    public Video getVideo() {
        return video;
    }

    public void setVideo(@Nullable Video video) {
        this.video = video;
    }

    @Nullable
    public Credit getCredit() {
        return credit;
    }

    public void setCredit(@Nullable Credit credit) {
        this.credit = credit;
    }

    public boolean isNew() {
        return isNew;
    }

    public boolean isSeen() {
        return isSeen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Notification that = (Notification) o;

        if (uri != null ? !uri.equals(that.uri) : that.uri != null) {
            return false;
        }
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) {
            return false;
        }
        if (!type.equals(that.type)) {
            return false;
        }
        if (user != null ? !user.equals(that.user) : that.user != null) {
            return false;
        }
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) {
            return false;
        }
        if (video != null ? !video.equals(that.video) : that.video != null) {
            return false;
        }
        return credit != null ? credit.equals(that.credit) : that.credit == null;

    }

    @Override
    public int hashCode() {
        int result = uri != null ? uri.hashCode() : 0;
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + type.hashCode();
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (video != null ? video.hashCode() : 0);
        result = 31 * result + (credit != null ? credit.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Notification{" +
               "uri='" + uri + '\'' +
               ", createdDate=" + createdDate +
               ", type='" + type + '\'' +
               ", user=" + user +
               ", comment=" + comment +
               ", video=" + video +
               ", credit=" + credit +
               '}';
    }
}
