package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Connection to get information about a video's versions.
 *
 * Requires [CapabilitiesType.CAPABILITY_VIDEO_VERSIONS].
 */
@JsonClass(generateAdapter = true)
data class VideoVersionsConnection(

    /**
     * The URI of the current version of the video.
     */
    @Json(name = "current_uri")
    val currentUri: String? = null,

    /**
     * The API URI that resolves to the connection data.
     */
    @Json(name = "latest_incomplete_version")
    val latestIncompleteVersion: String? = null,

    /**
     * An array of HTTP methods permitted on this URI.
     */
    @Json(name = "options")
    val options: List<String>? = null,

    /**
     * The total number of versions on this connection.
     */
    @Json(name = "total")
    val total: Int? = null,

    /**
     * The API URI that resolves to the connection data.
     */
    @Json(name = "uri")
    val uri: String? = null

)
