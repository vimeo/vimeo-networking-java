package com.vimeo.networking2.enums

/**
 * The status of the Create Video session.
 */
enum class EditSessionStatusType(override val value: String?) : StringValue {

    /**
     * The video is in draft stage.
     */
    PROCESSING("processing"),

    /**
     * The video is finished.
     */
    DONE("done"),

    /**
     * Unknown status value.
     */
    UNKNOWN(null)
}
