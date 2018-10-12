package com.vimeo.networking2
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

import java.util.*

/**
 * Folder data.
 */
@JsonClass(generateAdapter = true)
data class Folder(

    /**
     * The time in ISO 8601 format when the folder was created.
     */
    @Json(name = "created_time")
    val createdTime: String? = null,

    /**
     * The folder's metadata.
     */
    @Json(name = "metadata")
    val metadata: Metadata<AlbumConnections, AlbumInteractions>? = null,

    /**
     * The time in ISO 8601 format when the folder was last modified.
     */
    @Json(name = "modified_time")
    val modifiedTime: Date? = null,

    /**
     * The name of the folder.
     */
    @Json(name = "name")
    val name: String? = null,

    /**
     * The resource key string of the folder.
     */
    @Json(name = "resource_key")
    val resourceKey: String? = null,

    /**
     * The URI of the folder.
     */
    @Json(name = "uri")
    val uri: String? = null,

    /**
     * The owner of the folder.
     */
    @Json(name = "user")
    val user: User? = null
)
