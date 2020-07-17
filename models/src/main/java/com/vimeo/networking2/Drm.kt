package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal

/**
 * A holder on to the drm content. There are three types, `fairplay`, `widevine`, and `playready`. Since this is a Java
 * library and only Apple products support `fairplay`, that type is omitted. Clients will only receive these if given
 * the app-specific permission, essentially this class is not consumable by the general public.
 */
@Internal
@JsonClass(generateAdapter = true)
data class Drm(
    /**
     * The video file containing the info about the DRM protected stream.
     */
    @Json(name = "widevine")
    val widevine: DashVideoFile? = null
)
