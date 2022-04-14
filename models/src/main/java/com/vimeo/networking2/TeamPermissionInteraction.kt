package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class TeamPermissionInteraction(
    @Json(name = "edit")
    val edit: BasicInteraction? = null,

    @Json(name = "remove")
    val remove: BasicInteraction? = null
)