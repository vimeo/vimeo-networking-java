package com.vimeo.networking2

/**
 * List of notifications that could be paged.
 */
data class NotificationList(
    override val total: Int? = null,
    override val page: Int? = null,
    override val perPage: Int? = null,
    override val paging: Paging? = null,
    override val data: List<Notification>? = null
) : Pageable<Notification>
