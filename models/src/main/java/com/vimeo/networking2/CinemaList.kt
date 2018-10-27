package com.vimeo.networking2

import com.vimeo.networking2.common.Pageable

/**
 * List of cinema that could be paged.
 */
data class CinemaList(
    override val total: Int? = null,
    override val page: Int? = null,
    override val perPage: Int? = null,
    override val paging: Paging? = null,
    override val data: List<Cinema>? = null
) : Pageable<Cinema>
