package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * LiveEvent embed data.
 *
 * @param chatEmbedSource The chat's iFrame source URL.
 * @param color The primary player color, which controls the color of the progress bar, buttons, and more.
 * @param embedChat The embed code for RLE chat.
 * @param embedProperties The height, width, and source URL properties used to generate the fixed HTML embed code.
 * @param html The fixed HTML code to embed the live event's playlist on a website.
 * @param logoInfo A collection of information about the logo in the corner of the embeddable player.
 * @param responsiveHtml The responsive HTML code to embed the live event's playlist on a website.
 * @param shouldAutoplay Whether the embedded RLE player should autoplay the RLE content.
 * @param shouldDisplayEventSchedule Whether the embedded RLE player should display the event schedule.
 * @param shouldDisplayFullscreenControls Whether the embedded RLE player should include the fullscreen controls.
 * @param shouldDisplayLikeButton Whether the embedded RLE player should include the like button.
 * @param shouldHideLiveLabel Whether the embedded RLE player should hide the live label.
 * @param shouldHideViewerCount Whether the embedded RLE player should hide the viewer count.
 * @param shouldLoop Whether the embedded RLE player should loop back to the first video once content is exhausted.
 * @param shouldShowName Whether the embedded RLE player should display the author's name.
 * @param shouldShowPlayBar Whether the embedded RLE player should include the play bar.
 * @param shouldShowPlayList Whether the play list component appears in the embeddable player for this RLE.
 * @param shouldShowPortrait Whether the embedded RLE player should display the author's portrait.
 * @param shouldShowSchedule Whether the schedule component appears in the embeddable player for this RLE.
 * @param shouldShowLatestVideoPlaceholder Whether the embedded RLE player should display the latest video placeholder.
 * @param shouldShowVolumeControls Whether the embedded RLE player should include the volume controls.
 * @param shouldShowTitle Whether the embedded RLE player should display the video title.
 * @param shouldUseCustomColor Whether the embedded RLE player should use a custom color or the default Vimeo blue.
 */
@JsonClass(generateAdapter = true)
data class LiveEventEmbed(

    @Json(name = "chat_embed_source")
    val chatEmbedSource: String? = null,

    @Json(name = "color")
    val color: String? = null,

    @Json(name = "embed_chat")
    val embedChat: String? = null,

//    @Json(name = "embed_properties")
//    val embedProperties: LiveEventEmbedProperties? = null,

    @Json(name = "html")
    val html: String? = null,

//    @Json(name = "logos")
//    val logoInfo: LiveEventLogoInfo? = null,

    @Json(name = "responsiveHtml")
    val responsiveHtml: String? = null,

    @Json(name = "autoplay")
    val shouldAutoplay: Boolean? = null,

    @Json(name = "event_schedule")
    val shouldDisplayEventSchedule: Boolean? = null,

    @Json(name = "fullscreen_button")
    val shouldDisplayFullscreenControls: Boolean? = null,

    @Json(name = "like_button")
    val shouldDisplayLikeButton: Boolean? = null,

    @Json(name = "hide_live_label")
    val shouldHideLiveLabel: Boolean? = null,

    @Json(name = "hide_viewer_count")
    val shouldHideViewerCount: Boolean? = null,

    @Json(name = "loop")
    val shouldLoop: Boolean? = null,

    @Json(name = "byline")
    val shouldShowName: Boolean? = null,

    @Json(name = "playbar")
    val shouldShowPlayBar: Boolean? = null,

    @Json(name = "playlist")
    val shouldShowPlayList: Boolean? = null,

    @Json(name = "portrait")
    val shouldShowPortrait: Boolean? = null,

    @Json(name = "schedule")
    val shouldShowSchedule: Boolean? = null,

    @Json(name = "show_latest_archived_clip")
    val shouldShowLatestVideoPlaceholder: Boolean? = null,

    @Json(name = "volume")
    val shouldShowVolumeControls: Boolean? = null,

    @Json(name = "title")
    val shouldShowTitle: Boolean? = null,

    @Json(name = "use_color")
    val shouldUseCustomColor: Boolean? = null
)
