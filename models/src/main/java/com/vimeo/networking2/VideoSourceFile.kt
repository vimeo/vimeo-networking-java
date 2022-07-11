@file:JvmName("VideoSourceFileUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.VideoQualityType
import com.vimeo.networking2.enums.VideoSourceType
import com.vimeo.networking2.enums.asEnum
import java.util.Date

/**
 * A video file that is a source for a published video.
 *
 * @param createdTime The time in ISO 8601 format when the video file was created.
 * @param expires The time in ISO 8601 format when the video file expires.
 * @param fps The FPS of the video.
 * @param height The height of the video in pixels.
 * @param link The direct link to this video file.
 * @param log Video logging information.
 * @param md5 The MD5 hash of the video file.
 * @param videoQuality The video quality (as determined by height and width). See [VideoSourceFile.videoQualityType].
 * @param size The file size of the video.
 * @param sourceLink The source link for the video file.
 * @param rawType The type of the video file. See [VideoSourceFile.type].
 * @param width The width of the video in pixels.
 */
@JsonClass(generateAdapter = true)
data class VideoSourceFile(

    @Json(name = "created_time")
    val createdTime: Date? = null,

    @Json(name = "expires")
    val expires: Date? = null,

    @Json(name = "fps")
    val fps: Double? = null,

    @Json(name = "height")
    val height: Int? = null,

    @Json(name = "link")
    val link: String? = null,

    @Json(name = "log")
    val log: VideoLog? = null,

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

)

/**
 * @see ProgressiveVideoFile.videoQuality
 * @see VideoQualityType
 */
val VideoSourceFile.videoQualityType: VideoQualityType
    get() = videoQuality.asEnum(VideoQualityType.UNKNOWN)

/**
 * @see ProgressiveVideoFile.rawType
 * @see VideoSourceType
 */
val VideoSourceFile.type: VideoSourceType
    get() = rawType.asEnum(VideoSourceType.UNKNOWN)
