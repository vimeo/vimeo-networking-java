package com.vimeo.networking2.enums

import com.squareup.moshi.Json

/**
 * All actions that can be taken on a video.
 */
enum class VideoActionType {

    @Json(name = "Added to")
    ADDED_TO,

    @Json(name = "Appearance by")
    APPEARANCE_BY,

    @Json(name = "Liked by")
    LIKED_BY,

    @Json(name = "Uploaded by")
    UPLOADED_BY,

    UNKNOWN
}
