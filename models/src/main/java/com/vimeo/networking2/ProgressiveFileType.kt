package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.VideoQualityType
import com.vimeo.networking2.enums.VideoSourceType
import java.util.*

@JsonClass(generateAdapter = true)
data class ProgressiveFileType(

    /**
     * The time in ISO 8601 format when the video file was created.
     */
    @Json(name = "created_time")
    val createdTime: Date? = null,

    /**
     * The FPS of the video.
     */
    @Json(name = "fps")
    val fps: Double? = null,

    /**
     * The height of the video in pixels.
     */
    @Json(name = "height")
    val height: Int? = null,

    /**
     * The direct link to this video file.
     */
    @Json(name = "link")
    val link: String? = null,

    /**
     * The time in ISO 8601 format when the link to this video file expires.
     */
    @Json(name = "link_expiration_time")
    val linkExpirationTime: String? = null,

    /**
     * Video logging information.
     */
    @Json(name = "log")
    val log: String? = null,

    /**
     * The MD5 hash of the video file.
     */
    @Json(name = "md5")
    val md5: String? = null,

    /**
     * The video quality (as determined by height and width).
     */
    @Json(name = "quality")
    val quality: VideoQualityType? = null,

    /**
     * The file size of the video.
     */
    @Json(name = "size")
    val size: Int? = null,

    /**
     * The source link for the video file.
     */
    @Json(name = "source_link")
    val sourceLink: String? = null,

    /**
     * The type of the video file.
     */
    @Json(name = "type")
    val type: VideoSourceType? = null,

    /**
     * The width of the video in pixels.
     */
    @Json(name = "width")
    val width: Int? = null

)
