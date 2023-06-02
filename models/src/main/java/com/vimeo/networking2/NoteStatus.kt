package com.vimeo.networking2

import com.vimeo.networking2.enums.StringValue

/**
 * Enum representing status of the discussion of the note.
 */
enum class NoteStatus(override val value: String?) : StringValue {
    /**
     * Note discussion status is open.
     */
    OPEN("open"),

    /**
     * Note discussion status is closed.
     */
    CLOSED("closed"),

    /**
     * Unknown discussion status.
     */
    UNKNOWN(null)
}
