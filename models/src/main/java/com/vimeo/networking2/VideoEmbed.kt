package com.vimeo.networking2

data class VideoEmbed(

    /**
     * A collection of the video's badges.
     */
    val badges: VideoBadges?,

    /**
     * A collection of information about the buttons that appear on the
     * interface of the embeddable player.
     */
    val buttons: EmbedButtons?,

    /**
     * The primary player color, which controls the color of the progress bar, buttons,
     * and more.
     */
    val color: String?,

    /**
     * HTML code for embedding this video on a web page.
     */
    val html: String?,

    /**
     * Whether the playbar appears in the embeddable player for this video.
     */
    val playBar: Boolean?,

    /**
     * Whether the speed controls appear in the embeddable player for this video.
     */
    val speed: Boolean?,

    /**
     * A collection of information relating to the embeddable player's title bar.
     */
    val title: EmbedTitle?,

    /**
     * The URI of the embed preset.
     */
    val uri: String?,

    /**
     * Whether the volume controls appear in the embeddable player for this video.
     */
    val volume: Boolean?

)
