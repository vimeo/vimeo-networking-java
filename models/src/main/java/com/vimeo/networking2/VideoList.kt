package com.vimeo.networking2

/**
 * List of videos that could be paged.
 */
data class VideoList(
    override val total: Int? = null,
    override val page: Int? = null,
    override val perPage: Int? = null,
    override val paging: Paging? = null,
    override val data: List<Video>? = null
) : Pageable<Video>
