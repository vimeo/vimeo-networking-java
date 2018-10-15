package com.vimeo.networking2

/**
 * List of notifications that could be paged.
 */
data class NotificationList(

    /**
     * Total number of notifications.
     */
    override val total: Int? = null,

    /**
     * The current page number of paging list.
     */
    override val page: Int? = null,

    /**
     * The number of notifications to return per page.
     */
    override val perPage: Int? = null,

    /**
     * Urls to the first, last page, next and previous pages.
     */
    override val paging: Paging? = null,

    /**
     * List of notifications.
     */
    override val data: List<Notification>?

) : Pageable<Notification>
