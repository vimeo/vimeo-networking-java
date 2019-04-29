package com.vimeo.networking2.enums

/**
 * Different types of licenses.
 */
enum class LicenseType(override val value: String?) : StringValue {

    BY("by"),

    BY_NC("by-nc"),

    BY_NC_ND("by-nc-nd"),

    BY_NC_SA("by-nc-sa"),

    BY_ND("by-nd"),

    BY_SA("by-sa"),

    CC0("by-cc0"),

    UNKNOWN(null)
}
