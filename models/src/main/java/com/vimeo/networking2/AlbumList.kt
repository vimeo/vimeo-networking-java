package com.vimeo.networking2

/**
 * List of albums that could be paged.
 */
data class AlbumList(

    /**
     * Total number of albums.
     */
    override val total: Int? = null,

    /**
     * The current page number of paging list.
     */
    override val page: Int? = null,

    /**
     * The number of albums to returns per page.
     */
    override val perPage: Int? = null,

    /**
     * Urls to the first, last page, next and previous pages.
     */
    override val paging: Paging? = null,

    /**
     * List of albums.
     */
    override val data: List<Album>?

) : Pageable<Album>
