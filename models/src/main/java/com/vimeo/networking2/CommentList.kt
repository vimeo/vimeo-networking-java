package com.vimeo.networking2

/**
 * List of comments that are pageable.
 */
data class CommentList(

    /**
     * Total number of comments.
     */
    override val total: Int? = null,

    /**
     * Current page number.
     */
    override val page: Int? = null,

    /**
     * Total number of comments per page.
     */
    override val perPage: Int? = null,

    /**
     * Urls to the first, last page, next and previous pages.
     */
    override val paging: Paging? = null,

    /**
     * Comments for each page.
     */
    override val data: List<Comment>? = null

): Pageable<Comment>
