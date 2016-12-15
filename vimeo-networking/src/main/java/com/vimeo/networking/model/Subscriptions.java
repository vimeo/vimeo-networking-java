package com.vimeo.networking.model;

import com.vimeo.stag.GsonAdapterKey;

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
public class Subscriptions implements Serializable {

    private static final long serialVersionUID = 3088065484753327987L;

    public static final String KEY_COMMENT = "comment";
    public static final String KEY_CREDIT = "credit";
    public static final String KEY_LIKE = "like";
    public static final String KEY_REPLY = "reply";
    public static final String KEY_FOLLOW = "follow";
    public static final String KEY_VIDEO_AVAILABLE = "video_available";

    @GsonAdapterKey(KEY_COMMENT)
    boolean mComment;

    @GsonAdapterKey(KEY_CREDIT)
    boolean mCredit;

    @GsonAdapterKey(KEY_LIKE)
    boolean mLike;

    @GsonAdapterKey(KEY_REPLY)
    boolean mReply;

    @GsonAdapterKey(KEY_FOLLOW)
    boolean mFollow;

    @GsonAdapterKey(KEY_VIDEO_AVAILABLE)
    boolean mVideoAvailable;

    public boolean isComment() {
        return mComment;
    }

    public void setComment(boolean comment) {
        mComment = comment;
    }

    public boolean isCredit() {
        return mCredit;
    }

    public void setCredit(boolean credit) {
        mCredit = credit;
    }

    public boolean isLike() {
        return mLike;
    }

    public void setLike(boolean like) {
        mLike = like;
    }

    public boolean isReply() {
        return mReply;
    }

    public void setReply(boolean reply) {
        mReply = reply;
    }

    public boolean isFollow() {
        return mFollow;
    }

    public void setFollow(boolean follow) {
        mFollow = follow;
    }

    public boolean isVideoAvailable() {
        return mVideoAvailable;
    }

    public void setVideoAvailable(boolean videoAvailable) {
        mVideoAvailable = videoAvailable;
    }

    @NotNull
    public Map<String, Boolean> getMapFromSubscriptions() {
        Map<String, Boolean> map = new HashMap<>();

        map.put(KEY_COMMENT, mComment);
        map.put(KEY_CREDIT, mCredit);
        map.put(KEY_LIKE, mLike);
        map.put(KEY_REPLY, mReply);
        map.put(KEY_FOLLOW, mFollow);
        map.put(KEY_VIDEO_AVAILABLE, mVideoAvailable);

        return map;
    }
}
