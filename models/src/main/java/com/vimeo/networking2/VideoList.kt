package com.vimeo.networking2

/**
 * List of videos that could be paged.
 */
data class VideoList(

    /**
     * Total number of videos.
     */
    override val total: Int? = null,

    /**
     * The current page number of paging list.
     */
    override val page: Int? = null,

    /**
     * The number of videos to return per page.
     */
    override val perPage: Int? = null,

    /**
     * Urls to the first, last page, next and previous pages.
     */
    override val paging: Paging? = null,

    /**
     * List of videos.
     */
    override val data: List<Video>?

) : Pageable<Video>
