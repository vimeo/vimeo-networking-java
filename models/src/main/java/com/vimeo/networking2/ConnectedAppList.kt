package com.vimeo.networking2

import com.squareup.moshi.Json
import com.vimeo.networking2.common.Pageable

/**
 * List of the logged in user's [ConnectedApps][ConnectedApp].
 */
data class ConnectedAppList(

        @Json(name = "total")
        override val total: Int? = null,

        @Json(name = "page")
        override val page: Int? = null,

        @Json(name = "per_page")
        override val perPage: Int? = null,

        @Json(name = "paging")
        override val paging: Paging? = null,

        @Json(name = "data")
        override val data: List<ConnectedApp>? = null

) : Pageable<ConnectedApp>
