@file:JvmName("TranscodeUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.TranscodeStatusType
import com.vimeo.networking2.enums.asEnum

/**
 * The transcode information for a video upload.
 *
 * @param status Status code for clip availability. See [Transcode.statusType].
 */
@JsonClass(generateAdapter = true)
data class Transcode(

    @Json(name = "status")
    val status: String? = null

)

/**
 * @see Transcode.status
 * @see TranscodeStatusType
 */
val Transcode.statusType: TranscodeStatusType
    get() = status.asEnum(TranscodeStatusType.UNKNOWN)
