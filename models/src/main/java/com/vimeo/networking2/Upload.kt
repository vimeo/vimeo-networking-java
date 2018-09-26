package com.vimeo.networking2

data class Upload(

        /**
         * The approach for uploading the video.
         */
        val approach: Approach?,

        /**
         * The URI for completing the upload.
         */
        val completeUri: String?,

        /**
         * The HTML form for uploading a video through the post approach.
         */
        val form: String?,

        /**
         * GCS information to perform an upload.
         */
        val gcs: Gcs?,

        /**
         * The link of the video to capture through the pull approach.
         */
        val link: String?,

        /**
         * The redirect URL for the upload app.
         */
        val redirectUrl: String?,

        /**
         * The file size in bytes of the uploaded video.
         */
        val size: Long?,

        /**
         * The status code for the availability of the uploaded video.
         */
        val status: UploadStatus?,

        /**
         * The link for sending video file data.
         */
        val uploadLink: String?

)
