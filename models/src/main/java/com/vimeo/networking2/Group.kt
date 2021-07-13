package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Entity
import com.vimeo.networking2.common.Followable
import java.util.Date

/**
 * Group DTO.
 *
 * @param createdTime The time in ISO 8601 format when the group was created.
 * @param description The group's description.
 * @param link The link to the group.
 * @param modifiedTime The time in ISO 8601 format when the group was last modified.
 * @param name The group's display name.
 * @param pictures The active picture for this group.
 * @param privacy The group's privacy settings.
 * @param resourceKey The resource key of the group.
 * @param uri The canonical relative URI of this group.
 * @param user The owner of the group.
 */
@JsonClass(generateAdapter = true)
data class Group(

    @Json(name = "created_time")
    val createdTime: Date? = null,

    @Json(name = "description")
    val description: String? = null,

    @Json(name = "link")
    val link: String? = null,

    @Json(name = "metadata")
    override val metadata: Metadata<GroupConnections, GroupInteractions>? = null,

    @Json(name = "modified_time")
    val modifiedTime: Date? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "pictures")
    val pictures: PictureCollection? = null,

    @Json(name = "privacy")
    val privacy: GroupPrivacy? = null,

    @Json(name = "resource_key")
    val resourceKey: String? = null,

    @Json(name = "uri")
    val uri: String? = null,

    @Json(name = "user")
    val user: User? = null

) : Followable, Entity {
    override val identifier: String? = resourceKey
}
