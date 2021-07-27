package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal

/**
 * GCS data.
 *
 * @param endByte Expected ending byte range for the current [uploadLink].
 * @param startByte Expected starting byte size for the current [uploadLink].
 * @param uploadLink Link for uploading file chunk to.
 */
@Internal
@JsonClass(generateAdapter = true)
data class Gcs(

    @Internal
    @Json(name = "end_byte")
    val endByte: Long? = null,

    @Internal
    @Json(name = "start_byte")
    val startByte: Long? = null,

    @Internal
    @Json(name = "upload_link")
    val uploadLink: String? = null
)
