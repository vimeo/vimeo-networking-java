package com.vimeo.networking2.enums

/**
 * The availability status for the LiveQuota object.
 */
enum class LiveQuotaStatusType(override val value: String?) : StringValue {
    /**
     * The live quota is available.
     */
    AVAILABLE("available"),

    /**
     * The live quota is in private mode.
     */
    PRIVATE_MODE("private_mode"),

    /**
     * The live quota is restricted by a time limit.
     */
    TIME_LIMIT("time_limit"),

    /**
     * The live quota is restricted by a stream limit.
     */
    STREAM_LIMIT("stream_limit"),

    /**
     * Unknown value for the status.
     */
    UNKNOWN(null)
}
