package com.vimeo.networking2

/**
 * List of text tracks for a video.
 */
data class TextTrackList(

    /**
     * Total number of text tracks.
     */
    override val total: Int? = null,

    /**
     * The current page number of paging list.
     */
    override val page: Int? = null,

    /**
     * The number of text tracks to return per page.
     */
    override val perPage: Int? = null,

    /**
     * Urls to the first, last page, next and previous pages.
     */
    override val paging: Paging? = null,

    /**
     * List of text tracks.
     */
    override val data: List<TextTrack>?

) : Pageable<TextTrack>
