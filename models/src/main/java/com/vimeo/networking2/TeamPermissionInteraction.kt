package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Represents any interactions for a [TeamPermission].
 *
 * @param edit an interaction denoting the ability to edit the [TeamPermission]
 * @param remove an interaction denoting the ability to remove the [TeamPermission]
 */
@JsonClass(generateAdapter = true)
data class TeamPermissionInteraction(
    @Json(name = "edit")
    val edit: BasicInteraction? = null,

    @Json(name = "remove")
    val remove: BasicInteraction? = null
)
