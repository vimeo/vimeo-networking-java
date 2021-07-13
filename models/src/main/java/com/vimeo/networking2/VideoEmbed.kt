package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Video embed data.
 *
 * @param badges A collection of the video's badges.
 * @param buttons A collection of information about the buttons that appear on the interface of the embeddable player.
 * @param color The primary player color, which controls the color of the progress bar, buttons, and more.
 * @param html HTML code for embedding this video on a web page.
 * @param playBar Whether the playbar appears in the embeddable player for this video.
 * @param speed Whether the speed controls appear in the embeddable player for this video.
 * @param title A collection of information relating to the embeddable player's title bar.
 * @param uri The URI of the embed preset.
 * @param volume Whether the volume controls appear in the embeddable player for this video.
 */
@JsonClass(generateAdapter = true)
data class VideoEmbed(

    @Json(name = "badges")
    val badges: VideoBadges? = null,

    @Json(name = "buttons")
    val buttons: EmbedButtons? = null,

    @Json(name = "color")
    val color: String? = null,

    @Json(name = "html")
    val html: String? = null,

    @Json(name = "playBar")
    val playBar: Boolean? = null,

    @Json(name = "speed")
    val speed: Boolean? = null,

    @Json(name = "title")
    val title: EmbedTitle? = null,

    @Json(name = "uri")
    val uri: String? = null,

    @Json(name = "volume")
    val volume: Boolean? = null

)
