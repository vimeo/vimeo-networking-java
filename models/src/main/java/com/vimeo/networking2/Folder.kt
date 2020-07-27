@file:JvmName("FolderUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.Date

/**
 * A folder that acts as a container for a list of [Videos][Video] that can be fetched via
 * a uri defined in [Metadata.connections].
 */
@JsonClass(generateAdapter = true)
data class Folder(

    /**
     * The time in ISO 8601 format when the folder was created.
     */
    @Json(name = "created_time")
    val createdDate: Date? = null,

    /**
     * The time in ISO 8601 format when a user last performed an action on the folder.
     */
    @Json(name = "last_user_action_event_date")
    val lastUserActionEventDate: Date? = null,

    /**
     * The folder's metadata.
     */
    @Json(name = "metadata")
    val metadata: Metadata<FolderConnections, Void>? = null,

    /**
     *  The time in ISO 8601 format when the folder was last modified.
     */
    @Json(name = "modified_time")
    val lastModifiedDate: Date? = null,

    /**
     *  The name of the folder.
     */
    @Json(name = "name")
    val name: String? = null,

    /**
     *  The [FolderPrivacy] that defines the public visibility of the folder.
     */
    @Json(name = "privacy")
    val privacy: FolderPrivacy? = null,

    /**
     * The resource key string of the folder.
     */
    @Json(name = "resource_key")
    val resourceKey: String? = null,

    /**
     * The folder's canonical relative URI.
     */
    @Json(name = "uri")
    val uri: String? = null,

    /**
     * The folder's owner.
     */
    @Json(name = "user")
    val user: User? = null
)
