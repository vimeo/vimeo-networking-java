package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Basic authentication for client credentials. It contains an access token
 * that you could use to make unauthenticated requests.
 */
@JsonClass(generateAdapter = true)
data class BasicAccessToken(

    /**
     * Basic access token returned by a client credentials request.
     */
    @Json(name = "access_token")
    override val accessToken: String

) : AccessTokenProvider
