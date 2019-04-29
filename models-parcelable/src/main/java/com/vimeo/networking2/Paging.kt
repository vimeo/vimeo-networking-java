package com.vimeo.networking2

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * Paging urls for next, previous, fist and last pages.
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class Paging(

    /**
     * Next page's url.
     */
    @Json(name = "next")
    val next: String? = null,

    /**
     * Previous page's url.
     */
    @Json(name = "previous")
    val previous: String? = null,

    /**
     * First page's url.
     */
    @Json(name = "first")
    val first: String? = null,

    /**
     * Last page's url.
     */
    @Json(name = "last")
    val last: String? = null

): Parcelable
