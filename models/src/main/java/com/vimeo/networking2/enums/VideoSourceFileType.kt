package com.vimeo.networking2.enums

import com.vimeo.networking2.VideoLog
import java.util.*

data class VideoSourceFileType(

    /**
     * The time in ISO 8601 format when the video file was created.
     */
    val createdTime: Date?,

    /**
     * The time in ISO 8601 format when the video file expires.
     */
    val expires: Date?,

    /**
     * The FPS of the video.
     */
    val fps: Int?,

    /**
     * The height of the video in pixels.
     */
    val height: Int?,

    /**
     * The direct link to this video file.
     */
    val link: String?,

    /**
     * Video log.
     */
    val log: VideoLog?,

    /**
     * The MD5 hash of the video file.
     */
    val md5: String?,

    /**
     * The video quality (as determined by height and width).
     */
    val quality: VideoQualityType?,

    /**
     * The file size of the video.
     */
    val siz: Int?,

    /**
     * The source link for the video file.
     */
    val sourceLink: String?,

    /**
     * The type of the video file.
     */
    val type: VideoSourceType?,

    /**
     * The width of the video in pixels.
     */
    val width: Int?

)
