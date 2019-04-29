package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal

/**
 * GCS data.
 */
@Internal
@JsonClass(generateAdapter = true)
data class Gcs(

    /**
     * Expected ending byte range for the current upload_link.
     */
    @Internal
    @Json(name = "end_byte")
    val endByte: Int? = null,

    /**
     * Expected starting byte size for the current upload_link.
     */
    @Internal
    @Json(name = "start_byte")
    val startByte: Int? = null,

    /**
     * Link for uploading file chunk to.
     */
    @Internal
    @Json(name = "upload_link")
    val uploadLink: String? = null
)
