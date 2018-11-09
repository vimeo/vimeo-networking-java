package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal
import com.vimeo.networking2.enums.ApproachType
import com.vimeo.networking2.enums.UploadStatusType

/**
 * Upload data.
 */
@JsonClass(generateAdapter = true)
data class Upload(

    /**
     * The approach for uploading the video.
     */
    @Json(name = "approach")
    val approach: ApproachType? = null,

    /**
     * The URI for completing the upload.
     */
    @Json(name = "complete_uri")
    val completeUri: String? = null,

    /**
     * The HTML form for uploading a video through the post approach.
     */
    @Json(name = "form")
    val form: String? = null,

    /**
     * GCS information to perform an upload.
     */
    @Internal
    @Json(name = "gcs")
    val gcs: Gcs? = null,

    /**
     * The link of the video to capture through the pull approach.
     */
    @Json(name = "link")
    val link: String? = null,

    /**
     * The redirect URL for the upload app.
     */
    @Json(name = "redirectUrl")
    val redirectUrl: String? = null,

    /**
     * The file size in bytes of the uploaded video.
     */
    @Json(name = "size")
    val size: Long? = null,

    /**
     * The status code for the availability of the uploaded video.
     */
    @Json(name = "status")
    val status: UploadStatusType? = null,

    /**
     * The link for sending video file data.
     */
    @Json(name = "upload_link")
    val uploadLink: String? = null

)
