package com.vimeo.networking2.enums

enum class NotificationType {

    /**
     * Your Plus/Pro account is about to expire.
     */
    ACCOUNT_EXPIRATION_WARNING,

    /**
     * New comments on your video.
     */
    COMMENT,

    /**
     * You are added to the credits of a video.
     */
    CREDIT,

    /**
     * A user follows you.
     */
    FOLLOW,

    /**
     * Someone you follow uploaded a new item.
     */
    FOLLOWED_USER_VIDEO_AVAILABLE,

    /**
     * New likes on your videos.
     */
    LIKE,

    /**
     * If you are "@" mentioned in a comment.
     */
    MENTION,

    /**
     * New reply to your comment.
     */
    REPLY,

    /**
     * Someone shares a video with you.
     */
    SHARE,

    /**
     * You’re approaching your weekly storage limit.
     */
    STORAGE_WARNING,

    /**
     * New upload transcode complete (new video is posted).
     */
    VIDEO_AVAILABLE
}
