package com.vimeo.networking2.params

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.TeamRoleType

/**
 * Representation of a User being added to a team.
 */
@JsonClass(generateAdapter = true)
data class AddUserToTeam(
    /**
     * The email address for the user being added.
     */
    @Json(name = "email")
    val email: String,

    /**
     * The intended [TeamRoleType] for the user being added.
     */
    @Json(name = "permission_level")
    val permissionLevel: TeamRoleType,

    /**
     * If the user is being added as a [TeamRoleType.CONTRIBUTOR] or [TeamRoleType.VIEWER] a URI for the Folder
     * they can contribute or view also needs to be added.
     */
    @Json(name = "folder_uri")
    val folderUri: String? = null
)
