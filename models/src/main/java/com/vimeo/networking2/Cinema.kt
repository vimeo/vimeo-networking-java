package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.CinemaType
import com.vimeo.networking2.enums.CinemaType.UNKNOWN

/**
 * Cinema data.
 */
@JsonClass(generateAdapter = true)
data class Cinema(

    /**
     * The category associated with this programmed cinema item.
     */
    @Json(name = "category")
    val category: Category? = null,

    /**
     * The channel associated with this programmed cinema item.
     */
    @Json(name = "channel")
    val channel: Channel? = null,

    /**
     * Content for the programmed cinema item.
     */
    @Json(name = "content")
    val content: List<Video>? = null,

    /**
     * Cinema metadata.
     */
    @Json(name = "metadata")
    val metadata: MetadataConnections<CinemaConnections>? = null,

    /**
     * The name of the programmed cinema item.
     */
    @Json(name = "name")
    val name: String? = null,

    /**
     * The type of programmed cinema item.
     */
    @Json(name = "type")
    val type: CinemaType = UNKNOWN,

    /**
     * The programmed cinema items' canonical relative URI.
     */
    @Json(name = "uri")
    val uri: String? = null

)
