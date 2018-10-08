package com.vimeo.networking2

data class EmbedButtons(

    /**
     * Whether the Embed button appears in the embeddable player for this video.
     */
    val embed: Boolean? = null,

    /**
     * Whether the Fullscreen button appears in the embeddable player for this video.
     */
    val fullscreen: Boolean? = null,

    /**
     * Whether the HD button appears in the embeddable player for this video.
     */
    val hd: Boolean? = null,

    /**
     * Whether the Like button appears in the embeddable player for this video.
     */
    val like: Boolean? = null,

    /**
     * Whether the Scaling button appears in the embeddable player for this video.
     */
    val scaling: Boolean? = null,

    /**
     * Whether the Share button appears in the embeddable player for this video.
     */
    val share: Boolean? = null,

    /**
     * Whether the Watch Later button appears in the embeddable player for this video.
     */
    val watchlater: Boolean? = null

)
