package com.vimeo.networking2
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Requires [CapabilitiesType.CAPABILITY_LIVE_EVENT].
 */
@JsonClass(generateAdapter = true)
data class StreamingError(

    /**
     * The error message that developers receive.
     */
    @Json(name = "developer_message")
    val developerMessage: String? = null,

    /**
     * The error message that non-developer users receive.
     */
    @Json(name = "error")
    val error: String? = null,

    /**
     * The error code.
     */
    @Json(name = "error_code")
    val errorCode: Int? = null,

    /**
     * A link to more information about the error.
     */
    @Json(name = "link")
    val link: String? = null

)
