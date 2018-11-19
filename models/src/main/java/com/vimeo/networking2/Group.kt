package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Entity
import com.vimeo.networking2.common.Followable
import java.util.*

/**
 * Group DTO.
 */
@JsonClass(generateAdapter = true)
data class Group(

    /**
     * The time in ISO 8601 format when the group was created.
     */
    @Json(name = "created_time")
    val createdTime: Date? = null,

    /**
     * The group's description.
     */
    @Json(name = "description")
    val description: String? = null,

    /**
     * The link to the group.
     */
    @Json(name = "link")
    val link: String? = null,

    /**
     * Metadata about the group.
     */
    @Json(name = "metadata")
    override val metadata: Metadata<GroupConnections, GroupInteractions>? = null,

    /**
     * The time in ISO 8601 format when the group was last modified.
     */
    @Json(name = "modified_time")
    val modifiedTime: Date? = null,

    /**
     * The group's display name.
     */
    @Json(name = "name")
    val name: String? = null,

    /**
     * The active picture for this group.
     */
    @Json(name = "pictures")
    val pictures: PictureCollection? = null,

    /**
     *The group's privacy settings.
     */
    @Json(name = "privacy")
    val privacy: GroupPrivacy? = null,

    /**
     * The resource key of the group.
     */
    @Json(name = "resource_key")
    val resourceKey: String? = null,

    /**
     * The canonical relative URI of this group.
     */
    @Json(name = "uri")
    val uri: String? = null,

    /**
     * The owner of the group.
     */
    @Json(name = "user")
    val user: User? = null

): Followable, Entity {

    override val identifier: String? = resourceKey

}
