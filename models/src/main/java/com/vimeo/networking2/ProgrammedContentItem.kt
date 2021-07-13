@file:JvmName("ProgrammedContentItemUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.ProgrammedContentItemType
import com.vimeo.networking2.enums.asEnum

/**
 * Cinema data.
 *
 * @param category The category associated with this programmed cinema item.
 * @param channel The channel associated with this programmed cinema item.
 * @param metadata ProgrammedContentItem metadata.
 * @param name The name of the programmed cinema item.
 * @param rawType The type of programmed cinema item. See [ProgrammedContentItem.type].
 * @param uri The programmed cinema items' canonical relative URI.
 * @param videoList Content for the programmed cinema item.
 */
@JsonClass(generateAdapter = true)
data class ProgrammedContentItem(

    @Json(name = "category")
    val category: Category? = null,

    @Json(name = "channel")
    val channel: Channel? = null,

    @Json(name = "metadata")
    val metadata: MetadataConnections<CinemaConnections>? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "type")
    val rawType: String? = null,

    @Json(name = "uri")
    val uri: String? = null,

    @Json(name = "content")
    val videoList: List<Video>? = null
)

/**
 * @see ProgrammedContentItem.rawType
 * @see ProgrammedContentItem
 */
val ProgrammedContentItem.type: ProgrammedContentItemType
    get() = rawType.asEnum(ProgrammedContentItemType.UNKNOWN)
