package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.VideoActionType
import com.vimeo.networking2.enums.VideoActionType.UNKNOWN

/**
 * Video context data.
 */
@JsonClass(generateAdapter = true)
data class VideoContext(

    /**
     * The contextual action.
     */
    @Json(name = "action")
    val action: VideoActionType = UNKNOWN,

    /**
     * The contextual resource type.
     */
    @Json(name = "resource_key")
    val resourceType: String? = null

)
