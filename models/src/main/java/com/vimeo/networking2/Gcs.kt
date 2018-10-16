package com.vimeo.networking2

/**
 * GCS data.
 */
data class Gcs(

    /**
     * Expected ending byte range for the current upload_link.
     */
    val endByte: Int? = null,

    /**
     * Expected starting byte size for the current upload_link.
     */
    val startByte: Int? = null,

    /**
     * Link for uploading file chunk to.
     */
    val uploadLink: String? = null
)
