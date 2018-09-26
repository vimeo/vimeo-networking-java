package com.vimeo.networking2

data class Channel(

    /**
     * The categories to which this channel belongs as specified by the channel moderators.
     */
    val categories: List<Category>,

    /**
     * The time in ISO 8601 format when the channel was created.
     */
    val createdTime: String,

    /**
     * A brief explanation of the channel's content.
     */
    val description: String,

    /**
     * The banner that appears by default at the top of the channel page.
     */
    val header: PictureCollection,

    /**
     * The URL to access the channel in a browser.
     */
    val link: String,

    /**
     * Metadata about the channel.
     */
    val metadata: Metadata,

    /**
     * The time in ISO 8601 format when the album was last modified.
     */
    val modifiedTime: String,

    /**
     * The display name that identifies the channel.
     */
    val name: String,

    /**
     * The active image for the channel; defaults to the thumbnail of the last video
     * added to the channel.
     */
    val pictures: PictureCollection,

    /**
     * The privacy settings of the channel.
     */
    val privacy: Privacy,

    /**
     * The channel resource key.
     */
    val resourceKey: String,

    /**
     * An array of all tags assigned to this channel.
     */
    val tags: List<Tag>? = null,

    /**
     * The unique identifier to access the channel resource.
     */
    val uri: String

)
