package com.vimeo.networking2.enums

/**
 * Created by nicholas.doglio on 2020-01-07.
 */
enum class BlockerType(override val value: String?) : StringValue {
    SIZE("size"),

    DURATION("duration"),

    FB_NO_PAGES("fb_no_pages"),

    LI_NO_ORGANIZATIONS("li_no_organizations"),

    UNKNOWN(null)
}
