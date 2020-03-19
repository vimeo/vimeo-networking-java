package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal
import java.io.Serializable

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
): Serializable {

    companion object {
        private const val serialVersionUID = -7340170069002914807L
    }
}
