package com.vimeo.networking2

/**
 * Channel information.
 */
data class Channel(

    /**
     * The categories to which this channel belongs as specified by the channel moderators.
     */
    val categories: List<Category>? = null,

    /**
     * The time in ISO 8601 format when the channel was created.
     */
    val createdTime: String? = null,

    /**
     * A brief explanation of the channel's content.
     */
    val description: String? = null,

    /**
     * The banner that appears by default at the top of the channel page.
     */
    val header: PictureCollection? = null,

    /**
     * The URL to access the channel in a browser.
     */
    val link: String? = null,

    /**
     * Metadata about the channel.
     */
    val metadata: Metadata<ChannelConnections, ChannelInteractions>? = null,

    /**
     * The time in ISO 8601 format when the album was last modified.
     */
    val modifiedTime: String? = null,

    /**
     * The display name that identifies the channel.
     */
    val name: String? = null,

    /**
     * The active image for the channel; defaults to the thumbnail of the last video
     * added to the channel.
     */
    val pictures: PictureCollection? = null,

    /**
     * The privacy settings of the channel.
     */
    val privacy: Privacy? = null,

    /**
     * The channel resource key.
     */
    val resourceKey: String? = null,

    /**
     * An array of all tags assigned to this channel.
     */
    val tags: List<Tag>? = null,

    /**
     * The unique identifier to access the channel resource.
     */
    val uri: String? = null,

    /**
     * The Vimeo user who owns the channel.
     */
    val user: User? = null

)
