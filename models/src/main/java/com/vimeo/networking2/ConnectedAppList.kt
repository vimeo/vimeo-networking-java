package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Page

/**
 * List of the logged in user's [ConnectedApps][ConnectedApp].
 */
@JsonClass(generateAdapter = true)
data class ConnectedAppList(

    @Json(name = "total")
    override val total: Int? = null,

    @Json(name = "data")
    override val data: List<ConnectedApp>? = null

) : Page<ConnectedApp>
