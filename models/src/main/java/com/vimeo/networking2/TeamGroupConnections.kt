package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class TeamGroupConnections(
    @Json(name = "users")
    val users: BasicConnection
)