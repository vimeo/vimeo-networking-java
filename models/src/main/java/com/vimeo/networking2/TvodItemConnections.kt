package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * All of [TvodItem]'s connections.
 *
 * @param comments Information about the comments associated with this page.
 * @param genres Information about the genres associated with this page.
 * @param likes Information about the likes associated with this page.
 * @param pictures Information about the pictures associated with this page.
 * @param seasons Information about the seasons associated with this page.
 * @param videos Information about the videos associated with this page.
 */
@JsonClass(generateAdapter = true)
data class TvodItemConnections(

    @Json(name = "comment")
    val comments: BasicConnection? = null,

    @Json(name = "genres")
    val genres: BasicConnection? = null,

    @Json(name = "likes")
    val likes: BasicConnection? = null,

    @Json(name = "pictures")
    val pictures: BasicConnection? = null,

    @Json(name = "seasons")
    val seasons: BasicConnection? = null,

    @Json(name = "videos")
    val videos: VideosTvodItemConnection? = null
)
