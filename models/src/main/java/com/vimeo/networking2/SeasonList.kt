package com.vimeo.networking2

/**
 * List of seasons that are pageable.
 */
data class SeasonList(
    override val total: Int? = null,
    override val page: Int? = null,
    override val perPage: Int? = null,
    override val paging: Paging? = null,
    override val data: List<Season>? = null
): Pageable<Season>
