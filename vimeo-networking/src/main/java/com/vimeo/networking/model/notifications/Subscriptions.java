package com.vimeo.networking.model.notifications;

import com.vimeo.stag.GsonAdapterKey;
import com.vimeo.stag.UseStag;
import com.vimeo.stag.UseStag.FieldOption;

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
@UseStag(FieldOption.SERIALIZED_NAME)
public class Subscriptions implements Serializable {

    private static final long serialVersionUID = 3088065484753327987L;


    @GsonAdapterKey(NotificationConstants.NOTIFICATION_COMMENT)
    boolean mComment;

    @GsonAdapterKey(NotificationConstants.NOTIFICATION_CREDIT)
    boolean mCredit;

    @GsonAdapterKey(NotificationConstants.NOTIFICATION_LIKE)
    boolean mLike;

    @GsonAdapterKey(NotificationConstants.NOTIFICATION_REPLY)
    boolean mReply;

    @GsonAdapterKey(NotificationConstants.NOTIFICATION_FOLLOW)
    boolean mFollow;

    @GsonAdapterKey(NotificationConstants.NOTIFICATION_VIDEO_AVAILABLE)
    boolean mVideoAvailable;

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
