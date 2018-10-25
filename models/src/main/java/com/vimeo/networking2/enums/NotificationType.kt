package com.vimeo.networking2.enums

import com.squareup.moshi.Json

/**
 * Different types of notifications.
 */
enum class NotificationType {

    /**
     * Your Plus/Pro account is about to expire.
     */
    @Json(name = "account_expiration_warning")
    ACCOUNT_EXPIRATION_WARNING,

    /**
     * New comments on your video.
     */
    @Json(name = "comment")
    COMMENT,

    /**
     * You are added to the credits of a video.
     */
    @Json (name = "credit")
    CREDIT,

    /**
     * A user follows you.
     */
    @Json(name = "follow")
    FOLLOW,

    /**
     * Someone you follow uploaded a new item.
     */
    @Json(name = "followed_user_video_available")
    FOLLOWED_USER_VIDEO_AVAILABLE,

    /**
     * New likes on your videos.
     */
    @Json(name = "like")
    LIKE,

    /**
     * If you are "@" mentioned in a comment.
     */
    @Json(name = "mention")
    MENTION,

    /**
     * New reply to your comment.
     */
    @Json(name = "reply")
    REPLY,

    /**
     * Someone shares a video with you.
     */
    @Json(name = "share")
    SHARE,

    /**
     * You’re approaching your weekly storage limit.
     */
    @Json(name = "storage_warning")
    STORAGE_WARNING,

    /**
     * New upload transcode complete (new video is posted).
     */
    @Json(name = "video_available")
    VIDEO_AVAILABLE,

    /**
     * Unknown notification type.
     */
    UNKNOWN
}
