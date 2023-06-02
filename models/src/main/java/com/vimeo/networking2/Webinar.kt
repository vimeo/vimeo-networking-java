package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * The webinar information.
 *
 * @param uri a webinar uri.
 * @param manageUrl a webinar manage url.
 * @param eventUri a webinar event uri.
 * @param eventManageUrl a webinar event manage url.
 * @param status a webinar status.
 */
@JsonClass(generateAdapter = true)
data class Webinar(

    @Json(name = "webinar_uri")
    val uri: String? = null,

    @Json(name = "webinar_manage_url")
    val manageUrl: String? = null,

    @Json(name = "webinar_event_uri")
    val eventUri: String? = null,

    @Json(name = "webinar_event_manage_url")
    val eventManageUrl: String? = null,

    @Json(name = "webinar_status")
    val status: String? = null
)
