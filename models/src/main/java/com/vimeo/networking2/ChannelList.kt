package com.vimeo.networking2

/**
 * List of channels that are pageable.
 */
data class ChannelList(

    /**
     * Total number of channels.
     */
    override val total: Int? = null,

    /**
     * Current page number.
     */
    override val page: Int? = null,

    /**
     * Total number of channels per page.
     */
    override val perPage: Int? = null,

    /**
     * Urls to the first, last page, next and previous pages.
     */
    override val paging: Paging? = null,

    /**
     * Categories for each page.
     */
    override val data: List<Channel>? = null

): Pageable<Channel>
