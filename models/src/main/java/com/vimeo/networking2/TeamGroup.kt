package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.Date

@JsonClass(generateAdapter = true)
data class TeamGroup(
    @Json(name = "total")
    val uri: String? = null,

    @Json(name = "owner_id")
    val ownerId: String? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "created_on")
    val createdOn: Date? = null,

    @Json(name = "modified_on")
    val modifiedOn: Date? = null,

    @Json(name = "metadata")
    val metadata: MetadataConnections<TeamGroupConnections>
)