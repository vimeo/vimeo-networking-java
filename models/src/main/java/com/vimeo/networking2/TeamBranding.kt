package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Entity

/**
 * Stores user customization related information about a team.
 */
@JsonClass(generateAdapter = true)
data class TeamBranding(

    /**
     * The hexadecimal color code for the accent color of the team.
     */
    @Json(name = "accent_color")
    val acccentColor: String? = null,

    /**
     * The ID of the team.
     */
    @Json(name = "id")
    val id: Long? = null,

    /**
     * The URI of the team logo image.
     */
    @Json(name = "logo_uri")
    val logoUri: String? = null,

    /**
     * The name of the team.
     */
    @Json(name = "team_name")
    val name: String? = null,

    /**
     * The ID of the team owner.
     */
    @Json(name = "owner_id")
    val ownerId: Long? = null,

    /**
     * The active logo of the team.
     */
    @Json(name = "pictures")
    val pictures: PictureCollection? = null,

    /**
     * The team's URI.
     */
    @Json(name = "uri")
    val uri: String? = null

) : Entity {
    override val identifier: String? = id?.toString()
}
