package com.vimeo.networking2

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * All connections for a category.
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class CategoryConnections(

    /**
     * Information about the channels related to this category.
     */
    @Json(name = "channels")
    val channels: Connection? = null,

    /**
     * Information about the groups related to this category.
     */
    @Json(name = "groups")
    val groups: Connection? = null,

    /**
     * Information about the users related to this category.
     */
    @Json(name = "users")
    val users: Connection? = null,

    /**
     * Information about the videos related to this category.
     */
    @Json(name = "videos")
    val videos: Connection? = null

): Parcelable
