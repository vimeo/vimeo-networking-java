package com.vimeo.networking2.enums

/**
 * The type of the object that is contained within a ProjectItem.
 */
enum class ProjectItemType(override val value: String?) : StringValue {

    /**
     * The ProjectItem contains a folder.
     */
    FOLDER("folder"),

    /**
     * The ProjectItem contains a video.
     */
    VIDEO("video"),

    /**
     * The ProjectItem contains an unknown type.
     */
    UNKNOWN(null)
}
