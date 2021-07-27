@file:JvmName("ProgressiveVideoFileUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.LoggingVideoFile
import com.vimeo.networking2.enums.VideoQualityType
import com.vimeo.networking2.enums.VideoSourceType
import com.vimeo.networking2.enums.asEnum
import java.util.Date

/**
 * The representation of a video file that could be one of a number of types.
 *
 * @param createdTime The time in ISO 8601 format when the video file was created.
 * @param fps The FPS of the video.
 * @param height The height of the video in pixels.
 * @param md5 The MD5 hash of the video file.
 * @param videoQuality The video quality (as determined by height and width). See
 * [ProgressiveVideoFile.videoQualityType].
 * @param size The file size of the video.
 * @param sourceLink The source link for the video file.
 * @param rawType The type of the video file. See [ProgressiveVideoFile.type].
 * @param width The width of the video in pixels.
 */
@Deprecated("Use DownloadableVideoFile instead")
@JsonClass(generateAdapter = true)
data class ProgressiveVideoFile(

    @Json(name = "link")
    override val link: String? = null,

    @Json(name = "link_expiration_time")
    override val linkExpirationTime: Date? = null,

    @Json(name = "log")
    override val log: String? = null,

    @Json(name = "created_time")
    val createdTime: Date? = null,

    @Json(name = "fps")
    val fps: Double? = null,

    @Json(name = "height")
    val height: Int? = null,

    @Json(name = "md5")
    val md5: String? = null,

    @Json(name = "quality")
    val videoQuality: String? = null,

    @Json(name = "size")
    val size: Long? = null,

    @Json(name = "source_link")
    val sourceLink: String? = null,

    @Json(name = "type")
    val rawType: String? = null,

    @Json(name = "width")
    val width: Int? = null

) : LoggingVideoFile

/**
 * @see ProgressiveVideoFile.videoQuality
 * @see VideoQualityType
 */
val ProgressiveVideoFile.videoQualityType: VideoQualityType
    get() = videoQuality.asEnum(VideoQualityType.UNKNOWN)

/**
 * @see ProgressiveVideoFile.rawType
 * @see VideoSourceType
 */
val ProgressiveVideoFile.type: VideoSourceType
    get() = rawType.asEnum(VideoSourceType.UNKNOWN)
