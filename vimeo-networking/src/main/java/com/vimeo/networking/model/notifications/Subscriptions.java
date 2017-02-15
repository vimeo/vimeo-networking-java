package com.vimeo.networking.model.notifications;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * A model that holds the type of push subscriptions a user has. This class
 * has both getters and setters since a user can update their subscriptions
 * using a Patch.
 * <p>
 * Created by zetterstromk on 12/15/16.
 */
@SuppressWarnings("unused")
@UseStag
public class Subscriptions implements Serializable {

    private static final long serialVersionUID = 3088065484753327987L;


    @SerializedName(NotificationConstants.NOTIFICATION_COMMENT)
    protected boolean mComment;

    @SerializedName(NotificationConstants.NOTIFICATION_CREDIT)
    protected boolean mCredit;

    @SerializedName(NotificationConstants.NOTIFICATION_LIKE)
    protected boolean mLike;

    @SerializedName(NotificationConstants.NOTIFICATION_REPLY)
    protected boolean mReply;

    @SerializedName(NotificationConstants.NOTIFICATION_FOLLOW)
    protected boolean mFollow;

    @SerializedName(NotificationConstants.NOTIFICATION_VIDEO_AVAILABLE)
    protected boolean mVideoAvailable;

    public boolean isReceivingComment() {
        return mComment;
    }

    public void receiveComment(boolean receive) {
        mComment = receive;
    }

    public boolean isReceivingCredit() {
        return mCredit;
    }

    public void receiveCredit(boolean receive) {
        mCredit = receive;
    }

    public boolean isReceivingLike() {
        return mLike;
    }

    public void receiveLike(boolean receive) {
        mLike = receive;
    }

    public boolean isReceivingReply() {
        return mReply;
    }

    public void receiveReply(boolean receive) {
        mReply = receive;
    }

    public boolean isReceivingFollow() {
        return mFollow;
    }

    public void receiveFollow(boolean receive) {
        mFollow = receive;
    }

    public boolean isReceivingVideoAvailable() {
        return mVideoAvailable;
    }

    public void receiveVideoAvailable(boolean receive) {
        mVideoAvailable = receive;
    }

    @NotNull
    public Map<String, Boolean> getMapFromSubscriptions() {
        Map<String, Boolean> map = new HashMap<>();

        map.put(NotificationConstants.NOTIFICATION_COMMENT, mComment);
        map.put(NotificationConstants.NOTIFICATION_CREDIT, mCredit);
        map.put(NotificationConstants.NOTIFICATION_LIKE, mLike);
        map.put(NotificationConstants.NOTIFICATION_REPLY, mReply);
        map.put(NotificationConstants.NOTIFICATION_FOLLOW, mFollow);
        map.put(NotificationConstants.NOTIFICATION_VIDEO_AVAILABLE, mVideoAvailable);

        return map;
    }
}
