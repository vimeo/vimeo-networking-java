@file:JvmName("ProgressiveVideoFileUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.PlayableFile
import com.vimeo.networking2.enums.VideoQualityType
import com.vimeo.networking2.enums.VideoSourceType
import com.vimeo.networking2.enums.asEnum
import java.io.Serializable
import java.util.*

/**
 * The representation of a video file that could be one of a number of types.
 */
@JsonClass(generateAdapter = true)
data class ProgressiveVideoFile(

    @Json(name = "link")
    override val link: String? = null,

    @Json(name = "link_expiration_time")
    override val linkExpirationTime: Date? = null,

    @Json(name = "log")
    override val log: String? = null,

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
     * The MD5 hash of the video file.
     */
    @Json(name = "md5")
    val md5: String? = null,

    /**
     * The video quality (as determined by height and width).
     * @see ProgressiveVideoFile.videoQualityType
     */
    @Json(name = "quality")
    val videoQuality: String? = null,

    /**
     * The file size of the video.
     */
    @Json(name = "size")
    val size: Long? = null,

    /**
     * The source link for the video file.
     */
    @Json(name = "source_link")
    val sourceLink: String? = null,

    /**
     * The type of the video file.
     * @see ProgressiveVideoFile.type
     */
    @Json(name = "type")
    val rawType: String? = null,

    /**
     * The width of the video in pixels.
     */
    @Json(name = "width")
    val width: Int? = null

) : PlayableFile, Serializable {

    companion object {
        private const val serialVersionUID = -304502918118L
    }
}

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
