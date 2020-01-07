package com.vimeo.networking2.enums

/**
 * An enumeration of the supported types representing the published status
 * of the upload/post on the specified platform.
 */
enum class PublishStatusType(override val value: String?) : StringValue {

    /**
     * Status when an error has occurred.
     */
    ERROR("error"),

    /**
     * Status when a job has successfully completed.
     */
    FINISHED("finished"),

    /**
     * Status when a job is currently in progress.
     */
    IN_PROGRESS("in_progress"),

    /**
     * Unknown status type.
     */
    UNKNOWN(null);

}