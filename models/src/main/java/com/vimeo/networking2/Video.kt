package com.vimeo.networking2

import com.vimeo.networking2.enums.LicenseType
import com.vimeo.networking2.enums.TranscodeType
import com.vimeo.networking2.enums.VideoStatusType
import java.util.*

data class Video(

    /**
     * Information for the video's badge.
     */
    val badge: VideoBadge?,

    /**
     * The categories to which this video belongs.
     */
    val categories: List<Category>?,

    /**
     * The content ratings of this video.
     */
    val contentRating: List<String>?,

    /**
     * The context of the video's subscription, if this video is part of a subscription.
     */
    val context: VideoContext?,

    /**
     * The time in ISO 8601 format when the video was created.
     */
    val createdTime: Date?,

    /**
     * A brief explanation of the video's content.
     */
    val description: String?,

    /**
     * The video's duration in seconds.
     */
    val duration: Int?,

    /**
     * Information about embedding this video.
     */
    val embed: VideoEmbed?,

    /**
     * The video's height in pixels.
     */
    val height: Int?,

    /**
     * The video's primary language.
     */
    val language: String?,

    /**
     * The time in ISO 8601 format when the user last modified the video.
     */
    val lastUserActionEventDate: Date?,

    /**
     * The Creative Commons license used for the video.
     */
    val license: LicenseType?,

    /**
     * The link to the video.
     */
    val link: String?,

    /**
     * Live playback information.
     */
    val live: Live?,

    /**
     * The video's metadata.
     */
    val metadata: VideoMetadata?,

    /**
     * The time in ISO 8601 format when the video metadata was last modified.
     */
    val modifiedTime: Date?,

    /**
     * The video's title.
     */
    val name: String?,

    /**
     * Information about the folder that contains this video.
     */
    val parentFolder: Folder?,

    /**
     * The privacy-enabled password to watch this video.
     * This data requires a bearer token with the private scope.
     *
     * Based on CAPABILITY_PROTECTED_VIDEOS.
     */
    val password: String?,

    /**
     * The active picture for this video.
     */
    val pictures: PictureCollection?,

    /**
     * The Play representation.
     *
     * Based on CAPABILITY_PLAY_REPRESENTATION.
     */
    val play: Play?,

    /**
     * The video's privacy setting.
     */
    val privacy: Privacy?,

    /**
     * The time in ISO 8601 format when the video was released.
     */
    val releaseTime: Date?,

    /**
     * The resource key string of the video.
     */
    val resourceKey: String?,

    /**
     * Information about the review page associated with this video. This data requires a
     * bearer token with the private scope.
     *
     * Based on CAPABILITY_VIDEO_REVIEW.
     */
    val reviewPage: ReviewPage?,

    /**
     * 360 spatial data.
     */
    val spatial: Spatial?,

    /**
     * A collection of stats associated with this video.
     */
    val stats: VideoStats?,

    /**
     * The status code for the availability of the video. This field is deprecated in favor
     * of upload and transcode.
     */
    val status: VideoStatusType?,

    /**
     * An array of all tags assigned to this video.
     */
    val tags: List<Tag>?,

    /**
     * The transcode information for a video upload.
     */
    val transcode: TranscodeType?,

    /**
     * The upload information for this video.
     */
    val upload: Upload?,

    /**
     * The video's canonical relative URI.
     */
    val uri: String?,

    /**
     * The video owner.
     */
    val user: User?,

    /**
     * The video's width in pixels.
     */
    val width: Int?

)
