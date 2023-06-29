package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Information about the Vimeo Create session of a video.
 *
 * @param isMusicLicensed Whether the video has licensed music.
 * @param isMaxResolution Whether the current version of clip is of max resolution.
 * @param vsid Video session id.
 * @param resultVideoHash The result video hash.
 * @param hasWatermark Whether the video has watermark.
 * @param isRated Whether the video is rated.
 * @param minTierForMovie The minimum required Vimeo membership for the user to be able to share or download the video.
 * @param isEditedByTve Whether the video has been edited by Transcript Video Editing.
 */
@JsonClass(generateAdapter = true)
data class EditSession(

    @Json(name = "is_music_licensed")
    val isMusicLicensed: Boolean? = null,

    @Json(name = "is_max_resolution")
    val isMaxResolution: Boolean? = null,

    @Json(name = "vsid")
    val vsid: Int? = null,

    @Json(name = "result_video_hash")
    val resultVideoHash: String? = null,

    @Json(name = "has_watermark")
    val hasWatermark: Boolean? = null,

    @Json(name = "is_rated")
    val isRated: Boolean? = null,

    @Json(name = "min_tier_for_movie")
    val minTierForMovie: String? = null,

    @Json(name = "is_edited_by_tve")
    val isEditedByTve: Boolean? = null
)
