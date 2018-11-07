@file:JvmName("VideoContextUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.VideoActionType
import com.vimeo.networking2.enums.asEnum

/**
 * Video context data.
 */
@JsonClass(generateAdapter = true)
data class VideoContext(

    /**
     * The contextual action.
     */
    @Json(name = "action")
    val action: String? = null,

    /**
     * The contextual resource type.
     */
    @Json(name = "resource_key")
    val resourceType: String? = null

)

/**
 * @see VideoContext.action
 */
val VideoContext.actionType: VideoActionType
    get() = action.asEnum(VideoActionType.UNKNOWN)
