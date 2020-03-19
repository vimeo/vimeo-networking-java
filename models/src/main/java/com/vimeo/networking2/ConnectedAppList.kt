package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Page
import java.io.Serializable

/**
 * List of the logged in user's [ConnectedApps][ConnectedApp].
 */
@JsonClass(generateAdapter = true)
data class ConnectedAppList(

        @Json(name = "total")
        override val total: Int? = null,

        @Json(name = "data")
        override val data: List<ConnectedApp>? = null

) : Page<ConnectedApp>, Serializable {

        companion object {
                private const val serialVersionUID = -4687611L
        }
}
