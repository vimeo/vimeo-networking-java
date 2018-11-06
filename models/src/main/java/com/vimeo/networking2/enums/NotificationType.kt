package com.vimeo.networking2.enums

/**
 * Different types of notifications.
 */
enum class NotificationType(override val value: String?) : StringValue {

    /**
     * Your Plus/Pro account is about to expire.
     */
    ACCOUNT_EXPIRATION_WARNING("account_expiration_warning"),

    /**
     * New comments on your video.
     */
    COMMENT("comment"),

    /**
     * You are added to the credits of a video.
     */
    CREDIT("credit"),

    /**
     * A user follows you.
     */
    FOLLOW("follow"),

    /**
     * Someone you follow uploaded a new item.
     */
    FOLLOWED_USER_VIDEO_AVAILABLE("followed_user_video_available"),

    /**
     * New likes on your videos.
     */
    LIKE("like"),

    /**
     * If you are "@" mentioned in a comment.
     */
    MENTION("mention"),

    /**
     * New reply to your comment.
     */
    REPLY("reply"),

    /**
     * Someone shares a video with you.
     */
    SHARE("share"),

    /**
     * You’re approaching your weekly storage limit.
     */
    STORAGE_WARNING("storage_warning"),

    /**
     * New upload transcode complete (new video is posted).
     */
    VIDEO_AVAILABLE("video_available"),

    /**
     * Unknown notification type.
     */
    UNKNOWN(null)
}
