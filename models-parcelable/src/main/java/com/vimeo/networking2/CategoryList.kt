package com.vimeo.networking2

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Pageable
import kotlinx.android.parcel.Parcelize

/**
 * List of categories that are pageable.
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class CategoryList(

    @Json(name = "total")
    override val total: Int? = null,

    @Json(name = "page")
    override val page: Int? = null,

    @Json(name = "per_page")
    override val perPage: Int? = null,

    @Json(name = "paging")
    override val paging: Paging? = null,

    @Json(name = "data")
    override val data: List<Category>? = null

) : Pageable<Category>, Parcelable
