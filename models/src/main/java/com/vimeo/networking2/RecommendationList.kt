package com.vimeo.networking2

/**
 * List of recommendations that could be paged.
 */
data class RecommendationList(
    override val total: Int? = null,
    override val page: Int? = null,
    override val perPage: Int? = null,
    override val paging: Paging? = null,
    override val data: List<Recommendation>?
) : Pageable<Recommendation>
