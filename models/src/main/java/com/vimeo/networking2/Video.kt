package com.vimeo.networking2

import com.vimeo.networking2.common.Reportable
import com.vimeo.networking2.enums.LicenseType
import com.vimeo.networking2.enums.LicenseType.UNKNOWN
import com.vimeo.networking2.enums.TranscodeType
import com.vimeo.networking2.enums.VideoStatusType
import java.util.*

/**
 * Video data.
 */
data class Video(

    /**
     * Information for the video's badge.
     */
    val badge: VideoBadge? = null,

    /**
     * The categories to which this video belongs.
     */
    val categories: List<Category>? = null,

    /**
     * The content ratings of this video.
     */
    val contentRating: List<String>? = null,

    /**
     * The context of the video's subscription, if this video is part of a subscription.
     */
    val context: VideoContext? = null,

    /**
     * The time in ISO 8601 format when the video was created.
     */
    val createdTime: Date? = null,

    /**
     * A brief explanation of the video's content.
     */
    val description: String? = null,

    /**
     * The video's duration in seconds.
     */
    val duration: Int? = null,

    /**
     * Information about embedding this video.
     */
    val embed: VideoEmbed? = null,

    /**
     * The video's height in pixels.
     */
    val height: Int? = null,

    /**
     * The video's primary language.
     */
    val language: String? = null,

    /**
     * The time in ISO 8601 format when the user last modified the video.
     */
    val lastUserActionEventDate: Date? = null,

    /**
     * The Creative Commons license used for the video.
     */
    val license: LicenseType = UNKNOWN,

    /**
     * The link to the video.
     */
    val link: String? = null,

    /**
     * Live playback information.
     */
    val live: Live? = null,

    /**
     * The video's metadata.
     */
    override val metadata: Metadata<VideoConnections, VideoInteractions>? = null,

    /**
     * The time in ISO 8601 format when the video metadata was last modified.
     */
    val modifiedTime: Date? = null,

    /**
     * The video's title.
     */
    val name: String? = null,

    /**
     * Information about the folder that contains this video.
     */
    val parentFolder: Folder? = null,

    /**
     * The privacy-enabled password to watch this video.
     * This data requires a bearer token with the private scope.
     *
     * Requires [CapabilitiesType.CAPABILITY_PROTECTED_VIDEOS].
     */
    val password: String? = null,

    /**
     * The active picture for this video.
     */
    val pictures: PictureCollection? = null,

    /**
     * The Play representation.
     *
     * Requires [CapabilitiesType.CAPABILITY_PLAY_REPRESENTATION].
     */
    val play: Play? = null,

    /**
     * The video's privacy setting.
     */
    val privacy: Privacy? = null,

    /**
     * The time in ISO 8601 format when the video was released.
     */
    val releaseTime: Date? = null,

    /**
     * The resource key string of the video.
     */
    val resourceKey: String? = null,

    /**
     * Information about the review page associated with this video. This data requires a
     * bearer token with the private scope.
     *
     * Requires [CapabilitiesType.CAPABILITY_VIDEO_REVIEW].
     */
    val reviewPage: ReviewPage? = null,

    /**
     * 360 spatial data.
     */
    val spatial: Spatial? = null,

    /**
     * A collection of stats associated with this video.
     */
    val stats: VideoStats? = null,

    /**
     * The status code for the availability of the video. This field is deprecated in favor
     * of [upload] and [transcode].
     */
    @Deprecated("This field is deprecated in favor of upload and transcode.")
    val status: VideoStatusType? = VideoStatusType.UNKNOWN,

    /**
     * An array of all tags assigned to this video.
     */
    val tags: List<Tag>? = null,

    /**
     * The transcode information for a video upload.
     */
    val transcode: TranscodeType? = TranscodeType.UNKNOWN,

    /**
     * The upload information for this video.
     */
    val upload: Upload? = null,

    /**
     * The video's canonical relative URI.
     */
    val uri: String? = null,

    /**
     * The video owner.
     */
    val user: User? = null,

    /**
     * The video's width in pixels.
     */
    val width: Int? = null

): Reportable
