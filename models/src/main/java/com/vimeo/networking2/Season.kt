@file:JvmName("SeasonUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Entity
import com.vimeo.networking2.enums.SeasonType
import com.vimeo.networking2.enums.asEnum

/**
 * This model represents a "season" of content. Seasons are logical groupings of videos set up by creators. All TVODs
 * will have a Season, even if it is a feature film - the season would contain the film in this case.
 *
 * @param description The description for this season.
 * @param metadata Season metadata.
 * @param name The descriptive name of the season.
 * @param position The position of the season relative to other seasons in the series.
 * @param rawType The type of season. See [Season.type].
 * @param resourceKey The unique identifier for this On Demand season.
 * @param uri The season container's relative URI.
 * @param user The creator of this On Demand page.
 */
@JsonClass(generateAdapter = true)
data class Season(

    @Json(name = "description")
    val description: String? = null,

    @Json(name = "metadata")
    val metadata: Metadata<SeasonConnections, SeasonInteractions>? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "position")
    val position: Int? = null,

    @Json(name = "type")
    val rawType: String? = null,

    @Json(name = "resource_key")
    val resourceKey: String? = null,

    @Json(name = "uri")
    val uri: String? = null,

    @Json(name = "user")
    val user: User? = null

) : Entity {
    override val identifier: String? = resourceKey
}

/**
 * @see Season.rawType
 * @see SeasonType
 */
val Season.type: SeasonType
    get() = rawType.asEnum(SeasonType.UNKNOWN)
