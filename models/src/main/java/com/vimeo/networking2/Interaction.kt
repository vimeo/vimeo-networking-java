package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Information on taking an action on an entity.
 */
@JsonClass(generateAdapter = true)
data class Interaction(

    /**
     * An array of the HTTP methods permitted on this URI. This data requires a bearer
     * token with the private scope.
     */
    @Json(name = "options")
    val options: List<String>? = null,

    /**
     * The API URI that resolves to the connection data. This data requires a bearer
     * token with the private scope.
     */
    @Json(name = "uri")
    val uri: String? = null

)
