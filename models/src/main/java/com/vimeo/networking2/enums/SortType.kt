package com.vimeo.networking2.enums

/**
 * The sort ordering used for sorting an album.
 */
enum class SortType(override val value: String?) : StringValue {

    ADDED_FIRST("added first"),

    ADDED_LAST("added last"),

    ALPHABETICAL("alphabetical"),

    ARRANGED("arranged"),

    COMMENTS("comments"),

    LIKES("likes"),

    NEWEST("newest"),

    OLDEST("oldest"),

    PLAYS("plays"),

    UNKNOWN(null)
}
