package com.vimeo.networking2

/**
 * List of seasons that are pageable.
 */
data class SeasonList(

    /**
     * Total number of seasons.
     */
    override val total: Int? = null,

    /**
     * Current page number.
     */
    override val page: Int? = null,

    /**
     * Total number of seasons per page.
     */
    override val perPage: Int? = null,

    /**
     * Urls to the first, last page, next and previous pages.
     */
    override val paging: Paging? = null,

    /**
     * Seasons for each page.
     */
    override val data: List<Season>? = null

): Pageable<Season>
