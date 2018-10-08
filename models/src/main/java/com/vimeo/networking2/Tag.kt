package com.vimeo.networking2

data class Tag(

    /**
     * The normalized canonical tag name.
     */
    val canonical: String? = null,

    /**
     * AlbumMetadata about the group.
     */
    val metadata: AlbumMetadata? = null,

    /**
     * The tag value.
     */
    val name: String? = null,

    /**
     * The tag's resource key string.
     */
    val resourceKey: String? = null,

    /**
     * The canonical relative URI of the tag.
     */
    val uri: String? = null

)
