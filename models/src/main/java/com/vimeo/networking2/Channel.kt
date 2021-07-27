package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Entity
import com.vimeo.networking2.common.Followable
import java.util.Date

/**
 * Channel information.
 *
 * @param categories The categories to which this channel belongs as specified by the channel moderators.
 * @param createdTime The time in ISO 8601 format when the channel was created.
 * @param description A brief explanation of the channel's content.
 * @param header The banner that appears by default at the top of the channel page.
 * @param link The URL to access the channel in a browser.
 * @param modifiedTime The time in ISO 8601 format when the album was last modified.
 * @param name The display name that identifies the channel.
 * @param pictures The active image for the channel; defaults to the thumbnail of the last video added to the channel.
 * @param privacy The privacy settings of the channel.
 * @param resourceKey The channel resource key.
 * @param tags An array of all tags assigned to this channel.
 * @param uri The unique identifier to access the channel resource.
 * @param user The Vimeo user who owns the channel.
 */
@JsonClass(generateAdapter = true)
data class Channel(

    @Json(name = "categories")
    val categories: List<Category>? = null,

    @Json(name = "created_time")
    val createdTime: Date? = null,

    @Json(name = "description")
    val description: String? = null,

    @Json(name = "header")
    val header: PictureCollection? = null,

    @Json(name = "link")
    val link: String? = null,

    @Json(name = "metadata")
    override val metadata: Metadata<ChannelConnections, ChannelInteractions>? = null,

    @Json(name = "modified_time")
    val modifiedTime: Date? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "pictures")
    val pictures: PictureCollection? = null,

    @Json(name = "privacy")
    val privacy: Privacy? = null,

    @Json(name = "resource_key")
    val resourceKey: String? = null,

    @Json(name = "tags")
    val tags: List<Tag>? = null,

    @Json(name = "uri")
    val uri: String? = null,

    @Json(name = "user")
    val user: User? = null

) : Followable, Entity {
    override val identifier: String? = resourceKey
}
