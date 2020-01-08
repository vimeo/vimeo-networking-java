package com.vimeo.networking2.enums

/**
 * an enumeration of the possible blockers for a video being posted.
 * NOTE: some are of these are platform specific and will be documented as such.
 */
enum class BlockerType(override val value: String?) : StringValue {
    // The video size is too large.
    SIZE("size"),

    // The video is too long.
    DURATION("duration"),

    // The user has no pages connected to their account. (Facebook only)
    FB_NO_PAGES("fb_no_pages"),

    // The user has no organization pages connected to their account. (LinkedIn only)
    LI_NO_ORGANIZATIONS("li_no_organizations"),

    UNKNOWN(null)
}
