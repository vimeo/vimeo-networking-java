@file:JvmName("CinemaUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.ProgrammedContentItemType
import com.vimeo.networking2.enums.asEnum

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
     * @see ProgrammedContentItem.type
     */
    @Json(name = "type")
    val rawType: String? = null,

    /**
     * The programmed cinema items' canonical relative URI.
     */
    @Json(name = "uri")
    val uri: String? = null

)

/**
 * @see ProgrammedContentItem.rawType
 * @see ProgrammedContentItem
 */
val ProgrammedContentItem.type: ProgrammedContentItemType
    get() = rawType.asEnum(ProgrammedContentItemType.UNKNOWN)
