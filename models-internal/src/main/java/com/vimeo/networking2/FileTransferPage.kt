package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal

/**
 * Information about the file transfer page.
 */
@Internal
@JsonClass(generateAdapter = true)
data class FileTransferPage(

    /**
     * The link to the file transfer page.
     */
    @Internal
    @Json(name = "link")
    val link: String? = null
)
