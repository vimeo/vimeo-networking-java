package com.vimeo.networking2

data class EmbedTitle(

    /**
     * How the embeddable player handles the video title.
     */
    val name: String?,

    /**
     * How the embeddable player handles the video owner's information.
     */
    val owner: String?,

    /**
     * How the embeddable player handles the video owner's portrait.
     */
    val portrait: String?

)
