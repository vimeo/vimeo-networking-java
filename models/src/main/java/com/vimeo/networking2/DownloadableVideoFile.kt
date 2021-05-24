package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.VideoQualityType
import com.vimeo.networking2.enums.VideoSourceType
import com.vimeo.networking2.enums.asEnum
import java.util.Date

/**
 * A downloadable video file.
 *
 * @param publicName A name describing the file, suitable for display.
 * @param size The size of the file, in bytes.
 * @param sizeShort A short description of the file size, accurate to two decimal places.
 * @param md5 The MD5 hash of the file.
 * @param link The link from which the file can be downloaded.
 * @param width The width of the video content, in pixels.
 * @param height The height of the video content, in pixels.
 * @param videoQuality The quality of the video file. See [qualityType].
 * @param expires The expiration date of the file's [link].
 * @param fps The FPS of the video file.
 * @param rawType The type of the video file. See [type].
 */
@JsonClass(generateAdapter = true)
data class DownloadableVideoFile(

    @Json(name = "public_name")
    val publicName: String? = null,

    @Json(name = "size")
    val size: Long? = null,

    @Json(name = "size_short")
    val sizeShort: String? = null,

    @Json(name = "md5")
    val md5: String? = null,

    @Json(name = "link")
    val link: String? = null,

    @Json(name = "width")
    val width: Int? = null,

    @Json(name = "height")
    val height: Int? = null,

    @Json(name = "quality")
    val videoQuality: String? = null,

    @Json(name = "expires")
    val expires: Date? = null,

    @Json(name = "fps")
    val fps: Float? = null,

    @Json(name = "type")
    val rawType: String? = null
)

/**
 * @see DownloadableVideoFile.videoQuality
 * @see VideoQualityType
 */
val DownloadableVideoFile.qualityType: VideoQualityType
    get() = videoQuality.asEnum(VideoQualityType.UNKNOWN)

/**
 * @see DownloadableVideoFile.rawType
 * @see VideoQualityType
 */
val DownloadableVideoFile.type: VideoSourceType
    get() = rawType.asEnum(VideoSourceType.UNKNOWN)
