package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Video embed data.
 */
@JsonClass(generateAdapter = true)
data class VideoEmbed(

    /**
     * A collection of the video's badges.
     */
    @Json(name = "badges")
    val badges: VideoBadges? = null,

    /**
     * A collection of information about the buttons that appear on the
     * interface of the embeddable player.
     */
    @Json(name = "buttons")
    val buttons: EmbedButtons? = null,

    /**
     * The primary player color, which controls the color of the progress bar, buttons,
     * and more.
     */
    @Json(name = "color")
    val color: String? = null,

    /**
     * HTML code for embedding this video on a web page.
     */
    @Json(name = "html")
    val html: String? = null,

    /**
     * Whether the playbar appears in the embeddable player for this video.
     */
    @Json(name = "playBar")
    val playBar: Boolean? = null,

    /**
     * Whether the speed controls appear in the embeddable player for this video.
     */
    @Json(name = "speed")
    val speed: Boolean? = null,

    /**
     * A collection of information relating to the embeddable player's title bar.
     */
    @Json(name = "title")
    val title: EmbedTitle? = null,

    /**
     * The URI of the embed preset.
     */
    @Json(name = "uri")
    val uri: String? = null,

    /**
     * Whether the volume controls appear in the embeddable player for this video.
     */
    @Json(name = "volume")
    val volume: Boolean? = null

)
