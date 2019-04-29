package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * All of [TvodItem]'s connections.
 */
@JsonClass(generateAdapter = true)
data class TvodItemConnections(

    /**
     * Information about the comments associated with this page.
     */
    @Json(name = "comment")
    val comments: Connection? = null,

    /**
     * Information about the genres associated with this page.
     */
    @Json(name = "genres")
    val genres: Connection? = null,

    /**
     * Information about the likes associated with this page.
     */
    @Json(name = "likes")
    val likes: Connection? = null,

    /**
     * Information about the pictures associated with this page.
     */
    @Json(name = "pictures")
    val pictures: Connection? = null,

    /**
     * Information about the seasons associated with this page.
     */
    @Json(name = "seasons")
    val seasons: Connection? = null,

    /**
     * Information about the videos associated with this page.
     */
    @Json(name = "videos")
    val videos: VideosTvodItemConnection? = null

)
