package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by nicholas.doglio on 2020-01-07.
 */
@JsonClass(generateAdapter = true)
data class PublishConstraints(
        @Json(name = "facebook")
        val facebook: PlatformConstraint? = null,
        @Json(name = "linkedin")
        val linkedin: PlatformConstraint? = null,
        @Json(name = "youtube")
        val youtube: PlatformConstraint? = null,
        @Json(name = "twitter")
        val twitter: PlatformConstraint? = null
)
