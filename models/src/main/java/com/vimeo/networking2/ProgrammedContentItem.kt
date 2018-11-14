package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.ProgrammedContentItemType

/**
 * Cinema data.
 */
@JsonClass(generateAdapter = true)
data class ProgrammedContentItem(

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
    val videoList: List<Video>? = null,

    /**
     * ProgrammedContentItem metadata.
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
    val type: ProgrammedContentItemType? = null,

    /**
     * The programmed cinema items' canonical relative URI.
     */
    @Json(name = "uri")
    val uri: String? = null

)
