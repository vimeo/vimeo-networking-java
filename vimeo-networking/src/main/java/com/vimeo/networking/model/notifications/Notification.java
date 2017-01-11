package com.vimeo.networking.model.notifications;

import com.vimeo.networking.model.Comment;
import com.vimeo.networking.model.Credit;
import com.vimeo.networking.model.User;
import com.vimeo.networking.model.Video;
import com.vimeo.stag.GsonAdapterKey;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zetterstromk on 1/11/17.
 */
public class Notification implements Serializable {

    private static final long serialVersionUID = -68262442832775695L;

    @Nullable
    @GsonAdapterKey("uri")
    String mUri;

    @Nullable
    @GsonAdapterKey("created_time")
    Date mCreatedDate;

    @NotNull
    @GsonAdapterKey("type")
    String mType;

    @Nullable
    @GsonAdapterKey("user")
    User mUser;

    @Nullable
    @GsonAdapterKey("comment")
    Comment mComment;

    @Nullable
    @GsonAdapterKey("clip")
    Video mVideo;

    @Nullable
    @GsonAdapterKey("credit")
    Credit mCredit;

    @Nullable
    public String getUri() {
        return mUri;
    }

    @Nullable
    public Date getCreatedDate() {
        return mCreatedDate;
    }

    @NotNull
    public NotificationType getNotificationType() {
        return NotificationType.notificationTypeFromString(mType);
    }

    @Nullable
    public User getUser() {
        return mUser;
    }

    @Nullable
    public Comment getComment() {
        return mComment;
    }

    @Nullable
    public Video getVideo() {
        return mVideo;
    }

    @Nullable
    public Credit getCredit() {
        return mCredit;
    }
}
