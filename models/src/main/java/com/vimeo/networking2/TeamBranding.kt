package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Entity

/**
 * Stores user customization related information about a team.
 *
 * @param accentColor The hexadecimal color code for the accent color of the team.
 * @param id The ID of the team.
 * @param logoUri The URI of the team logo image.
 * @param name The name of the team.
 * @param ownerId The ID of the team owner.
 * @param pictures The active logo of the team.
 * @param uri The team's URI.
 */
@JsonClass(generateAdapter = true)
data class TeamBranding(

    @Json(name = "accent_color")
    val accentColor: String? = null,

    @Json(name = "id")
    val id: Long? = null,

    @Json(name = "logo_uri")
    val logoUri: String? = null,

    @Json(name = "team_name")
    val name: String? = null,

    @Json(name = "owner_id")
    val ownerId: Long? = null,

    @Json(name = "pictures")
    val pictures: PictureCollection? = null,

    @Json(name = "uri")
    val uri: String? = null

) : Entity {
    override val identifier: String? = id?.toString()
}
