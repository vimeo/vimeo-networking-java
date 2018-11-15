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
     * @see VideoContext.videoActionType
     */
    @Json(name = "action")
    val videoAction: String? = null,

    /**
     * The contextual resource type.
     */
    @Json(name = "resource_type")
    val resourceType: String? = null

)

/**
 * @see VideoContext.videoAction
 * @see VideoActionType
 */
val VideoContext.videoActionType: VideoActionType
    get() = videoAction.asEnum(VideoActionType.UNKNOWN)
