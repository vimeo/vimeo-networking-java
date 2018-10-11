package com.vimeo.networking2

/**
 * Embed data for a album.
 */
data class AlbumEmbed(

    /**
     * The responsive HTML code to embed the album on a website. This is present only
     * for non free tier accounts.
     */
    val html: String? = null
)
