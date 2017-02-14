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
    protected boolean comment;

    @SerializedName(NotificationConstants.NOTIFICATION_CREDIT)
    protected boolean credit;

    @SerializedName(NotificationConstants.NOTIFICATION_LIKE)
    protected boolean like;

    @SerializedName(NotificationConstants.NOTIFICATION_REPLY)
    protected boolean reply;

    @SerializedName(NotificationConstants.NOTIFICATION_FOLLOW)
    protected boolean follow;

    @SerializedName(NotificationConstants.NOTIFICATION_VIDEO_AVAILABLE)
    protected boolean videoAvailable;

    public boolean isReceivingComment() {
        return comment;
    }

    public void receiveComment(boolean receive) {
        comment = receive;
    }

    public boolean isReceivingCredit() {
        return credit;
    }

    public void receiveCredit(boolean receive) {
        credit = receive;
    }

    public boolean isReceivingLike() {
        return like;
    }

    public void receiveLike(boolean receive) {
        like = receive;
    }

    public boolean isReceivingReply() {
        return reply;
    }

    public void receiveReply(boolean receive) {
        reply = receive;
    }

    public boolean isReceivingFollow() {
        return follow;
    }

    public void receiveFollow(boolean receive) {
        follow = receive;
    }

    public boolean isReceivingVideoAvailable() {
        return videoAvailable;
    }

    public void receiveVideoAvailable(boolean receive) {
        videoAvailable = receive;
    }

    @NotNull
    public Map<String, Boolean> getMapFromSubscriptions() {
        Map<String, Boolean> map = new HashMap<>();

        map.put(NotificationConstants.NOTIFICATION_COMMENT, comment);
        map.put(NotificationConstants.NOTIFICATION_CREDIT, credit);
        map.put(NotificationConstants.NOTIFICATION_LIKE, like);
        map.put(NotificationConstants.NOTIFICATION_REPLY, reply);
        map.put(NotificationConstants.NOTIFICATION_FOLLOW, follow);
        map.put(NotificationConstants.NOTIFICATION_VIDEO_AVAILABLE, videoAvailable);

        return map;
    }
}
