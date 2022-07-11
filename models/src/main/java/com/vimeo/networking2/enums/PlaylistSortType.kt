package com.vimeo.networking2.enums

/**
 * Playlist sort types.
 */
enum class PlaylistSortType(override val value: String?) : StringValue {

    /**
     * The videos appear according to when they were added to the event, with the most recently added first.
     */
    ADDED_FIRST("added_first"),

    /**
     * The videos appear according to when they were added to the event, with the most recently added last.
     */
    ADDED_LAST("added_last"),

    /**
     * The videos appear alphabetically by their title.
     */
    ALPHABETICAL("alphabetical"),

    /**
     * The videos appear as arranged by the owner of the event.
     */
    ARRANGED("arranged"),

    /**
     * The videos appear according to their number of comments.
     */
    COMMENTS("comments"),

    /**
     * The videos appear in order of duration.
     */
    DURATION("duration"),

    /**
     * The videos appear according to their number of likes.
     */
    LIKES("likes"),

    /**
     * The videos appear in chronological order with the newest first.
     */
    NEWEST("newest"),

    /**
     * The videos appear in chronological order with the oldest first.
     */
    OLDEST("oldest"),

    /**
     * The videos appear according to their number of plays.
     */
    PLAYS("plays"),

    /**
     * The PlaylistSortType contains an unknown type.
     */
    UNKNOWN(null)
}
