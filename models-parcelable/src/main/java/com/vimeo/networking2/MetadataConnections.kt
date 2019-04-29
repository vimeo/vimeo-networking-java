package com.vimeo.networking2

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * Metadata with only connections.
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class MetadataConnections<Connections_T : Parcelable>(

    /**
     * Connections for [Connections_T].
     */
    @Json(name = "connections")
    val connections: Connections_T? = null

): Parcelable
