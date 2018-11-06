package com.vimeo.networking2.enums

/**
 * All type of actions that can be taken by the user.
 */
enum class UserActivityType(override val value: String?) : StringValue {

    ADD_COMMENT("add_comment"),

    ADD_COMMENT_BLOG("add_comment_blog"),

    ADD_COMMENT_CLIENT("add_comment_client"),

    ADD_COMMENT_FORUM("add_comment_forum"),

    ADD_COMMENT_ONDEMAND("add_comment_ondemand"),

    ADD_HELP_COMMENT_FORUM("add_help_comment_forum"),

    ADD_PORTFOLIO("add_portfolio"),

    ADD_TAGS("add_tags"),

    ALBUM_CLIP("album_clip"),

    ALBUM_CREATE("album_create"),

    CHANNEL_CLIP("channel_clip"),

    CHANNEL_CREATE("channel_create"),

    CHANNEL_SUBSCRIBE("channel_subscribe"),

    FOLLOW_USER("follow_user"),

    GROUP_CLIP("group_clip"),

    GROUP_CLIP_COMMENT("group_clip_comment"),

    GROUP_CREATE("group_create"),

    GROUP_JOIN("group_join"),

    LIKE("like"),

    ONDEMAND_PUBLISH("ondemand_publish"),

    PORTFOLIO_CLIP("portfolio_clip"),

    TIP_CLIP("tip_clip"),

    UPLOAD("upload"),

    UNKNOWN(null)
}
