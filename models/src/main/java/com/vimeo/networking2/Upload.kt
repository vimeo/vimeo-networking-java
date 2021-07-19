@file:JvmName("UploadUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal
import com.vimeo.networking2.enums.ApproachType
import com.vimeo.networking2.enums.UploadStatusType
import com.vimeo.networking2.enums.asEnum

/**
 * Upload data.
 *
 * @param approach The approach for uploading the video. See [Upload.approachType].
 * @param completeUri The URI for completing the upload.
 * @param form The HTML form for uploading a video through the post approach.
 * @param gcs GCS information to perform an upload.
 * @param link The link of the video to capture through the pull approach.
 * @param redirectUrl The redirect URL for the upload app.
 * @param size The file size in bytes of the uploaded video.
 * @param status The status code for the availability of the uploaded video. See [Upload.statusType].
 * @param uploadLink The link for sending video file data.
 */
@JsonClass(generateAdapter = true)
data class Upload(

    @Json(name = "approach")
    val approach: String? = null,

    @Json(name = "complete_uri")
    val completeUri: String? = null,

    @Json(name = "form")
    val form: String? = null,

    @Internal
    @Json(name = "gcs")
    val gcs: List<Gcs>? = null,

    @Json(name = "link")
    val link: String? = null,

    @Json(name = "redirectUrl")
    val redirectUrl: String? = null,

    @Json(name = "size")
    val size: Long? = null,

    @Json(name = "status")
    val status: String? = null,

    @Json(name = "upload_link")
    val uploadLink: String? = null

)

/**
 * @see Upload.approach
 * @see ApproachType
 */
val Upload.approachType: ApproachType
    get() = approach.asEnum(ApproachType.UNKNOWN)

/**
 * @see Upload.status
 * @see UploadStatusType
 */
val Upload.statusType: UploadStatusType
    get() = status.asEnum(UploadStatusType.UNKNOWN)
