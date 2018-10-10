package com.vimeo.networking2

/**
 * Embed data.
 */
data class EmbedTitle(

    /**
     * How the embeddable player handles the video title.
     */
    val name: String? = null,

    /**
     * How the embeddable player handles the video owner's information.
     */
    val owner: String? = null,

    /**
     * How the embeddable player handles the video owner's portrait.
     */
    val portrait: String? = null

)
