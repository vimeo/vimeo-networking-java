package com.vimeo.networking2

import com.vimeo.networking2.enums.VideoPlayStatus
import com.vimeo.networking2.enums.VideoSourceFile

data class Play(

        /**
         * The DASH video file.
         */
        val dash: VideoFile?,

        /**
         * HLS video files.
         */
        val hls: VideoFile?,

        /**
         * The play progress in seconds.
         */
        val progress: Int?,

        /**
         * Progressive files.
         */
        val progressive: Progressive?,

        /**
         * The source file of the video.
         */
        val source: VideoSourceFile?,

        /**
         * The play status of the video.
         */
        val status: VideoPlayStatus?
)
