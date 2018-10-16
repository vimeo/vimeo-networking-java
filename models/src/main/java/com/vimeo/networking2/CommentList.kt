package com.vimeo.networking2

/**
 * List of comments that are pageable.
 */
data class CommentList(
    override val total: Int? = null,
    override val page: Int? = null,
    override val perPage: Int? = null,
    override val paging: Paging? = null,
    override val data: List<Comment>? = null
): Pageable<Comment>
