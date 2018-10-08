package com.vimeo.networking2

import com.vimeo.networking2.enums.VideoPlayStatus
import com.vimeo.networking2.enums.VideoSourceFileType

data class Play(

    /**
     * The DASH video file.
     */
    val dash: VideoFile? = null,

    /**
     * HLS video files.
     */
    val hls: VideoFile? = null,

    /**
     * The play progress in seconds.
     */
    val progress: Int? = null,

    /**
     * Progressive files.
     */
    val progressive: List<ProgressiveFileType>? = null,

    /**
     * The source file of the video.
     */
    val source: VideoSourceFileType? = null,

    /**
     * The play status of the video.
     */
    val status: VideoPlayStatus? = null
)
