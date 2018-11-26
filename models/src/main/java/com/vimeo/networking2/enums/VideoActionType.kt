package com.vimeo.networking2.enums

/**
 * All actions that can be taken on a video.
 */
enum class VideoActionType(override val value: String?) : StringValue {

    ADDED_TO("Added to"),

    APPEARANCE_BY("Appearance by"),

    LIKED_BY("Liked by"),

    UPLOADED_BY("Uploaded by"),

    UNKNOWN(null)
}
