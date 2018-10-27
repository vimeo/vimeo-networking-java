package com.vimeo.networking2.enums

import com.squareup.moshi.Json

/**
 * All type of actions that can be taken by the user.
 */
enum class UserActivityType {

    @Json(name = "add_comment")
    ADD_COMMENT,

    @Json(name = "add_comment_blog")
    ADD_COMMENT_BLOG,

    @Json(name = "add_comment_client")
    ADD_COMMENT_CLIENT,

    @Json(name = "add_comment_forum")
    ADD_COMMENT_FORUM,

    @Json(name = "add_comment_ondemand")
    ADD_COMMENT_ONDEMAND,

    @Json(name = "add_help_comment_forum")
    ADD_HELP_COMMENT_FORUM,

    @Json(name = "add_portfolio")
    ADD_PORTFOLIO,

    @Json(name = "add_tags")
    ADD_TAGS,

    @Json(name = "album_clip")
    ALBUM_CLIP,

    @Json(name = "album_create")
    ALBUM_CREATE,

    @Json(name = "channel_clip")
    CHANNEL_CLIP,

    @Json(name = "channel_create")
    CHANNEL_CREATE,

    @Json(name = "channel_subscribe")
    CHANNEL_SUBSCRIBE,

    @Json(name = "follow_user")
    FOLLOW_USER,

    @Json(name = "group_clip")
    GROUP_CLIP,

    @Json(name = "group_clip_comment")
    GROUP_CLIP_COMMENT,

    @Json(name = "group_create")
    GROUP_CREATE,

    @Json(name = "group_join")
    GROUP_JOIN,

    @Json(name = "like")
    LIKE,

    @Json(name = "ondemand_publish")
    ONDEMAND_PUBLISH,

    @Json(name = "portfolio_clip")
    PORTFOLIO_CLIP,

    @Json(name = "tip_clip")
    TIP_CLIP,

    @Json(name = "upload")
    UPLOAD,

    UNKNOWN
}
