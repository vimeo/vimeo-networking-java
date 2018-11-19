package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Entity
import com.vimeo.networking2.enums.SeasonType
import com.vimeo.networking2.enums.asEnum

/**
 * This model represents a "season" of content. Seasons are logical groupings
 * of videos set up by creators. All TVODs will have a Season, even if it is
 * a feature film - the season would contain the film in this case.
 */
@JsonClass(generateAdapter = true)
data class Season(

    /**
     * The description for this season.
     */
    @Json(name = "description")
    val description: String? = null,

    /**
     * Season metadata.
     */
    @Json(name = "metadata")
    val metadata: Metadata<SeasonConnections, SeasonInteractions>? = null,

    /**
     * The descriptive name of the season.
     */
    @Json(name = "name")
    val name: String? = null,

    /**
     * The position of the season relative to other seasons in the series.
     */
    @Json(name = "position")
    val position: Int? = null,

    /**
     * The unique identifier for this On Demand season.
     */
    @Json(name = "resource_key")
    val resourceKey: String? = null,

    /**
     * The type of season.
     * @see Season.type
     */
    @Json(name = "type")
    val rawType: String? = null,

    /**
     * The season container''s relative URI.
     */
    @Json(name = "uri")
    val uri: String? = null,

    /**
     * The creator of this On Demand page.
     */
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
