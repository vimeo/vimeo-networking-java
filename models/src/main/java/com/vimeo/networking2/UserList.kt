package com.vimeo.networking2

/**
 * List of users that could be paged.
 */
data class UserList(

    /**
     * Total number of users.
     */
    override val total: Int? = null,

    /**
     * The current page number of paging list.
     */
    override val page: Int? = null,

    /**
     * The number of users to returns per page.
     */
    override val perPage: Int? = null,

    /**
     * Urls to the first, last page, next and previous pages.
     */
    override val paging: Paging? = null,

    /**
     * List of users.
     */
    override val data: List<User>?

) : Pageable<User>
