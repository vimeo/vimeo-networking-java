@file:JvmName("VideoUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal
import com.vimeo.networking2.common.Entity
import com.vimeo.networking2.enums.LicenseType
import com.vimeo.networking2.enums.VideoStatusType
import com.vimeo.networking2.enums.asEnum
import java.util.*

/**
 * Video data.
 */
@JsonClass(generateAdapter = true)
data class Video(

    /**
     * Information for the video's badge.
     */
    @Json(name = "badge")
    val badge: VideoBadge? = null,

    /**
     * The categories to which this video belongs.
     */
    @Json(name = "categories")
    val categories: List<Category>? = null,

    /**
     * The content ratings of this video.
     */
    @Json(name = "content_rating")
    val contentRating: List<String>? = null,

    /**
     * The context of the video's subscription, if this video is part of a subscription.
     */
    @Json(name = "context")
    val context: VideoContext? = null,

    /**
     * The time in ISO 8601 format when the video was created.
     */
    @Json(name = "created_time")
    val createdTime: Date? = null,

    /**
     * A brief explanation of the video's content.
     */
    @Json(name = "description")
    val description: String? = null,

    /**
     * The video's duration in seconds.
     */
    @Json(name = "duration")
    val duration: Int? = null,

    /**
     * Information about embedding this video.
     */
    @Json(name = "embed")
    val embed: VideoEmbed? = null,

    /**
     * The video's height in pixels.
     */
    @Json(name = "height")
    val height: Int? = null,

    /**
     * The video's primary language.
     */
    @Json(name = "language")
    val language: String? = null,

    /**
     * The time in ISO 8601 format when the user last modified the video.
     */
    @Json(name = "last_user_action_event_date")
    val lastUserActionEventDate: Date? = null,

    /**
     * The Creative Commons license used for the video.
     * @see Video.licenseType
     */
    @Json(name = "license")
    val license: String? = null,

    /**
     * The link to the video.
     */
    @Json(name = "link")
    val link: String? = null,

    /**
     * Live playback information.
     */
    @Json(name = "live")
    val live: Live? = null,

    /**
     * The video's metadata.
     */
    @Json(name = "metadata")
    val metadata: Metadata<VideoConnections, VideoInteractions>? = null,

    /**
     * The time in ISO 8601 format when the video metadata was last modified.
     */
    @Json(name = "modified_time")
    val modifiedTime: Date? = null,

    /**
     * The video's title.
     */
    @Json(name = "name")
    val name: String? = null,

    /**
     * The privacy-enabled password to watch this video.
     * This data requires a bearer token with the private scope.
     */
    @Internal
    @Json(name = "password")
    val password: String? = null,

    /**
     * The active picture for this video.
     */
    @Json(name = "pictures")
    val pictures: PictureCollection? = null,

    /**
     * The Play representation.
     */
    @Internal
    @Json(name = "play")
    val play: Play? = null,

    /**
     * The video's privacy setting.
     */
    @Json(name = "privacy")
    val privacy: Privacy? = null,

    /**
     * The time in ISO 8601 format when the video was released.
     */
    @Json(name = "release_time")
    val releaseTime: Date? = null,

    /**
     * The resource key string of the video.
     */
    @Json(name = "resource_key")
    val resourceKey: String? = null,

    /**
     * Information about the review page associated with this video. This data requires a
     * bearer token with the private scope.
     */
    @Internal
    @Json(name = "review_page")
    val reviewPage: ReviewPage? = null,

    /**
     * Information about the file transfer page associated with this video. This data
     * requires a bearer token with the private scope.
     */
    @Internal
    @Json(name = "file_transfer")
    val fileTransferPage: FileTransferPage? = null,

    /**
     * 360 spatial data.
     */
    @Json(name = "spatial")
    val spatial: Spatial? = null,

    /**
     * A collection of stats associated with this video.
     */
    @Json(name = "stats")
    val stats: VideoStats? = null,

    /**
     * The status code for the availability of the video. This field is deprecated in favor
     * of [upload] and [transcode].
     * @see Video.statusType
     */
    @Json(name = "status")
    @Deprecated("This property is deprecated in favor of upload and transcode.")
    val status: String? = null,

    /**
     * An array of all tags assigned to this video.
     */
    @Json(name = "tags")
    val tags: List<Tag>? = null,

    /**
     * The transcode information for a video upload.
     */
    @Json(name = "transcode")
    val transcode: Transcode? = null,

    /**
     * The upload information for this video.
     */
    @Json(name = "upload")
    val upload: Upload? = null,

    /**
     * The video's canonical relative URI.
     */
    @Json(name = "uri")
    val uri: String? = null,

    /**
     * The video owner.
     */
    @Json(name = "user")
    val user: User? = null,

    /**
     * The video's width in pixels.
     */
    @Json(name = "width")
    val width: Int? = null

) : Entity {

    override val identifier: String? = resourceKey

}

/**
 * @see Video.license
 * @see LicenseType
 */
val Video.licenseType: LicenseType
    get() = license.asEnum(LicenseType.UNKNOWN)

/**
 * @see Video.status
 * @see VideoStatusType
 */
@Deprecated(message = "This property is deprecated in favor of upload and transcode.")
val Video.statusType: VideoStatusType
    get() = status.asEnum(VideoStatusType.UNKNOWN)
