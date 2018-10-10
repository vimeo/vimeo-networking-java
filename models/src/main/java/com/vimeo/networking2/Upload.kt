package com.vimeo.networking2

import com.vimeo.networking2.enums.ApproachType
import com.vimeo.networking2.enums.UploadStatusType

/**
 * Upload data.
 */
data class Upload(

    /**
     * The approach for uploading the video.
     */
    val approach: ApproachType? = null,

    /**
     * The URI for completing the upload.
     */
    val completeUri: String? = null,

    /**
     * The HTML form for uploading a video through the post approach.
     */
    val form: String? = null,

    /**
     * GCS information to perform an upload.
     */
    val gcs: Gcs? = null,

    /**
     * The link of the video to capture through the pull approach.
     */
    val link: String? = null,

    /**
     * The redirect URL for the upload app.
     */
    val redirectUrl: String? = null,

    /**
     * The file size in bytes of the uploaded video.
     */
    val size: Long? = null,

    /**
     * The status code for the availability of the uploaded video.
     */
    val status: UploadStatusType? = null,

    /**
     * The link for sending video file data.
     */
    val uploadLink: String? = null

)
