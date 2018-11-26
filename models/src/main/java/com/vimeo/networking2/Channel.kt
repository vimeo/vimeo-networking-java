package com.vimeo.networking2

import com.vimeo.networking2.common.Followable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Entity

/**
 * Channel information.
 */
@JsonClass(generateAdapter = true)
data class Channel(

    /**
     * The categories to which this channel belongs as specified by the channel moderators.
     */
    @Json(name = "categories")
    val categories: List<Category>? = null,

    /**
     * The time in ISO 8601 format when the channel was created.
     */
    @Json(name = "created_time")
    val createdTime: String? = null,

    /**
     * A brief explanation of the channel's content.
     */
    @Json(name = "description")
    val description: String? = null,

    /**
     * The banner that appears by default at the top of the channel page.
     */
    @Json(name = "header")
    val header: PictureCollection? = null,

    /**
     * The URL to access the channel in a browser.
     */
    @Json(name = "link")
    val link: String? = null,

    /**
     * Metadata about the channel.
     */
    @Json(name = "metadata")
    override val metadata: Metadata<ChannelConnections, ChannelInteractions>? = null,

    /**
     * The time in ISO 8601 format when the album was last modified.
     */
    @Json(name = "modified_time")
    val modifiedTime: String? = null,

    /**
     * The display name that identifies the channel.
     */
    @Json(name = "name")
    val name: String? = null,

    /**
     * The active image for the channel; defaults to the thumbnail of the last video
     * added to the channel.
     */
    @Json(name = "pictures")
    val pictures: PictureCollection? = null,

    /**
     * The privacy settings of the channel.
     */
    @Json(name = "privacy")
    val privacy: Privacy? = null,

    /**
     * The channel resource key.
     */
    @Json(name = "resource_key")
    val resourceKey: String? = null,

    /**
     * An array of all tags assigned to this channel.
     */
    @Json(name = "tags")
    val tags: List<Tag>? = null,

    /**
     * The unique identifier to access the channel resource.
     */
    @Json(name = "uri")
    val uri: String? = null,

    /**
     * The Vimeo user who owns the channel.
     */
    @Json(name = "user")
    val user: User? = null

) : Followable, Entity {

    override val identifier: String? = resourceKey

}
