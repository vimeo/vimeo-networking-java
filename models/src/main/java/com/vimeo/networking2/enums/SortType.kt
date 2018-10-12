package com.vimeo.networking2.enums

import com.squareup.moshi.Json

enum class SortType {

    @Json(name = "added first")
    ADDED_FIRST,

    @Json(name = "added last")
    ADDED_LAST,

    @Json(name = "alphabetical")
    ALPHABETICAL,

    @Json(name = "arranged")
    ARRANGED,

    @Json(name = "comments")
    COMMENTS,

    @Json(name = "likes")
    LIKES,

    @Json(name = "newest")
    NEWEST,

    @Json(name = "oldest")
    OLDEST,

    @Json(name = "plays")
    PLAYS
}
