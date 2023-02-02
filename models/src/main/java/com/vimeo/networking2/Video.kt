@file:JvmName("VideoUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal
import com.vimeo.networking2.common.Entity
import com.vimeo.networking2.enums.LicenseType
import com.vimeo.networking2.enums.VideoStatusType
import com.vimeo.networking2.enums.asEnum
import java.util.Date

/**
 * Video data.
 *
 * @param badge Information for the video's badge.
 * @param categories The categories to which this video belongs.
 * @param contentRating The content ratings of this video.
 * @param context The context of the video's subscription, if this video is part of a subscription.
 * @param createdTime The time in ISO 8601 format when the video was created.
 * @param description A brief explanation of the video's content.
 * @param download A list of downloadable files.
 * @param duration The video's duration in seconds.
 * @param editSession Information about the Vimeo Create session of a video.
 * @param embed Information about embedding this video.
 * @param fileTransferPage Information about the file transfer page associated with this video. This data requires a
 * bearer token with the private scope.
 * @param height The video's height in pixels.
 * @param isPlayable Whether the clip is playable.
 * @param language The video's primary language.
 * @param lastUserActionEventDate The time in ISO 8601 format when the user last modified the video.
 * @param license The Creative Commons license used for the video. See [Video.licenseType].
 * @param link The link to the video.
 * @param live Live playback information.
 * @param metadata The video's metadata.
 * @param modifiedTime The time in ISO 8601 format when the video metadata was last modified.
 * @param name The video's title.
 * @param parentFolder Information about the folder that contains the video, or null if it is in the root directory.
 * @param password The privacy-enabled password to watch this video. This data requires a bearer token with the private
 * scope.
 * @param pictures The active picture for this video.
 * @param play The Play representation.
 * @param privacy The video's privacy setting.
 * @param releaseTime The time in ISO 8601 format when the video was released.
 * @param resourceKey The resource key string of the video.
 * @param reviewPage Information about the review page associated with this video. This data requires a bearer token
 * with the private scope.
 * @param spatial 360 spatial data.
 * @param stats A collection of stats associated with this video.
 * @param status The status code for the availability of the video. This field is deprecated in favor of [upload] and
 * [transcode]. See [Video.statusType].
 * @param tags An array of all tags assigned to this video.
 * @param transcode The transcode information for a video upload.
 * @param upload The upload information for this video.
 * @param uri The video's canonical relative URI.
 * @param user The video owner.
 * @param width The video's width in pixels.
 */
@JsonClass(generateAdapter = true)
data class Video(

    @Json(name = "badge")
    val badge: VideoBadge? = null,

    @Json(name = "categories")
    val categories: List<Category>? = null,

    @Json(name = "content_rating")
    val contentRating: List<String>? = null,

    @Json(name = "context")
    val context: VideoContext? = null,

    @Json(name = "created_time")
    val createdTime: Date? = null,

    @Json(name = "description")
    val description: String? = null,

    @Json(name = "download")
    val download: List<DownloadableVideoFile>? = null,

    @Json(name = "duration")
    val duration: Int? = null,

    @Json(name = "edit_session")
    val editSession: EditSession? = null,

    @Json(name = "embed")
    val embed: VideoEmbed? = null,

    @Internal
    @Json(name = "file_transfer")
    val fileTransferPage: FileTransferPage? = null,

    @Json(name = "height")
    val height: Int? = null,

    @Json(name = "is_playable")
    val isPlayable: Boolean? = null,

    @Json(name = "language")
    val language: String? = null,

    @Json(name = "last_user_action_event_date")
    val lastUserActionEventDate: Date? = null,

    @Json(name = "license")
    val license: String? = null,

    @Json(name = "link")
    val link: String? = null,

    @Json(name = "live")
    val live: Live? = null,

    @Json(name = "metadata")
    val metadata: Metadata<VideoConnections, VideoInteractions>? = null,

    @Json(name = "modified_time")
    val modifiedTime: Date? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "parent_project")
    val parentFolder: Folder? = null,

    @Internal
    @Json(name = "password")
    val password: String? = null,

    @Json(name = "pictures")
    val pictures: PictureCollection? = null,

    @Internal
    @Json(name = "play")
    val play: Play? = null,

    @Json(name = "privacy")
    val privacy: Privacy? = null,

    @Json(name = "release_time")
    val releaseTime: Date? = null,

    @Json(name = "resource_key")
    val resourceKey: String? = null,

    @Internal
    @Json(name = "review_page")
    val reviewPage: ReviewPage? = null,

    @Json(name = "spatial")
    val spatial: Spatial? = null,

    @Json(name = "stats")
    val stats: VideoStats? = null,

    @Json(name = "status")
    @Deprecated("This property is deprecated in favor of upload and transcode.")
    val status: String? = null,

    @Json(name = "tags")
    val tags: List<Tag>? = null,

    @Json(name = "transcode")
    val transcode: Transcode? = null,

    @Json(name = "upload")
    val upload: Upload? = null,

    @Json(name = "uri")
    override val uri: String? = null,

    @Json(name = "user")
    val user: User? = null,

    @Json(name = "width")
    val width: Int? = null

) : Entity, VideoContainer<Video> {

    override val identifier: String? = resourceKey

    override val video: Video get() = this

    override fun copyVideoContainer(
        video: Video?,
    ): Video = video ?: this
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
