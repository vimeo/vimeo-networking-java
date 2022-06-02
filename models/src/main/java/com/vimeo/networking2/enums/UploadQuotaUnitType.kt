package com.vimeo.networking2.enums

import com.vimeo.networking2.enums.StringValue

/**
 * The unit type of the approach to calculating the upload quota (e.g. a byte size based approach or a video count
 * based approach)
 *
 */
enum class UploadQuotaUnitType(override val value: String?) : StringValue {

    /**
     * The quota is calculated using count of videos.
     */
    COUNT("video_count"),

    /**
     * The quota is calculated using the size in bytes of the videos.
     */
    PERIODIC("video_size"),

    /**
     * The quota is calculated using an unknown approach.
     */
    UNKNOWN(null)

}
