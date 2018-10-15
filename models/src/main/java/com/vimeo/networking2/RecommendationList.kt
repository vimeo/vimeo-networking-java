package com.vimeo.networking2

/**
 * List of recommendations that could be paged.
 */
data class RecommendationList(

    /**
     * Total number of recommendations.
     */
    override val total: Int? = null,

    /**
     * The current page number of paging list.
     */
    override val page: Int? = null,

    /**
     * The number of recommendations to return per page.
     */
    override val perPage: Int? = null,

    /**
     * Urls to the first, last page, next and previous pages.
     */
    override val paging: Paging? = null,

    /**
     * List of recommendations.
     */
    override val data: List<Recommendation>?

) : Pageable<Recommendation>
