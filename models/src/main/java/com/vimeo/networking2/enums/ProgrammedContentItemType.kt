package com.vimeo.networking2.enums

/**
 * The type of programmed cinema item.
 */
enum class ProgrammedContentItemType(override val value: String?) : StringValue {

    /**
     * Category.
     */
    CATEGORY("category"),

    /**
     * Channel.
     */
    CHANNEL("channel"),

    /**
     * Unknown cinema item type.
     */
    UNKNOWN(null)
}
